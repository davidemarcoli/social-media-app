package com.social.dailylink.models.dto;

import com.social.dailylink.generic.DTOMapper;
import com.social.dailylink.models.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper extends DTOMapper<Category, CategoryDTO> {
}
