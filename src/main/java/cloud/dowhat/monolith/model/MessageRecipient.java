package cloud.dowhat.monolith.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author linen
 */
@Data
public class MessageRecipient implements Serializable {

    private Long id;

    private Long messageId;

    private Long recipientId;

    private Integer type;

}
