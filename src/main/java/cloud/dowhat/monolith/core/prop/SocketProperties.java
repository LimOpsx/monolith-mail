package cloud.dowhat.monolith.core.prop;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * @author linen
 */

@Component
@ConfigurationProperties(prefix = "monolith.socket")
@Getter
@Setter
public class SocketProperties {

    private Integer port;

}
