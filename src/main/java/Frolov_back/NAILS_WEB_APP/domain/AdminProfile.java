package Frolov_back.NAILS_WEB_APP.domain;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "admin_profiles")
public class AdminProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;

    // Важно: user_id должен быть уникальным и внешним ключом
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private SystemUser systemUser; // Связь обратно к SystemUser

    @Column(nullable = false, columnDefinition = "INTEGER DEFAULT 1")
    private Integer permissionsLevel; // 1-обычный, 2-супер-админ

    // --- Конструкторы, геттеры, сеттеры, equals/hashCode ---
    public AdminProfile() {}

    public AdminProfile(SystemUser systemUser, Integer permissionsLevel) {
        this.systemUser = systemUser;
        this.permissionsLevel = permissionsLevel;
    }

    public Long getAdminId() { return adminId; }
    public void setAdminId(Long adminId) { this.adminId = adminId; }
    public SystemUser getSystemUser() { return systemUser; }
    public void setSystemUser(SystemUser systemUser) { this.systemUser = systemUser; }
    public Integer getPermissionsLevel() { return permissionsLevel; }
    public void setPermissionsLevel(Integer permissionsLevel) { this.permissionsLevel = permissionsLevel; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminProfile that = (AdminProfile) o;
        return Objects.equals(adminId, that.adminId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adminId);
    }
}