package com.chenx.websocket;

import org.apache.juli.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArrayList;

@ServerEndpoint("/ws/{userId}")
@Component
public class ScanCodeLoginWebSocket {
    private static Logger log = LoggerFactory.getLogger(ScanCodeLoginWebSocket.class);

    public static final CopyOnWriteArrayList<ScanCodeLoginWebSocket> websocketList = new CopyOnWriteArrayList<>();

    private static volatile int count = 0;
    private Session session;

    private String userId;  // 当前连接的用户id
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        this.session = session;
        websocketList.add(this);
        count ++;
        log.info("有新窗口开始监听: + " + userId + "，当前在线人数为" + count);
        this.userId = userId;
    }

    @OnClose
    public void onClose() {
        websocketList.remove(this);
        count --;
        log.info("有一连接关闭，当前在线人数为" + count);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到客户端发来的消息:" + message);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }

    public void sendMessage(String message) {
        this.session.getAsyncRemote().sendText(message);
    }

}
