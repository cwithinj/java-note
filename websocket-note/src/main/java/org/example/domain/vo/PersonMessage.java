package org.example.domain.vo;

import lombok.Data;

/**
 * 对话交流消息
 *
 * @author cjia
 * @date 2023/8/28 下午 10:48
 */
@Data
public class PersonMessage extends AbstractMessage {
    private String fromName ;
    private String toName ;
}
