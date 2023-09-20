package org.example.demo.inter;

import org.example.demo.SourceData;
import org.example.demo.TargetData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.control.DeepClone;
import org.mapstruct.factory.Mappers;

/**
 * 类描述
 *
 * @author cjia
 * @date 2023/9/20 下午 10:15
 */
@Mapper(typeConversionPolicy = ReportingPolicy.ERROR, mappingControl = ConversationMapping.class)
public interface BeanMapper {
    BeanMapper INSTANCE = Mappers.getMapper(BeanMapper.class);

    /**
     * @param source
     * @return {@link TargetData}
     */
    @IgnoreFixedField
    @Mapping(target = "data", mappingControl = DeepClone.class)
    //@Mapping(target = "id", ignore = true)
    TargetData map(SourceData source);
}
