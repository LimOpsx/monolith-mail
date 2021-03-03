package cloud.dowhat.monolith.core.session.smtp;

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
            br = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );
            ps = new PrintStream(
                    socket.getOutputStream()
            );
            doWelcome();
            String line;
            line = br.readLine();
            while (line != null) {
                log.info("\nsmtp infos:{}", line);
                String command = line.substring(0, 4).trim();
                if (command.equalsIgnoreCase(SmtpEnum.HELO.getCode()) || command.equalsIgnoreCase(SmtpEnum.EHLO.getCode())) {
                    doHello();
                } else if (command.equalsIgnoreCase(SmtpEnum.RSET.getCode()))
                    doRset();
                else if (command.equalsIgnoreCase(SmtpEnum.MAIL.getCode()))
                    doMail(line);
                else if (command.equalsIgnoreCase(SmtpEnum.RCPT.getCode()))
                    doRcpt(line);
                else if (command.equalsIgnoreCase(SmtpEnum.DATA.getCode()))
                    doData();
                else if (command.equalsIgnoreCase(SmtpEnum.NOOP.getCode()))
                    doNoop();
                else if (command.equalsIgnoreCase(SmtpEnum.QUIT.getCode())) {
                    doQuit();
                    break;
                }
                line = br.readLine();
            }
        } catch (IOException e) {
            ps.println("500  ERROR");
            log.error("message switching error....", e);
        } finally {
            try {
                br.close();
                ps.close();
                socket.close();
            } catch (IOException e) {
                ps.println("500  ERROR");
                log.error("It is possible that an error occurred when the stream was closed....", e);
            }
        }
    }

    private void doNoop() {
        ps.println("250 OK");
    }

    private void doQuit() {
        ps.println("221 Welcome to my smtp serve");
    }

    private void doData() {
        try {
            ps.println("354 End data with <CR><LF>.<LF><CR>");
            String line = null;
            StringBuilder stringBuffer = new StringBuilder();
            while ((line = br.readLine()) != null) {
                if (line.equals(".")) {
                    break;
                }
                stringBuffer.append(line).append("\r");
            }
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
            try {
                //event and async execute
                MimeMessage mimeMessage = MimeMessageUtils.createMimeMessage(null, new ByteArrayInputStream(stringBuffer.toString().getBytes()));
                MimeMessageParser parser = new MimeMessageParser(mimeMessage);
                MessageEventParam messageEventParam = new MessageEventParam();
                messageEventParam.setParser(parser);
                MessageEvent event = new MessageEvent(messageEventParam);
                MonolithEventManager.publishEvent(event);
            } catch (Exception e) {
                log.error("publish event error",e);
                ps.println("500  ERROR");
            }
            ps.println("250 OK");
            ps.flush();
        } catch (IOException e) {
            ps.println("500  ERROR");
            log.error("doData error....", e);
        }
    }

    private void doRcpt(String command) {
        ps.println("250  OK");
        ps.flush();
    }

    private void doMail(String command) {
        ps.println("250  OK");
        ps.flush();
    }

    private void doRset() {
        ps.println("250 OK");
        ps.flush();
    }

    private void doHello() {
        ps.println("250-Welcome to my smtp server");
        ps.println("250-PIPELINING");
        ps.println("250-SIZE 10485760");
        ps.println("250 8BITMIME");
        ps.flush();
    }

    private void doWelcome() {
        ps.println("220 Welcome to my smtp server");
        ps.flush();
    }
}
