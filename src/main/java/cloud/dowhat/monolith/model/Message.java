package cloud.dowhat.monolith.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Mail message body
 *
 * @author linen
 */
@Data
public class Message implements Serializable {

    private Long id;

    private String senderAddress;

    private String subject;

    private String content;

    private Integer del;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
