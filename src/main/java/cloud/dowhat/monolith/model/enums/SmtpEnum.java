package cloud.dowhat.monolith.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * smtp enum
 */
@Getter
@AllArgsConstructor
public enum SmtpEnum {

    HELO("HELO"),
    EHLO("EHLO"),
    MAIL("MAIL"),
    RCPT("RCPT"),
    DATA("DATA"),
    QUIT("QUIT"),
    RSET("RSET"),
    NOOP("NOOP");

    final String code;

}
