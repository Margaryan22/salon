package Frolov_back.NAILS_WEB_APP.service.impl;

import Frolov_back.NAILS_WEB_APP.domain.AdminProfile;
import Frolov_back.NAILS_WEB_APP.domain.SystemUser;
import Frolov_back.NAILS_WEB_APP.repository.AdminProfileRepository;
import Frolov_back.NAILS_WEB_APP.repository.SystemUserRepository;
import Frolov_back.NAILS_WEB_APP.service.DTO.UserDto;
import Frolov_back.NAILS_WEB_APP.service.DTO.UserProfileDto;
import Frolov_back.NAILS_WEB_APP.service.DTO.UserRegistrationRequestDto;
import Frolov_back.NAILS_WEB_APP.service.DTO.UserResponseDto;
import Frolov_back.NAILS_WEB_APP.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final SystemUserRepository systemUserRepository;
    private final AdminProfileRepository adminProfileRepository;

    public UserServiceImpl(SystemUserRepository systemUserRepository,
                           AdminProfileRepository adminProfileRepository) {
        this.systemUserRepository = systemUserRepository;
        this.adminProfileRepository = adminProfileRepository;
    }

    @Override
    @Transactional
    public Optional<UserResponseDto> registerNewUser(UserRegistrationRequestDto requestDto) {
        // Проверяем, существует ли пользователь с таким email
        if (systemUserRepository.existsByEmail(requestDto.getEmail())) {
            return Optional.empty(); // Email уже занят
        }

        // Валидация обязательных полей
        if (requestDto.getEmail() == null || requestDto.getEmail().trim().isEmpty() ||
                requestDto.getPassword() == null || requestDto.getPassword().trim().isEmpty() ||
                requestDto.getFirstName() == null || requestDto.getFirstName().trim().isEmpty() ||
                requestDto.getLastName() == null || requestDto.getLastName().trim().isEmpty()) {
            return Optional.empty(); // Невалидные данные
        }

        // Создаем нового пользователя
        SystemUser newUser = new SystemUser();
        newUser.setEmail(requestDto.getEmail().trim().toLowerCase());
        newUser.setPasswordHash(requestDto.getPassword()); // Без хеширования временно
        newUser.setFirstName(requestDto.getFirstName().trim());
        newUser.setLastName(requestDto.getLastName().trim());
        newUser.setPhone(requestDto.getPhone() != null ? requestDto.getPhone().trim() : null);
        newUser.setCreatedAt(LocalDateTime.now());

        // Сохраняем пользователя
        SystemUser savedUser = systemUserRepository.save(newUser);

        // Создаем профиль админа
        AdminProfile adminProfile = new AdminProfile(savedUser, 1); // Уровень прав 1 - обычный админ
        adminProfileRepository.save(adminProfile);

        // Устанавливаем связь
        savedUser.setAdminProfile(adminProfile);

        // Создаем DTO для ответа
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setUserId(savedUser.getUserId());
        responseDto.setEmail(savedUser.getEmail());
        responseDto.setFirstName(savedUser.getFirstName());
        responseDto.setLastName(savedUser.getLastName());
        responseDto.setPhone(savedUser.getPhone());
        responseDto.setCreatedAt(savedUser.getCreatedAt());

        return Optional.of(responseDto);
    }

    @Override
    public Optional<SystemUser> findUserByEmail(String email) {
        return systemUserRepository.findByEmail(email);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        List<SystemUser> users = systemUserRepository.findAll();

        return users.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // Вспомогательный метод для конвертации SystemUser в UserResponseDto
    private UserResponseDto convertToDto(SystemUser user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setUserId(user.getUserId());
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setPhone(user.getPhone());
        dto.setCreatedAt(user.getCreatedAt());

        // Определяем тип пользователя
        String userType = determineUserType(user);
        dto.setUserType(userType);

        return dto;
    }

    // Метод для определения типа пользователя
    private String determineUserType(SystemUser user) {
        if (user.getAdminProfile() != null) {
            return "ADMIN";
        } else if (user.getMasterProfile() != null) {
            return "MASTER";
        } else if (user.getClientProfile() != null) {
            return "CLIENT";
        } else {
            return "UNKNOWN";
        }
    }
}
