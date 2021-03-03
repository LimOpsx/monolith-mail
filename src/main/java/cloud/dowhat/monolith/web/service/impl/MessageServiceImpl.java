package cloud.dowhat.monolith.web.service.impl;

import cloud.dowhat.monolith.model.Message;
import cloud.dowhat.monolith.model.MessageRecipient;
import cloud.dowhat.monolith.model.Recipient;
import cloud.dowhat.monolith.model.enums.EmailTypeEnum;
import cloud.dowhat.monolith.web.mapper.IMessageMapper;
import cloud.dowhat.monolith.web.mapper.IMessageRecipientMapper;
import cloud.dowhat.monolith.web.mapper.IRecipientMapper;
import cloud.dowhat.monolith.web.service.IMessageService;
import cloud.dowhat.monolith.web.ws.WebSocketServer;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

/**
 * @author linen
 */
@Service
@AllArgsConstructor
@Log4j2
public class MessageServiceImpl implements IMessageService {

    private final IMessageMapper iMessageMapper;

    private final IMessageRecipientMapper iMessageRecipientMapper;

    private final IRecipientMapper iRecipientMapper;

    /**
     * save to db
     *
     * @param message   email model
     * @param recipient s
     */
    @Override
    public void save(Message message, Recipient recipient) {
        iMessageMapper.insert(message);
    }

    /**
     * save to db
     *
     * @param message   email model
     * @param addresses s
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void save(Message message, String[] addresses) {
        //assembly intermediate table and insert
        iMessageMapper.insert(message);
        //query address
        Map<String, Long> addressAndId = iRecipientMapper.selectListByAddress(addresses).stream().collect(Collectors.toMap(Recipient::getRecipientAddress, Recipient::getId));

        //insert
        for (int i = 0; i < addresses.length; i++) {
            MessageRecipient messageRecipient = new MessageRecipient();
            messageRecipient.setMessageId(message.getId());
            String address = addresses[i];
            messageRecipient.setRecipientId(addressAndId.get(address));
            messageRecipient.setType(EmailTypeEnum.getCodeByAddressIndex(i));
            iMessageRecipientMapper.insert(messageRecipient);
            //send mail content to html
            CopyOnWriteArraySet<ConcurrentHashMap<String, WebSocketServer>> concurrentHashMapCopyOnWriteArraySet = WebSocketServer.getWEB_SOCKET_SERVERS();
            concurrentHashMapCopyOnWriteArraySet.forEach(webSocketServerConcurrentHashMap -> {
                try {
                    if (webSocketServerConcurrentHashMap.get(address) != null) {
                        log.info("publish ws_query_string:{}", address);
                        WebSocketServer webSocketServer = webSocketServerConcurrentHashMap.get(address);
                        webSocketServer.sendMessage(JSONObject.toJSONString(iMessageMapper.getMessages(address).get(0)));
                    }
                } catch (IOException e) {
                    log.error("send msg to html error...", e);
                }
            });
        }
    }
}
