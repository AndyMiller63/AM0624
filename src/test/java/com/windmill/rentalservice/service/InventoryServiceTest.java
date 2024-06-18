package com.windmill.rentalservice.service;

import com.windmill.rentalservice.model.ToolQuantity;
import com.windmill.rentalservice.repository.ToolQuantityRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class InventoryServiceTest {

    @Mock
    private ToolQuantityRepository toolQuantityRepository;

    @InjectMocks
    private InventoryService inventoryService;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }
    @Test
    void testCheckoutTool() {
        Long toolId = 1L;
        ToolQuantity toolQuantity = new ToolQuantity();
        toolQuantity.setToolId(toolId);
        toolQuantity.setInStockCount(10);

        when(toolQuantityRepository.findByToolId(toolId)).thenReturn(Optional.of(toolQuantity));
        when(toolQuantityRepository.save(any(ToolQuantity.class))).thenReturn(toolQuantity);

        ToolQuantity updatedToolQuantity = inventoryService.checkoutTool(toolId);

        assertNotNull(updatedToolQuantity);
        assertEquals(9, updatedToolQuantity.getInStockCount());
        verify(toolQuantityRepository, times(1)).save(toolQuantity);
    }

    @Test
    void testReturnTool() {
        Long toolId = 1L;
        ToolQuantity toolQuantity = new ToolQuantity();
        toolQuantity.setToolId(toolId);
        toolQuantity.setInStockCount(9);

        when(toolQuantityRepository.findByToolId(toolId)).thenReturn(Optional.of(toolQuantity));
        when(toolQuantityRepository.save(any(ToolQuantity.class))).thenReturn(toolQuantity);

        ToolQuantity updatedToolQuantity = inventoryService.returnTool(toolId);

        assertNotNull(updatedToolQuantity);
        assertEquals(10, updatedToolQuantity.getInStockCount());
        verify(toolQuantityRepository, times(1)).save(toolQuantity);
    }
}
