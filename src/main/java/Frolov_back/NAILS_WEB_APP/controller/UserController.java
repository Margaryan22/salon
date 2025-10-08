// TODO 04.10.2025: проработать полную цепочку регистрации пользователя (как админа) и вернуть объект зарегистрированного пользователя (JSON)
// TODO 08.10.2025: Кривая реализация готова -> далее нужно подойти более правильно



package Frolov_back.NAILS_WEB_APP.controller;

import Frolov_back.NAILS_WEB_APP.service.UserService;
import Frolov_back.NAILS_WEB_APP.service.DTO.UserRegistrationRequestDto; // DTO для регистрации
import Frolov_back.NAILS_WEB_APP.service.DTO.UserResponseDto; // DTO для ответа
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users") // Версионирование API
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register/user")
    public ResponseEntity<UserResponseDto> registerUser(@RequestBody UserRegistrationRequestDto requestDto) {
        // Тут должна быть валидация requestDto
        //userService.registerNewUser() должен возвращать Optional<UserResponseDto>
        return userService.registerNewUser(requestDto)
                .map(userDto -> ResponseEntity.status(HttpStatus.CREATED).body(userDto))
                .orElseGet(() -> ResponseEntity.badRequest().build()); // Например, если email уже занят
    }

    // ... другие эндпоинты для пользователей

    @PostMapping("/register/admin")
    public ResponseEntity<?> registerAdmin(@RequestBody UserRegistrationRequestDto requestDto) {
        // Базовая валидация
        if (requestDto.getEmail() == null || requestDto.getEmail().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Email is required");
        }
        if (requestDto.getPassword() == null || requestDto.getPassword().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Password is required");
        }
        if (requestDto.getFirstName() == null || requestDto.getFirstName().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("First name is required");
        }
        if (requestDto.getLastName() == null || requestDto.getLastName().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Last name is required");
        }

        Optional<UserResponseDto> result = userService.registerNewUser(requestDto);

        if (result.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(result.get());
        } else {
            return ResponseEntity.badRequest().body("User with this email already exists or invalid data");
        }
    }

    // GET - Получить всех пользователей
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // GET - Получить пользователя по ID
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        // Пока заглушка - можно реализовать позже
        return ResponseEntity.notFound().build();
    }

    // GET - Поиск пользователя по email
    @GetMapping("/search")
    public ResponseEntity<UserResponseDto> getUserByEmail(@RequestParam String email) {
        // Пока заглушка - можно реализовать позже
        return ResponseEntity.notFound().build();
    }

}