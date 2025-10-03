package Frolov_back.NAILS_WEB_APP.service;

import Frolov_back.NAILS_WEB_APP.service.DTO.UserDto; // Пример DTO
import Frolov_back.NAILS_WEB_APP.service.DTO.UserProfileDto; // Пример DTO
import Frolov_back.NAILS_WEB_APP.domain.SystemUser;

import java.util.Optional;

public interface UserService {
    Optional<UserDto> registerNewUser(UserProfileDto profileDto);
    Optional<SystemUser> findUserByEmail(String email);
    // ... другие методы для управления пользователями
}