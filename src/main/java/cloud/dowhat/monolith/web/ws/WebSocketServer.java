package cloud.dowhat.monolith.web.ws;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * WebSocketServer
 * @author linen
 */
@ServerEndpoint(value = "/join/{address}")
@Service
@Getter
@Setter
@Log4j2
public class WebSocketServer {

    private static int onlineCount = 0;
    @Getter
    private static CopyOnWriteArraySet<ConcurrentHashMap<String,WebSocketServer>> WEB_SOCKET_SERVERS = new CopyOnWriteArraySet<ConcurrentHashMap<String,WebSocketServer>>();
    private Session session;
    @OnOpen
    public void onOpen(@PathParam("address") String address, Session session) {
        this.session = session;
        ConcurrentHashMap<String, WebSocketServer> socketServerConcurrentHashMap = new ConcurrentHashMap<>();
        socketServerConcurrentHashMap.put(address,this);
        WEB_SOCKET_SERVERS.add(socketServerConcurrentHashMap);
        addOnlineCount();
        log.info("\nnew online count!current online count:{},mail_address:{}",getOnlineCount(),address);
    }

    @OnClose
    public void onClose(@PathParam("address") String address) {
        ConcurrentHashMap<String,WebSocketServer> o = new ConcurrentHashMap<>();
        o.put(address,this);
        WEB_SOCKET_SERVERS.remove(o);
        subOnlineCount();
        log.info("have one connection closed!current online count:{}",getOnlineCount());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("ws error...");
    }

    public void sendMessage(String json) throws IOException {
        this.session.getBasicRemote().sendText(json);
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }



}
