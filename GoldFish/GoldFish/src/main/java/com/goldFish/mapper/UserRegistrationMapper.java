package com.goldFish.mapper;

import com.goldFish.entity.UserRegistration;
import com.goldFish.payload.UserRegistrationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;


@Mapper(componentModel = "spring")
public abstract class UserRegistrationMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Mapping(target = "password", source = "password", qualifiedByName = "mapPasswordtoEncoder")
    public abstract UserRegistration userRegistrationDTOtoEntityConverter(UserRegistrationDTO userRegistrationDTO);

    @Named("mapPasswordtoEncoder")
    String mapPasswordtoEncoder(String password) {
        return passwordEncoder.encode(password);
    }
}


