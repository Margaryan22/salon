package Frolov_back.NAILS_WEB_APP.service;

import Frolov_back.NAILS_WEB_APP.service.DTO.UserDto; // Пример DTO
import Frolov_back.NAILS_WEB_APP.service.DTO.UserProfileDto; // Пример DTO
import Frolov_back.NAILS_WEB_APP.domain.SystemUser;
import Frolov_back.NAILS_WEB_APP.service.DTO.UserRegistrationRequestDto;
import Frolov_back.NAILS_WEB_APP.service.DTO.UserResponseDto;

import java.util.Optional;

public interface UserService {
    Optional<UserResponseDto> registerNewUser(UserRegistrationRequestDto userRegistrationRequestDto);
    Optional<SystemUser> findUserByEmail(String email);
    // ... другие методы для управления пользователями
}