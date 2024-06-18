package com.windmill.rentalservice.service;

import com.windmill.rentalservice.dto.ToolQuantityDto;
import com.windmill.rentalservice.mapper.ToolQuantityMapper;
import com.windmill.rentalservice.model.ToolQuantity;
import com.windmill.rentalservice.repository.ToolQuantityRepository;
import com.windmill.rentalservice.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for managing tool quantities.
 */
@Service
@Transactional
public class ToolQuantityService {

    private final ToolQuantityRepository toolQuantityRepository;
    private final ToolQuantityMapper toolQuantityMapper;

    @Autowired
    public ToolQuantityService(ToolQuantityRepository toolQuantityRepository, ToolQuantityMapper toolQuantityMapper) {
        this.toolQuantityRepository = toolQuantityRepository;
        this.toolQuantityMapper = toolQuantityMapper;
    }

    /**
     * Retrieves all tool quantities.
     *
     * @return a list of ToolQuantityDto
     */
    @Transactional(readOnly = true)
    public List<ToolQuantityDto> getAllToolQuantities() {
        return toolQuantityRepository.findAll().stream()
                .map(toolQuantityMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves the quantity of a tool by its ID.
     *
     * @param toolId the ID of the tool
     * @return the ToolQuantityDto
     */
    @Transactional(readOnly = true)
    public ToolQuantityDto getToolQuantityByToolId(Long toolId) {
        ToolQuantity toolQuantity = toolQuantityRepository.findByToolId(toolId)
                .orElseThrow(() -> new RuntimeException(AppConstants.TOOL_QUANTITY_NOT_FOUND + toolId));
        return toolQuantityMapper.toDto(toolQuantity);
    }
}
