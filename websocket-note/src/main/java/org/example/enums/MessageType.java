package org.example.enums;

/**
 * 消息类型
 *
 * @author cjia
 * @date 2023/8/28 下午 10:49
 */
public enum MessageType {
    /**
     *   系统消息 0000;心跳检查消息 0001;点对点消息2001
     */
    SYSTEM("0000"), PING("0001"), PERSON("2001") ;

    private String type ;

    private MessageType(String type) {
        this.type = type ;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
