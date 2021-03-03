package cloud.dowhat.monolith.core.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author linen
 */
@Log4j2
@UtilityClass
public class MailUtil {


    /**
     * if to the type,get email to and copy to and blind carbon copy.if so to is null,then get all to
     * <p>Message.RecipientType.TO  recipients</p>
     * <p>Message.RecipientType.CC  copy to</p>
     * <p>Message.RecipientType.BCC blind carbon copy</p>
     *
     * @param mimeMessage   email content
     * @param recipientType to the type
     * @return to1 <email1>, to2 <email2>, ...
     * @throws MessagingException error
     */
    public String getReceiveAddresses(MimeMessage mimeMessage, Message.RecipientType recipientType) throws MessagingException {
        StringBuilder receiveAddress = new StringBuilder();
        Address[] addresses = null;
        if (recipientType == null) {
            addresses = mimeMessage.getAllRecipients();
        } else {
            addresses = mimeMessage.getRecipients(recipientType);
        }

        if (addresses == null || addresses.length < 1)
            throw new MessagingException("no Receive!");
        for (Address address : addresses) {
            InternetAddress internetAddress = (InternetAddress) address;
            receiveAddress.append(internetAddress.toUnicodeString()).append(",");
        }

        receiveAddress.deleteCharAt(receiveAddress.length() - 1);

        return receiveAddress.toString();
    }

}
