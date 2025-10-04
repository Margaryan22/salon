package Frolov_back.NAILS_WEB_APP.service;

import Frolov_back.NAILS_WEB_APP.domain.AdminProfile;
import Frolov_back.NAILS_WEB_APP.service.DTO.AdminProfileDto;

import java.util.Optional;

public interface AdminService {
    public Optional<AdminProfile> createAdminProfile(Long userId, Integer permissionsLevel);
    public Optional<AdminProfileDto> getAdminProfileByUserId(Long userId);
}
