package Frolov_back.NAILS_WEB_APP.domain;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "client_profiles")
public class ClientProfile {
    @Id
    // Ключ для client_profiles - это user_id из system_users
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Если user_id - внешний ключ, то это может быть не нужно
    private Long userId; // Так как user_id здесь является первичным ключом

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private SystemUser systemUser; // Связь обратно к SystemUser

    @Column(name = "birthdate")
    private LocalDate birthdate;

    @Column(name = "bonus_points", columnDefinition = "INTEGER DEFAULT 0")
    private Integer bonusPoints;

    @Column(columnDefinition = "TEXT") // предпочтения клиента
    private String preferences;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @Column(name = "registration_source", length = 100) // откуда пришел клиент
    private String registrationSource;

    @Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    // --- Конструкторы, геттеры, сеттеры, equals/hashCode ---
    public ClientProfile() {}

    public ClientProfile(SystemUser systemUser, LocalDate birthdate) {
        this.systemUser = systemUser;
        this.birthdate = birthdate;
        this.bonusPoints = 0; // По умолчанию 0
    }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public SystemUser getSystemUser() { return systemUser; }
    public void setSystemUser(SystemUser systemUser) { this.systemUser = systemUser; }
    public LocalDate getBirthdate() { return birthdate; }
    public void setBirthdate(LocalDate birthdate) { this.birthdate = birthdate; }
    public Integer getBonusPoints() { return bonusPoints; }
    public void setBonusPoints(Integer bonusPoints) { this.bonusPoints = bonusPoints; }
    public String getPreferences() { return preferences; }
    public void setPreferences(String preferences) { this.preferences = preferences; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    public String getRegistrationSource() { return registrationSource; }
    public void setRegistrationSource(String registrationSource) { this.registrationSource = registrationSource; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientProfile that = (ClientProfile) o;
        return Objects.equals(userId, that.userId); // userId здесь выступает как PK
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}