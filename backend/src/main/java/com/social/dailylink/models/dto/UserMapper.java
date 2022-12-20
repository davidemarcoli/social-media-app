package com.social.dailylink.models.dto;

import com.social.dailylink.generic.DTOMapper;
import com.social.dailylink.global.GlobalStrings;
import com.social.dailylink.models.Role;
import com.social.dailylink.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = GlobalStrings.MAPPER_COMPONENT_MODEL_NAME, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends DTOMapper<User, UserDTO> {
}
