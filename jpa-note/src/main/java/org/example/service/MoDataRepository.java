package org.example.service;

import org.example.domain.entity.MoData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * MoData业务类
 *
 * @author cjia
 * @date 2023/9/3 下午 03:13
 */
@Repository
public interface MoDataRepository extends JpaRepository<MoData, Long> {
}
