package Frolov_back.NAILS_WEB_APP.service.DTO;

import java.math.BigDecimal;

public class MasterProfileDto {
    private Long userId; // Ссылка на SystemUser
    private String specialization;
    private Integer workExperience;
    private String description;
    private String photoUrl;
    private Boolean isActive;
    private BigDecimal hourlyRate;
    // ... геттеры/сеттеры
}
