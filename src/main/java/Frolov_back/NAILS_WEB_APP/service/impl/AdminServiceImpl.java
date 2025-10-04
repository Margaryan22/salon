package Frolov_back.NAILS_WEB_APP.service.impl;

import Frolov_back.NAILS_WEB_APP.domain.AdminProfile;
import Frolov_back.NAILS_WEB_APP.domain.SystemUser;
import Frolov_back.NAILS_WEB_APP.repository.AdminProfileRepository;
import Frolov_back.NAILS_WEB_APP.repository.SystemUserRepository;
import Frolov_back.NAILS_WEB_APP.service.AdminService;
import Frolov_back.NAILS_WEB_APP.service.DTO.AdminProfileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    private final SystemUserRepository userRepository;
    private final AdminProfileRepository adminProfileRepository;

    @Autowired
    public AdminServiceImpl(SystemUserRepository userRepository, AdminProfileRepository adminProfileRepository) {
        this.userRepository = userRepository;
        this.adminProfileRepository = adminProfileRepository;
    }

    @Override
    @Transactional
    public Optional<AdminProfile> createAdminProfile(Long userId, Integer permissionsLevel) {
        Optional<SystemUser> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            return Optional.empty(); // Пользователь не найден
        }

        SystemUser user = userOptional.get();
        // Проверка, что у пользователя еще нет админ-профиля (если нужно)
        if (adminProfileRepository.findBySystemUser(user).isPresent()) {
            // Можно выбросить исключение или вернуть Optional.empty()
            throw new IllegalArgumentException("User already has an admin profile.");
        }

        AdminProfile adminProfile = new AdminProfile(user, permissionsLevel);
        return Optional.of(adminProfileRepository.save(adminProfile));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AdminProfileDto> getAdminProfileByUserId(Long userId) {
        return adminProfileRepository.findBySystemUserUserId(userId)
                .map(this::convertToDto); // Преобразовать сущность в DTO
    }

    private AdminProfileDto convertToDto(AdminProfile adminProfile) {
        // Логика преобразования AdminProfile в AdminProfileDto
        AdminProfileDto dto = new AdminProfileDto();
        dto.setAdminId(adminProfile.getAdminId());
        dto.setUserId(adminProfile.getSystemUser().getUserId());
        dto.setPermissionsLevel(adminProfile.getPermissionsLevel());
        return dto;
    }

    // ... другие методы
}