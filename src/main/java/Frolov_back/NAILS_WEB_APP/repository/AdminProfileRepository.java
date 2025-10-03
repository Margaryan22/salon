package Frolov_back.NAILS_WEB_APP.repository;

import Frolov_back.NAILS_WEB_APP.domain.AdminProfile;
import Frolov_back.NAILS_WEB_APP.domain.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Если AdminProfile связан с SystemUser один-к-одному,
// то часто удобно иметь репозиторий, который ищет по SystemUser
@Repository
public interface AdminProfileRepository extends JpaRepository<AdminProfile, Long> {
    // Находит профиль администратора, связанный с конкретным SystemUser
    Optional<AdminProfile> findBySystemUser(SystemUser systemUser);
    // Или по userId, если это требуется
    Optional<AdminProfile> findBySystemUserUserId(Long userId);
}