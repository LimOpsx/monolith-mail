package cloud.dowhat.monolith.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * recipients
 *
 * @author linen
 *
 */
@Data

public class Recipient {

    private Long id;

    private String recipientAddress;

    private Integer del;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
