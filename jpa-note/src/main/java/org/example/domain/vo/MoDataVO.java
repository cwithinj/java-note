package org.example.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * 类描述
 *
 * @author cjia
 * @date 2023/9/3 下午 03:50
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoDataVO {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 线体编号
     */
    private String lineBody;

    /**
     * 箱体数量
     */
    private Long boxNum;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 修改时间
     */
    private Timestamp updateTime;
}
