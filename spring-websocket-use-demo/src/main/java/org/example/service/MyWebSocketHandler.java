package org.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * websocekt的数据发送
 *
 * @author JIA*/
@Slf4j
@Component
public class MyWebSocketHandler implements WebSocketHandler {
    private ConcurrentHashMap<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 当WebSocket连接建立成功时调用

        // 获取url指定的参数信息：ws://127.0.0.1:10010/ws?scanPoint=01&userId=123
        String scanPoint = extractParams(session, "scanPoint");
        if (Objects.isNull(scanPoint)) {
            return;
        }
        log.info("===========>扫码点:{}",scanPoint);
        final String userId = extractParams(session, "userId");
        log.info("===========>userId:{}",userId);
        sessions.put(scanPoint, session);

    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        // 当接收到WebSocket消息时调用
        String scanPoint = extractParams(session, "scanPoint");
        // 获取消息内容
        String payload = (String) message.getPayload();
        log.info("服务端收到客户端【{}】的消息:{}", scanPoint, payload);
        //这里假设服务端收到客户端的消息，需要返回信息
        //sendMessageToScanPoint(scanPoint, payload);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // 当WebSocket连接关闭时调用
        String scanPoint = extractParams(session, "scanPoint");
        if (Objects.isNull(scanPoint)) {
            return;
        }
        // 将扫码点位和session从Map中移除
        sessions.remove(scanPoint);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        // 当WebSocket传输发生错误时调用
        // 可以在这里进行错误处理逻辑
    }

    /**
     * 获取url拼接的参数信息
     */
    private String extractParams(WebSocketSession session, String param) {
        // 从session中提取扫码点位的逻辑，根据实际情况实现
        // 这里假设扫码点位存储在session的attributes中
        try {
            String query = Objects.requireNonNull(session.getUri()).getQuery();
            Map<String, String> params = new HashMap<>();
            if (query != null) {
                String[] pairs = query.split("&");
                for (String pair : pairs) {
                    int idx = pair.indexOf("=");
                    String key = URLDecoder.decode(pair.substring(0, idx), "UTF-8");
                    String value = URLDecoder.decode(pair.substring(idx + 1), "UTF-8");
                    params.put(key, value);
                }
            }
            return params.get(param);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void sendMessageToScanPoint(String scanPoint, String message) throws IOException {
        // 根据扫码点位获取对应的session，并发送消息
        if (!sessions.containsKey(scanPoint)) {
            return;
        }
        sessions.get(scanPoint).sendMessage(new TextMessage(message));

    }

    private void sendMessageToAll(String message) throws IOException {
        // 根据扫码点位获取对应的session，群发消息
        for (Map.Entry<String, WebSocketSession> sessionEntry : sessions.entrySet()) {
            if (sessionEntry.getValue().isOpen()) {
                sessionEntry.getValue().sendMessage(new TextMessage(message));
            }
        }
    }
}
