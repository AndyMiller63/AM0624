package com.windmill.rentalservice.repository;

import com.windmill.rentalservice.model.Setting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SettingsRepository extends JpaRepository<Setting, Long> {
    Optional<Setting> findBySettingKey(String key);
}
