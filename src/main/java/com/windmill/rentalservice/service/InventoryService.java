package com.windmill.rentalservice.service;

import com.windmill.rentalservice.exception.RentalCreationException;
import com.windmill.rentalservice.model.ToolQuantity;
import com.windmill.rentalservice.repository.ToolQuantityRepository;
import com.windmill.rentalservice.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class for managing tool inventory.
 */
@Service
@Transactional
public class InventoryService {

    private final ToolQuantityRepository toolQuantityRepository;

    @Autowired
    public InventoryService(ToolQuantityRepository toolQuantityRepository) {
        this.toolQuantityRepository = toolQuantityRepository;
    }

    /**
     * Checks out a tool by decreasing its quantity.
     *
     * @param toolId the ID of the tool to check out
     * @return the updated ToolQuantity
     * @throws RuntimeException if the tool is not available
     */
    @Transactional(readOnly = false)
    public ToolQuantity checkoutTool(Long toolId) {
        ToolQuantity toolQuantity = toolQuantityRepository.findByToolId(toolId)
                .orElseThrow(() -> new RuntimeException(AppConstants.TOOL_NOT_FOUND + toolId));
        if (toolQuantity.getInStockCount() <= 0) {
            throw new RentalCreationException(AppConstants.TOOL_NOT_AVAILABLE);
        }
        toolQuantity.setInStockCount(toolQuantity.getInStockCount() - 1);
        return toolQuantityRepository.save(toolQuantity);
    }

    /**
     * Returns a tool by increasing its quantity.
     *
     * @param toolId the ID of the tool to return
     * @return the updated ToolQuantity
     */
    @Transactional(readOnly = false)
    public ToolQuantity returnTool(Long toolId) {
        ToolQuantity toolQuantity = toolQuantityRepository.findByToolId(toolId)
                .orElseThrow(() -> new RuntimeException(AppConstants.TOOL_NOT_FOUND + toolId));
        toolQuantity.setInStockCount(toolQuantity.getInStockCount() + 1);
        return toolQuantityRepository.save(toolQuantity);
    }
}
