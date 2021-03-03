package cloud.dowhat.monolith.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author linen
 */
public class MessageEvent extends ApplicationEvent {

    public MessageEvent(MessageEventParam source) {
        super(source);
    }
}
