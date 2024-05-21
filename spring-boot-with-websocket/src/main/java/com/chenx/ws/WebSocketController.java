package com.chenx.ws;


import io.netty.util.concurrent.RejectedExecutionHandlers;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

@Component
@Slf4j
@ServerEndpoint("/ws/userCount/{userId}")
public class WebSocketController {

    private static final Map<String, Session> SESSION_POOL = new HashMap<>();

    @OnOpen
    public void buildLink(Session session, @PathParam(value = "userId") String userId) {
        SESSION_POOL.put(userId, session);
        log.info("出现新连接");
    }

    @OnClose
    public void closeLink(@PathParam("userId") String userId) {
        log.info("有连接关闭：" + userId);
    }

    @OnMessage
    public void onMessage(@PathParam("userId") String userId, String Message) {
        log.info("收到消息: " + Message);
        respMessage(userId);
    }

    @OnError
    public void onError(Throwable t) {
        log.info("连接异常");
        t.printStackTrace();
    }

    private void respMessage(String userId) {
        for (Session session : SESSION_POOL.values()) {
            session.getAsyncRemote().sendText(userId + "，收到了用户：1 的消息");
        }
    }

    public void sendMessage() {
        for (Session session : SESSION_POOL.values()) {
            session.getAsyncRemote().sendText("嗨嗨嗨，你好");
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5, 20, 30, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(2000), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        Future<Integer> result1 = poolExecutor.submit(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i * 2);
            }
            return 2;
        });
        Future<Integer> result2 = poolExecutor.submit(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i * 5);
            }
            return 5;
        });
        System.out.println(result1.get());
        System.out.println(result2.get());
        poolExecutor.shutdown();
    }
}
