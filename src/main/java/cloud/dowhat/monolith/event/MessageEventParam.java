package cloud.dowhat.monolith.event;

import lombok.Data;
import lombok.ToString;
import org.apache.commons.mail.util.MimeMessageParser;

import java.io.Serializable;

/**
 * @author linen
 */
@Data
@ToString
public class MessageEventParam implements Serializable {

    private MimeMessageParser parser;

}
