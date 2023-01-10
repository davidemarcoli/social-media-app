package com.social.dailylink.model.dto;

import com.social.dailylink.generic.DTOMapper;
import com.social.dailylink.global.GlobalStrings;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = GlobalStrings.MAPPER_COMPONENT_MODEL_NAME, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper extends DTOMapper<Category, CategoryDTO> {
}
