package com.goldFish.service.impl;


import com.goldFish.entity.UserRegistration;
import com.goldFish.mapper.UserRegistrationMapper;
import com.goldFish.payload.UserRegistrationDTO;
import com.goldFish.repository.UserRegistrationRepository;
import com.goldFish.service.UserRegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {

    @Autowired
    private UserRegistrationRepository userRegistrationRepository;
    @Autowired
    private UserRegistrationMapper userRegistrationMapper;

    @Override
    public void saveUser(UserRegistrationDTO registrationDTO) {

        UserRegistration save = userRegistrationRepository.save(userRegistrationMapper.userRegistrationDTOtoEntityConverter(registrationDTO));
        System.out.println(save.getPassword());
        log.info("Registration Complete");
    }
}
