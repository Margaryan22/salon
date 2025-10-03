package Frolov_back.NAILS_WEB_APP.repository;

import Frolov_back.NAILS_WEB_APP.domain.SystemUser;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SystemUserRepository extends BaseRepository<SystemUser, Long> {
    Optional<SystemUser> findByEmail(String email);
    boolean existsByEmail(String email);
}
