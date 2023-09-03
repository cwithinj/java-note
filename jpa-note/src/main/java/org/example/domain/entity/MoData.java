package org.example.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * MoData测试类
 *
 * @author cjia
 * @date 2023/8/27 下午 12:59
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "t_mo_data")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MoData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "line_body")
    private String lineBody;

    @Column(name = "box_num")
    private Long boxNum;

    @Column(name = "create_time")
    private Timestamp createTime;

    @Column(name = "update_time")
    private Timestamp updateTime;
}
