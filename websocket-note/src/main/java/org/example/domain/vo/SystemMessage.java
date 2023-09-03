package org.example.domain.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统消息
 *
 * @author cjia
 * @date 2023/8/28 下午 10:48
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SystemMessage extends AbstractMessage{
    public SystemMessage() {}
    public SystemMessage(String type, String content) {
        this.type = type ;
        this.content = content ;
    }
}
