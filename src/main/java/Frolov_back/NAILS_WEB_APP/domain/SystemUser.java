package Frolov_back.NAILS_WEB_APP.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

// --- Пример сущности SystemUser ---
@Entity
@Table(name = "system_users")
public class SystemUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId; // Изменено с user_id для соответствия Java-стилю

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    @Column(nullable = false, length = 100)
    private String firstName;

    @Column(nullable = false, length = 100)
    private String lastName;

    @Column(length = 20)
    private String phone;

    @Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    // --- Связи ---
    // Связь один-к-одному с профилями.
    // Удалить или настроить каскадное удаление/обновление осторожно!
    @OneToOne(mappedBy = "systemUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private AdminProfile adminProfile;

    @OneToOne(mappedBy = "systemUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private MasterProfile masterProfile;

    @OneToOne(mappedBy = "systemUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private ClientProfile clientProfile;

    // --- Конструкторы, геттеры, сеттеры, equals/hashCode ---
    public SystemUser() {}

    // Пример конструктора
    public SystemUser(String email, String passwordHash, String firstName, String lastName, String phone) {
        this.email = email;
        this.passwordHash = passwordHash;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public AdminProfile getAdminProfile() { return adminProfile; }
    public void setAdminProfile(AdminProfile adminProfile) {
        if (adminProfile != null) {
            adminProfile.setSystemUser(this);
        }
        this.adminProfile = adminProfile;
    }

    public MasterProfile getMasterProfile() { return masterProfile; }
    public void setMasterProfile(MasterProfile masterProfile) {
        if (masterProfile != null) {
            masterProfile.setSystemUser(this);
        }
        this.masterProfile = masterProfile;
    }

    public ClientProfile getClientProfile() { return clientProfile; }
    public void setClientProfile(ClientProfile clientProfile) {
        if (clientProfile != null) {
            clientProfile.setSystemUser(this);
        }
        this.clientProfile = clientProfile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SystemUser that = (SystemUser) o;
        return Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}