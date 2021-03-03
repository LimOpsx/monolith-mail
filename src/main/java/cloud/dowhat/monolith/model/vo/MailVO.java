package cloud.dowhat.monolith.model.vo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @author linen
 */
@Data
public class MailVO {

    private String from;

    private String to;

    private String subject;

    private String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time;

    private Integer type;

}
