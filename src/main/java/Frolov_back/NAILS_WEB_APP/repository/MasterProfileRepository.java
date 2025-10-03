package Frolov_back.NAILS_WEB_APP.repository;


import Frolov_back.NAILS_WEB_APP.domain.MasterProfile;
import Frolov_back.NAILS_WEB_APP.domain.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MasterProfileRepository extends JpaRepository<MasterProfile, Long> {
    Optional<MasterProfile> findBySystemUser(SystemUser systemUser);
    Optional<MasterProfile> findBySystemUserUserId(Long userId);

    List<MasterProfile> findAllByIsActiveTrue(); // Получить всех активных мастеров
}