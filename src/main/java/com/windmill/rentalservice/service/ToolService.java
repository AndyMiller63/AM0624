package com.windmill.rentalservice.service;

import com.windmill.rentalservice.dto.ToolDto;
import com.windmill.rentalservice.dto.CreateToolDto;
import com.windmill.rentalservice.mapper.ToolMapper;
import com.windmill.rentalservice.model.Tool;
import com.windmill.rentalservice.repository.ToolRepository;
import com.windmill.rentalservice.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for managing tools.
 */
@Service
@Transactional
public class ToolService {

    private final ToolRepository toolRepository;
    private final ToolMapper toolMapper;

    @Autowired
    public ToolService(ToolRepository toolRepository, ToolMapper toolMapper) {
        this.toolRepository = toolRepository;
        this.toolMapper = toolMapper;
    }

    /**
     * Creates a new tool.
     *
     * @param createToolDto the CreateToolDto containing tool details
     * @return the created ToolDto
     */
    public ToolDto createTool(CreateToolDto createToolDto) {
        Tool tool = toolMapper.toEntity(createToolDto);
        Tool savedTool = toolRepository.save(tool);
        return toolMapper.toDto(savedTool);
    }

    /**
     * Updates an existing tool.
     *
     * @param id the ID of the tool to update
     * @param toolDto the ToolDto containing updated tool details
     * @return the updated ToolDto
     */
    public ToolDto updateTool(Long id, ToolDto toolDto) {
        Optional<Tool> existingToolOpt = toolRepository.findById(id);
        if (existingToolOpt.isPresent()) {
            Tool existingTool = existingToolOpt.get();
            existingTool.setToolCode(toolDto.getToolCode());
            existingTool.setToolTypeId(toolDto.getToolTypeId());
            existingTool.setBrandId(toolDto.getBrandId());
            Tool updatedTool = toolRepository.save(existingTool);
            return toolMapper.toDto(updatedTool);
        } else {
            throw new RuntimeException(AppConstants.TOOL_NOT_FOUND_ERROR + id);
        }
    }

    /**
     * Deletes a tool.
     *
     * @param id the ID of the tool to delete
     */
    public void deleteTool(Long id) {
        if (toolRepository.existsById(id)) {
            toolRepository.deleteById(id);
        } else {
            throw new RuntimeException(AppConstants.TOOL_NOT_FOUND_ERROR + id);
        }
    }

    /**
     * Retrieves a tool by its ID.
     *
     * @param id the ID of the tool
     * @return the ToolDto
     */
    @Transactional(readOnly = true)
    public ToolDto getToolById(Long id) {
        Tool tool = toolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(AppConstants.TOOL_NOT_FOUND_ERROR + id));
        return toolMapper.toDto(tool);
    }

    /**
     * Retrieves all tools.
     *
     * @return a list of ToolDto
     */
    @Transactional(readOnly = true)
    public List<ToolDto> getAllTools() {
        return toolRepository.findAll().stream()
                .map(toolMapper::toDto)
                .collect(Collectors.toList());
    }
}
