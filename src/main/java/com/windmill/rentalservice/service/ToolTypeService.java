package com.windmill.rentalservice.service;

import com.windmill.rentalservice.dto.CreateToolTypeDto;
import com.windmill.rentalservice.dto.ToolTypeDto;
import com.windmill.rentalservice.mapper.ToolTypeMapper;
import com.windmill.rentalservice.model.ToolType;
import com.windmill.rentalservice.repository.ToolTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ToolTypeService {

    private ToolTypeRepository toolTypeRepository;
    private ToolTypeMapper toolTypeMapper;

    @Autowired
    public ToolTypeService(ToolTypeRepository toolTypeRepository, ToolTypeMapper toolTypeMapper) {
        this.toolTypeRepository = toolTypeRepository;
        this.toolTypeMapper = toolTypeMapper;
    }

    public ToolTypeDto createToolType(CreateToolTypeDto createToolTypeDto) {
        ToolType toolType = toolTypeMapper.toEntity(createToolTypeDto);
        ToolType savedToolType = toolTypeRepository.save(toolType);
        return toolTypeMapper.toDto(savedToolType);
    }

    public ToolTypeDto updateToolType(long id, ToolTypeDto toolTypeDto) {
        Optional<ToolType> existingToolTypeOpt = toolTypeRepository.findById(id);
        if (existingToolTypeOpt.isPresent()) {
            ToolType existingToolType = existingToolTypeOpt.get();
            existingToolType.setToolTypeName(toolTypeDto.getToolTypeName());
            existingToolType.setDailyCharge(toolTypeDto.getDailyCharge());
            existingToolType.setWeekdayCharge(toolTypeDto.isWeekdayCharge());
            existingToolType.setWeekendCharge(toolTypeDto.isWeekendCharge());
            existingToolType.setHolidayCharge(toolTypeDto.isHolidayCharge());
            ToolType updatedToolType = toolTypeRepository.save(existingToolType);
            return toolTypeMapper.toDto(updatedToolType);
        } else {
            throw new RuntimeException("ToolType not found with id: " + id);
        }
    }

    public void deleteToolType(long id) {
        if (toolTypeRepository.existsById(id)) {
            toolTypeRepository.deleteById(id);
        } else {
            throw new RuntimeException("ToolType not found with id: " + id);
        }
    }

    @Transactional(readOnly = true)
    public ToolTypeDto getToolTypeById(long id) {
        ToolType toolType = toolTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ToolType not found with id: " + id));
        return toolTypeMapper.toDto(toolType);
    }

    @Transactional(readOnly = true)
    public List<ToolTypeDto> getAllToolTypes() {
        return toolTypeRepository.findAll().stream()
                .map(toolTypeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ToolType getByToolTypeName(String toolTypeName) {
        List<ToolType> toolTypes = toolTypeRepository.findByToolTypeName(toolTypeName);
        if (toolTypes != null && toolTypes.size() > 0) {
            return toolTypes.get(0);
        } else {
            return null;
        }
    }
}
