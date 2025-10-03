package Frolov_back.NAILS_WEB_APP.service.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

// --- DTO для запроса регистрации ---
public class UserRegistrationRequestDto {
    private String email;
    private String password; // Нужно хешировать в сервисе!
    private String firstName;
    private String lastName;
    private String phone;
    // Можно добавить поля для профиля (например, birthdate для клиента)

    // Геттеры и сеттеры
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}
