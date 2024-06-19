package com.windmill.rentalservice.service;

import com.windmill.rentalservice.model.Setting;
import com.windmill.rentalservice.repository.SettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SettingService {

    private final SettingsRepository settingsRepository;

    @Autowired
    public SettingService(SettingsRepository settingsRepository) {
        this.settingsRepository = settingsRepository;
    }

    public Setting createOrUpdateSetting(Setting settings) {
        Optional<Setting> existingSettingsOpt = settingsRepository.findBySettingKey(settings.getSettingKey());
        if (existingSettingsOpt.isPresent()) {
            Setting existingSettings = existingSettingsOpt.get();
            existingSettings.setSettingValue(settings.getSettingValue());
            return settingsRepository.save(existingSettings);
        } else {
            return settingsRepository.save(settings);
        }
    }

    public void deleteSetting(String settingKey) {
        Optional<Setting> existingSettingsOpt = settingsRepository.findBySettingKey(settingKey);
        existingSettingsOpt.ifPresent(settingsRepository::delete);
    }

    public Setting getSetting(String settingKey) {
        return settingsRepository.findBySettingKey(settingKey)
                .orElseThrow(() -> new RuntimeException("Setting not found for key: " + settingKey));
    }

    public List<Setting> getAllSettings() {
        return settingsRepository.findAll();
    }

    public String getCronExpression(String settingKey) {
        return settingsRepository.findBySettingKey(settingKey)
                .orElseThrow(() -> new RuntimeException("Setting not found for key: " + settingKey))
                .getSettingValue();
    }
}
