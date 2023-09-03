package org.example.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.websocket.Session;


/**
 * 自定义UserSession实体类
 *
 * @author cjia
 * @date 2023/8/28 下午 11:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSession {
    private String id;
    private String userName;
    private Session session;
}
