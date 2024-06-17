package com.windmill.rentalservice.service;

import com.windmill.rentalservice.dto.CreateToolDto;
import com.windmill.rentalservice.dto.ToolDto;
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

    public ToolDto createTool(CreateToolDto createToolDto) {
        Tool tool = toolMapper.toEntity(createToolDto);
        Tool savedTool = toolRepository.save(tool);
        return toolMapper.toDto(savedTool);
    }

    public ToolDto updateTool(long toolId, ToolDto toolDto) {
        Optional<Tool> existingToolOpt = toolRepository.findById(toolId);
        if (existingToolOpt.isPresent()) {
            Tool existingTool = existingToolOpt.get();
            existingTool.setToolTypeId(toolDto.getToolTypeId());
            existingTool.setToolCode(toolDto.getToolCode());
            existingTool.setBrandId(toolDto.getBrandId());
            Tool updatedTool = toolRepository.save(existingTool);
            return toolMapper.toDto(updatedTool);
        } else {
            throw new RuntimeException(AppConstants.TOOL_NOT_FOUND+toolId);
        }
    }

    public void deleteTool(Long toolId) {
        if (toolRepository.existsById(toolId)) {
            toolRepository.deleteById(toolId);
        } else {
            throw new RuntimeException(AppConstants.TOOL_NOT_FOUND+toolId);
        }
    }

    @Transactional(readOnly = true)
    public ToolDto getToolById(Long toolId) {
        Tool tool = toolRepository.findById(toolId)
                .orElseThrow(() -> new RuntimeException(AppConstants.TOOL_NOT_FOUND+toolId));
        return toolMapper.toDto(tool);
    }

    @Transactional(readOnly = true)
    public List<ToolDto> getAllTools() {
        return toolRepository.findAll().stream()
                .map(toolMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Tool getByToolCode(String toolCode) {
        List<Tool> tools = toolRepository.findByToolCode(toolCode);
        if (tools != null && tools.size() > 0) {
            return tools.get(0);
        } else {
            return null;
        }
    }
}
