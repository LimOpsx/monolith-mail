package cloud.dowhat.monolith.core.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author linen
 */
@Configuration
@ConfigurationProperties(prefix = "mail")
@Getter
@Setter
public class MailConfig {

    private String host;

}
