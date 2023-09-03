package org.example.service;

import org.example.domain.entity.MoData;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * MoData业务类
 *
 * @author cjia
 * @date 2023/9/3 下午 03:13
 */
public interface MoDataService extends JpaRepository<MoData, Long> {
}
