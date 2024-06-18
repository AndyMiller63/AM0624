package com.windmill.rentalservice.service;

import com.windmill.rentalservice.dto.CreateToolTypeDto;
import com.windmill.rentalservice.dto.ToolTypeDto;
import com.windmill.rentalservice.mapper.ToolTypeMapper;
import com.windmill.rentalservice.model.ToolType;
import com.windmill.rentalservice.repository.ToolTypeRepository;
import com.windmill.rentalservice.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for managing tool types.
 */
@Service
@Transactional
public class ToolTypeService {

    private final ToolTypeRepository toolTypeRepository;
    private final ToolTypeMapper toolTypeMapper;

    @Autowired
    public ToolTypeService(ToolTypeRepository toolTypeRepository, ToolTypeMapper toolTypeMapper) {
        this.toolTypeRepository = toolTypeRepository;
        this.toolTypeMapper = toolTypeMapper;
    }

    /**
     * Creates a new tool type.
     *
     * @param createToolTypeDto the CreateToolTypeDto containing tool type details
     * @return the created ToolTypeDto
     */
    public ToolTypeDto createToolType(CreateToolTypeDto createToolTypeDto) {
        ToolType toolType = toolTypeMapper.toEntity(createToolTypeDto);
        ToolType savedToolType = toolTypeRepository.save(toolType);
        return toolTypeMapper.toDto(savedToolType);
    }

    /**
     * Updates an existing tool type.
     *
     * @param id the ID of the tool type to update
     * @param toolTypeDto the ToolTypeDto containing updated tool type details
     * @return the updated ToolTypeDto
     */
    public ToolTypeDto updateToolType(Long id, ToolTypeDto toolTypeDto) {
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
            throw new RuntimeException(AppConstants.TOOL_TYPE_NOT_FOUND + id);
        }
    }

    /**
     * Deletes a tool type.
     *
     * @param id the ID of the tool type to delete
     */
    public void deleteToolType(Long id) {
        if (toolTypeRepository.existsById(id)) {
            toolTypeRepository.deleteById(id);
        } else {
            throw new RuntimeException(AppConstants.TOOL_TYPE_NOT_FOUND + id);
        }
    }

    /**
     * Retrieves a tool type by its ID.
     *
     * @param id the ID of the tool type
     * @return the ToolTypeDto
     */
    @Transactional(readOnly = true)
    public ToolTypeDto getToolTypeById(Long id) {
        ToolType toolType = toolTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(AppConstants.TOOL_TYPE_NOT_FOUND + id));
        return toolTypeMapper.toDto(toolType);
    }

    /**
     * Retrieves all tool types.
     *
     * @return a list of ToolTypeDto
     */
    @Transactional(readOnly = true)
    public List<ToolTypeDto> getAllToolTypes() {
        return toolTypeRepository.findAll().stream()
                .map(toolTypeMapper::toDto)
                .collect(Collectors.toList());
    }
}
