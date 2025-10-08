package Frolov_back.NAILS_WEB_APP.repository;

import Frolov_back.NAILS_WEB_APP.domain.AdminProfile;
import Frolov_back.NAILS_WEB_APP.domain.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminProfileRepository extends JpaRepository<AdminProfile, Long> {
    Optional<AdminProfile> findBySystemUser(SystemUser systemUser);
    Optional<AdminProfile> findBySystemUserUserId(Long userId);
}