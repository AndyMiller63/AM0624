package com.windmill.rentalservice.service;

import com.windmill.rentalservice.exception.RentalCreationException;
import com.windmill.rentalservice.model.ToolQuantity;
import com.windmill.rentalservice.repository.ToolQuantityRepository;
import com.windmill.rentalservice.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service class for handling inventory related operations.
 */
@Service
public class InventoryService {

    private final ToolQuantityRepository toolQuantityRepository;

    @Autowired
    public InventoryService(ToolQuantityRepository toolQuantityRepository) {
        this.toolQuantityRepository = toolQuantityRepository;
    }

    @Transactional
    public ToolQuantity checkoutTool(Long toolId) {
        Optional<ToolQuantity> toolQuantityOpt = toolQuantityRepository.findByToolId(toolId);
        if (toolQuantityOpt.isPresent()) {
            ToolQuantity toolQuantity = toolQuantityOpt.get();
            if (toolQuantity.getInStockCount() > 0) {
                toolQuantity.setInStockCount(toolQuantity.getInStockCount() - 1);
                return toolQuantityRepository.save(toolQuantity);
            } else {
                throw new RentalCreationException(AppConstants.TOOL_NOT_AVAILABLE+toolId);
            }
        } else {
            throw new RentalCreationException(AppConstants.TOOL_NOT_FOUND+toolId);
        }
    }

    @Transactional
    public ToolQuantity returnTool(Long toolId) {
        Optional<ToolQuantity> toolQuantityOpt = toolQuantityRepository.findByToolId(toolId);
        if (toolQuantityOpt.isPresent()) {
            ToolQuantity toolQuantity = toolQuantityOpt.get();
            toolQuantity.setInStockCount(toolQuantity.getInStockCount() + 1);
            return toolQuantityRepository.save(toolQuantity);
        } else {
            throw new RuntimeException(AppConstants.TOOL_NOT_FOUND+toolId);
        }
    }
}
