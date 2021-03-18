package cloud.dowhat.monolith.core.prop;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author linen
 */
@ConfigurationProperties(prefix = "monolith.socket.pool")
@Configuration
@Getter
@Setter
public class PoolProperties {

    private Integer port;

    private Integer timeout;

    private Integer maxCache;

    private Integer maxIdle;

    private Integer maxTotal;

    private Integer minIdle;

    private Integer initConn;

}
