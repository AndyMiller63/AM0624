package com.windmill.rentalservice.service;

import com.windmill.rentalservice.dto.ToolQuantityDto;
import com.windmill.rentalservice.mapper.ToolQuantityMapper;
import com.windmill.rentalservice.repository.ToolQuantityRepository;
import com.windmill.rentalservice.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ToolQuantityService {

    private final ToolQuantityRepository toolQuantityRepository;
    private final ToolQuantityMapper toolQuantityMapper;

    @Autowired
    public ToolQuantityService(ToolQuantityRepository toolQuantityRepository, ToolQuantityMapper toolQuantityMapper) {
        this.toolQuantityRepository = toolQuantityRepository;
        this.toolQuantityMapper = toolQuantityMapper;
    }

    public ToolQuantityDto getToolQuantityByToolId(Long toolId) {
        return toolQuantityRepository.findByToolId(toolId)
                .map(toolQuantityMapper::toDto)
                .orElseThrow(() -> new RuntimeException(AppConstants.TOOL_QUANTITY_NOT_FOUND + toolId));
    }

    public List<ToolQuantityDto> getAllToolQuantities() {
        return toolQuantityRepository.findAll().stream()
                .map(toolQuantityMapper::toDto)
                .collect(Collectors.toList());
    }
}
