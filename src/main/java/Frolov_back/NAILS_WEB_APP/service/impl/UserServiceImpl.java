package Frolov_back.NAILS_WEB_APP.service.impl;

import Frolov_back.NAILS_WEB_APP.domain.SystemUser;
import Frolov_back.NAILS_WEB_APP.service.DTO.UserDto;
import Frolov_back.NAILS_WEB_APP.service.DTO.UserProfileDto;
import Frolov_back.NAILS_WEB_APP.service.DTO.UserRegistrationRequestDto;
import Frolov_back.NAILS_WEB_APP.service.DTO.UserResponseDto;
import Frolov_back.NAILS_WEB_APP.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public Optional<UserResponseDto> registerNewUser(UserRegistrationRequestDto userRegistrationRequestDto) {
        return Optional.empty();
    }

    @Override
    public Optional<SystemUser> findUserByEmail(String email) {
        return Optional.empty();
    }
}
