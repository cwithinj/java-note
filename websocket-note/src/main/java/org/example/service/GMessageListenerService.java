package org.example.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.example.domain.entity.UserSession;
import org.example.domain.vo.SystemMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 处理websocket连接、发送消息以及错误情况的业务类
 *
 * @author cjia
 * @date 2023/8/28 下午 10:50
 */

@ServerEndpoint(value = "/message/{username}")
@Component
public class GMessageListenerService {
    public static ConcurrentMap<String, UserSession> sessions = new ConcurrentHashMap<>();
    private static Logger logger = LoggerFactory.getLogger(GMessageListenerService.class);

    private String username;

    /**
     * 建立连接时触发的方法
     *
     * @param session  会话
     * @param config   配置
     * @param username 用户名
     */
    @OnOpen
    public void onOpen(Session session, EndpointConfig config, @PathParam("username") String username) {
        UserSession userSession = new UserSession(session.getId(), username, session);
        this.username = username;
        sessions.put(username, userSession);
        //sessions.put(username, new ConcurrentWebSocketSessionDecorator(session, 10 * 1000, 64000));
        logger.info("【{}】用户进入, 当前连接数：{}", username + ":" + session.getId(), sessions.size());
    }

    /**
     * 关闭连接时触发的方法
     *
     * @param session 会话
     * @param reason  原因
     */
    @OnClose
    public void onClose(Session session, CloseReason reason) {
        UserSession userSession = sessions.remove(this.username);
        if (userSession != null) {
            logger.info("用户【{}】, 断开连接, 当前连接数：{}", username, sessions.size());
        }
    }


    /**
     * 接收消息触发的方法
     *
     * @param session 会话
     * @param message 消息
     * @throws IOException ioexception
     */
    @OnMessage
    public void onMessage(Session session, String message) throws IOException {
        logger.info("接受到消息{}", message);
        JSONObject jsonObject = JSON.parseObject(message);
        logger.info("解析的内容:{}", jsonObject.get("type"));
        logger.info("解析的内容:{}", jsonObject.get("content"));
        SystemMessage systemMessage = jsonObject.toJavaObject(SystemMessage.class);
        logger.info("解析之后转换:{}", systemMessage);
        session.getBasicRemote().sendText("消息收到: " + message);
        session.getAsyncRemote().sendText("sss");
    }


    /**
     * 出现错误时触发的方法
     *
     * @param session 会话
     * @param error   错误
     */
    @OnError
    public void onError(Session session, Throwable error) {
        logger.error(error.getMessage());
    }
}
