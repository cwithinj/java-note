package org.example.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 类描述
 *
 * @author cjia
 * @date 2023/9/3 下午 03:15
 */
@SpringBootTest
class MoDataRepositoryTest01 {
    @Autowired
    JPAQueryFactory jpaQueryFactory;
    @BeforeEach
    void setUp() {
    }
    @Test
    void test01(){

    }
}