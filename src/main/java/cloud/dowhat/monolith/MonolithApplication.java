package cloud.dowhat.monolith;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author linen
 */
@EnableAsync
@EnableRetry
@MapperScan(basePackages = {"cloud.dowhat.monolith.web.mapper"})
@SpringBootApplication
public class MonolithApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonolithApplication.class, args);
    }



}
