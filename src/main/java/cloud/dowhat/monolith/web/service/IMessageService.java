package cloud.dowhat.monolith.web.service;

import cloud.dowhat.monolith.model.Message;
import cloud.dowhat.monolith.model.Recipient;

public interface IMessageService {

    /**
     * save to db
     * @param message email model
     * @param recipient s
     */
    void save(Message message, Recipient recipient);

    /**
     * save to db
     * @param message email model
     * @param addresses s
     */
    void save(Message message, String[] addresses);
}
