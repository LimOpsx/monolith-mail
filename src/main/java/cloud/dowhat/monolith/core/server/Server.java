package cloud.dowhat.monolith.core.server;

import cloud.dowhat.monolith.core.session.smtp.SMTPSession;
import cn.hutool.core.thread.ThreadUtil;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.ServerSocket;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author linen
 */
@Component
@Log4j2
@AllArgsConstructor
public class Server {

    private final SMTPSession smtpSession;

    @PostConstruct
    public void listener() {
        ThreadUtil.newExecutor(5,10).execute(()->{
            AtomicReference<ServerSocket> serverSocket = new AtomicReference<>();
            //listener port:25
            log.info(new Date() + "\tThe mailbox has been started");
            try {
                serverSocket.set(new ServerSocket(25));
                for (; ; ) {
                    smtpSession.setSocket(serverSocket.get().accept());
                    //todo: sleep 2s
                    ThreadUtil.sleep(2, TimeUnit.SECONDS);
                    smtpSession.run();
                }
            }catch (Exception e){
                log.error("smtp start error...",e);
                System.exit(1);
            }
        });

    }

}
