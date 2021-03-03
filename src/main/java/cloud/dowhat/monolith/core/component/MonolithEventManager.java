package cloud.dowhat.monolith.core.component;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * @author linen
 */
@Slf4j
@Service
@Lazy(false)
public class MonolithEventManager implements ApplicationContextAware, DisposableBean {

    @Getter
    private static ApplicationContext applicationContext;

    @Override
    public void destroy() throws Exception {
        log.info("clear MonolithEventManager the ApplicationContext:{}", applicationContext);
        applicationContext = null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        MonolithEventManager.applicationContext = applicationContext;
    }


    /**
     * publishEvent to eventLister
     *
     * @param event event
     */
    public static void publishEvent(ApplicationEvent event) {
        if (applicationContext == null) {
            return;
        }
        applicationContext.publishEvent(event);
    }

}
