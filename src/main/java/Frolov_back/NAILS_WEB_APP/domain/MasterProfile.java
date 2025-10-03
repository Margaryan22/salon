package Frolov_back.NAILS_WEB_APP.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "master_profiles")
public class MasterProfile {
    @Id
    // Ключ для master_profiles - это user_id из system_users
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Если user_id - внешний ключ, то это может быть не нужно
    private Long userId; // Так как user_id здесь является первичным ключом

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private SystemUser systemUser; // Связь обратно к SystemUser

    @Column(columnDefinition = "TEXT")
    private String specialization;

    @Column(name = "work_experience") // стаж в месяцах
    private Integer workExperience;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "photo_url", length = 500)
    private String photoUrl;

    @Column(name = "is_active", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean isActive;

    @Column(name = "hourly_rate", precision = 10, scale = 2)
    private BigDecimal hourlyRate;

    @Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    // --- Конструкторы, геттеры, сеттеры, equals/hashCode ---
    public MasterProfile() {}

    public MasterProfile(SystemUser systemUser, String specialization, Integer workExperience, BigDecimal hourlyRate) {
        this.systemUser = systemUser;
        this.specialization = specialization;
        this.workExperience = workExperience;
        this.hourlyRate = hourlyRate;
        this.isActive = true; // По умолчанию активен
    }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public SystemUser getSystemUser() { return systemUser; }
    public void setSystemUser(SystemUser systemUser) { this.systemUser = systemUser; }
    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }
    public Integer getWorkExperience() { return workExperience; }
    public void setWorkExperience(Integer workExperience) { this.workExperience = workExperience; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getPhotoUrl() { return photoUrl; }
    public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }
    public Boolean getActive() { return isActive; }
    public void setActive(Boolean active) { isActive = active; }
    public BigDecimal getHourlyRate() { return hourlyRate; }
    public void setHourlyRate(BigDecimal hourlyRate) { this.hourlyRate = hourlyRate; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MasterProfile that = (MasterProfile) o;
        return Objects.equals(userId, that.userId); // userId здесь выступает как PK
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}