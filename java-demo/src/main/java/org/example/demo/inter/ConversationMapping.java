package org.example.demo.inter;

import org.mapstruct.control.MappingControl;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 类描述
 *
 * @author cjia
 * @date 2023/9/20 下午 10:22
 */

@Retention(RetentionPolicy.CLASS)
@MappingControl(MappingControl.Use.DIRECT)
@MappingControl(MappingControl.Use.MAPPING_METHOD)
@MappingControl(MappingControl.Use.COMPLEX_MAPPING)
public @interface ConversationMapping {
}
