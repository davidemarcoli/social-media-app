package com.social.dailylink.models.dto;

import com.social.dailylink.generic.DTOMapper;
import com.social.dailylink.global.GlobalStrings;
import com.social.dailylink.models.Authority;
import com.social.dailylink.models.Post;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = GlobalStrings.MAPPER_COMPONENT_MODEL_NAME, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthorityMapper extends DTOMapper<Authority, AuthorityDTO> {
}
