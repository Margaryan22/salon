package Frolov_back.NAILS_WEB_APP.controller;

import Frolov_back.NAILS_WEB_APP.service.UserService;
import Frolov_back.NAILS_WEB_APP.service.DTO.UserRegistrationRequestDto; // DTO для регистрации
import Frolov_back.NAILS_WEB_APP.service.DTO.UserResponseDto; // DTO для ответа
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users") // Версионирование API
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> registerUser(@RequestBody UserRegistrationRequestDto requestDto) {
        // Тут должна быть валидация requestDto
        //userService.registerNewUser() должен возвращать Optional<UserResponseDto>
        return userService.registerNewUser(requestDto)
                .map(userDto -> ResponseEntity.status(HttpStatus.CREATED).body(userDto))
                .orElseGet(() -> ResponseEntity.badRequest().build()); // Например, если email уже занят
    }

    // ... другие эндпоинты для пользователей
}