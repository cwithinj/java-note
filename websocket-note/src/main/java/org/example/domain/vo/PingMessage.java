package org.example.domain.vo;

/**
 * ping消息
 *
 * @author cjia
 * @date 2023/8/28 下午 10:47
 */
public class PingMessage extends AbstractMessage{
    public PingMessage() {}
    public PingMessage(String type) {
        this.type = type ;
    }
}
