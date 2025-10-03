package Frolov_back.NAILS_WEB_APP.service;

import Frolov_back.NAILS_WEB_APP.service.DTO.MasterProfileDto;
import Frolov_back.NAILS_WEB_APP.service.DTO.MasterListItemDto; // DTO для списка мастеров
import Frolov_back.NAILS_WEB_APP.domain.MasterProfile; // Может понадобиться для возврата сущностей
import Frolov_back.NAILS_WEB_APP.domain.SystemUser;

import java.util.List;
import java.util.Optional;

public interface MasterService {
    Optional<MasterProfile> findBySystemUser(SystemUser systemUser);
    Optional<MasterProfile> findByUserId(Long userId);
    List<MasterListItemDto> getAllActiveMasters();
    MasterProfileDto getMasterDetails(Long userId);
    MasterProfileDto createMasterProfile(Long userId, MasterProfileDto profileDto);
    MasterProfileDto updateMasterProfile(Long userId, MasterProfileDto profileDto);
    // ... другие методы
}