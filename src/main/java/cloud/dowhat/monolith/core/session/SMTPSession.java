package cloud.dowhat.monolith.core.session;

import cloud.dowhat.monolith.core.component.MonolithEventManager;
import cloud.dowhat.monolith.event.MessageEvent;
import cloud.dowhat.monolith.event.MessageEventParam;
import cloud.dowhat.monolith.model.enums.SmtpEnum;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.mail.util.MimeMessageParser;
import org.apache.commons.mail.util.MimeMessageUtils;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.io.*;
import java.net.Socket;

/**
 * @author linen
 */
@Log4j2
@Component
public class SMTPSession {

    @Setter
    private Socket socket;
    private BufferedReader br;
    private PrintStream ps;

    public void run() {
        try {
            synchronized (this) {
                br = new BufferedReader(
                        new InputStreamReader(socket.getInputStream())
                );
                ps = new PrintStream(
                        socket.getOutputStream()
                );
            }
            doWelcome();
            String line;
            line = br.readLine();
            while (line != null) {
                log.info("\nsmtp infos:{}", line);
                String command = line.substring(0, 4).trim();
                if (command.equalsIgnoreCase(SmtpEnum.HELO.getCode()) || command.equalsIgnoreCase(SmtpEnum.EHLO.getCode())) {
                    doHello();
                } else if (command.equalsIgnoreCase(SmtpEnum.RSET.getCode())) {
                    doRset();
                } else if (command.equalsIgnoreCase(SmtpEnum.MAIL.getCode())) {
                    doMail();
                } else if (command.equalsIgnoreCase(SmtpEnum.RCPT.getCode())) {
                    doRcpt();
                } else if (command.equalsIgnoreCase(SmtpEnum.DATA.getCode())) {
                    doData();
                } else if (command.equalsIgnoreCase(SmtpEnum.NOOP.getCode())) {
                    doNoop();
                } else if (command.equalsIgnoreCase(SmtpEnum.QUIT.getCode())) {
                    doQuit();
                    break;
                }
                line = br.readLine();
            }
        } catch (IOException e) {
            ps.print("500  ERROR\r\n");
            log.error("message switching error....", e);
        } finally {
            try {
                br.close();
                ps.close();
                synchronized (this) {
                    socket.close();
                    log.info("socket current close...");
                }
            } catch (IOException e) {
                ps.print("500  ERROR\r\n");
                log.error("It is possible that an error occurred when the stream was closed....", e);
            }
        }
    }

    /**
     * nothing
     */
    private void doNoop() {
        ps.print("250 OK\r\n");
    }

    /**
     * close
     */
    private void doQuit() {
        ps.print("221 Welcome to my smtp serve\r\n");
    }

    /**
     * parse mine
     */
    private void doData() {
        try {
            ps.print("354 Start mail input; end with <CRLF>.<CRLF>\r\n");
            String line = null;
            StringBuilder stringBuffer = new StringBuilder();
            while ((line = br.readLine()) != null) {
                if (".".equals(line)) {
                    break;
                }
                stringBuffer.append(line).append("\r");
            }
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
            try {
                //publish insert to db event
                MimeMessage mimeMessage = MimeMessageUtils.createMimeMessage(null, new ByteArrayInputStream(stringBuffer.toString().getBytes()));
                MimeMessageParser parser = new MimeMessageParser(mimeMessage);
                log.info("publish event=============>> 1 step");
                MessageEventParam messageEventParam = new MessageEventParam();
                messageEventParam.setParser(parser);
                MessageEvent event = new MessageEvent(messageEventParam);
                MonolithEventManager.publishEvent(event);
                log.info("publish event=============>> 2 step");
            } catch (Exception e) {
                log.error("publish event error", e);
                ps.print("500  ERROR\r\n");
            }
            ps.print("250 OK\r\n");
        } catch (IOException e) {
            ps.print("500  ERROR\r\n");
            log.error("doData error....", e);
        }
    }

    private void doRcpt() {
        ps.print("250  OK\r\n");
    }

    /**
     * do mail
     */
    private void doMail() {
        ps.print("250  OK\r\n");
    }

    /**
     * reset
     */
    private void doRset() {
        ps.print("250 OK\r\n");
    }

    /**
     * smtp supports
     */
    private void doHello() {
        ps.print("250-Welcome to my smtp server\r\n250-PIPELINING\r\n250-SIZE 10485760\r\n250-8BITMIME\r\n250 SMTPUTF8\r\n");
    }

    /**
     * one
     */
    private void doWelcome() {
        ps.print("220 mail.gitee.ltd Service ready\r\n");
    }
}
