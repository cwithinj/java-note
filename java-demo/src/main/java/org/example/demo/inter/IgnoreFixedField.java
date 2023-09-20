package org.example.demo.inter;

import org.mapstruct.Mapping;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 类描述
 *
 * @author cjia
 * @date 2023/9/20 下午 10:26
 */
@Mapping(target = "id", ignore = true)
@Mapping(target = "createTime", ignore = true)
//@Mapping(target = "updateTime", ignore = true)
@Target(METHOD)
@Retention(RUNTIME)
@Documented
public @interface IgnoreFixedField {
}
