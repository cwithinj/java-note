package org.example.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.persistence.EntityManager;

/**
 * JPAQueryFactory配置
 *
 * @author cjia
 * @date 2023/8/26 上午 11:08
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Bean
    public JPAQueryFactory jpaQuery(EntityManager entityManager) {
        return new JPAQueryFactory(entityManager);
    }
}
