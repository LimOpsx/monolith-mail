package cloud.dowhat.monolith.event;

import cloud.dowhat.monolith.core.utils.MailUtil;
import cloud.dowhat.monolith.model.Message;
import cloud.dowhat.monolith.web.service.IMessageService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.mail.util.MimeMessageParser;
import org.springframework.context.event.EventListener;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author linen
 */
@Component
@AllArgsConstructor
@Log4j2
public class MessageListener {

    public static final String ADDRESSES_REGEX = ",";
    private final IMessageService iMessageService;

    /**
     * save message to db
     * @param source cloud.dowhat.monolith.event.MessageEventParam
     * @throws Exception error
     */
    @Async
    @EventListener(MessageEvent.class)
    @Retryable(backoff = @Backoff(delay = 5000L, multiplier = 1))
    public void save(MessageEvent source) {
        MessageEventParam messageEventParam = (MessageEventParam) source.getSource();
        //assembly data save to db
        MimeMessageParser parser = messageEventParam.getParser();
        Message message = new Message();
        String from = null;
        try {
            from = parser.getFrom();
        message.setSenderAddress(from);
        String subject = parser.getSubject();
        message.setSubject(subject);
        String plainContent = parser.parse().getPlainContent();
        message.setContent(plainContent);
        String receiveAddresses = MailUtil.getReceiveAddresses(parser.getMimeMessage(), null);
        log.info("\nEmail Info:{from:{}\nto:{}\nsubject:{}\ncontent:{}\n}", from, receiveAddresses, subject, plainContent);
        iMessageService.save(message, receiveAddresses.split(ADDRESSES_REGEX));
        } catch (Exception e) {
            log.error("save message to db error...",e);
        }
    }

}
