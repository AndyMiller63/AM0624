package com.windmill.rentalservice.service;

import com.windmill.rentalservice.dto.CreateRentalDto;
import com.windmill.rentalservice.dto.RentalDto;
import com.windmill.rentalservice.dto.ToolDto;
import com.windmill.rentalservice.dto.ToolTypeDto;
import com.windmill.rentalservice.exception.RentalCreationException;
import com.windmill.rentalservice.mapper.RentalMapper;
import com.windmill.rentalservice.model.*;
import com.windmill.rentalservice.repository.*;
import com.windmill.rentalservice.util.AppConstants;
import com.windmill.rentalservice.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.String.format;

/**
 * Service class for handling rental operations.
 */
@Service
@Transactional
public class RentalService {

    private final ToolRepository toolRepository;
    private final ToolService toolService;
    private final ToolTypeService toolTypeService;
    private final ToolTypeRepository toolTypeRepository;
    private final BrandRepository brandRepository;
    private final InventoryService inventoryService;
    private final RentalRepository rentalRepository;
    private final RentalMapper rentalMapper;
    private final ToolQuantityRepository toolQuantityRepository;
    private final HolidayRepository holidayRepository;
    private final HolidayService holidayService;
    private final CustomerRepository customerRepository;


    /**
     * Constructor for RentalService.
     *
     * @param toolService             the ToolService
     * @param toolTypeService         the ToolTypeService
     * @param toolRepository          the ToolRepository
     * @param toolTypeRepository      the ToolTypeRepository
     * @param brandRepository         the BrandRepository
     * @param inventoryService        the InventoryService
     * @param rentalRepository        the RentalRepository
     * @param rentalMapper            the RentalMapper
     * @param toolQuantityRepository  the ToolQuantityRepository
     * @param holidayRepository       the HolidayRepository
     * @param holidayService          the HolidayService
     */
    @Autowired
    public RentalService(ToolService toolService, ToolTypeService toolTypeService,
                         ToolRepository toolRepository, ToolTypeRepository toolTypeRepository,
                         BrandRepository brandRepository, InventoryService inventoryService,
                         RentalRepository rentalRepository, RentalMapper rentalMapper,
                         ToolQuantityRepository toolQuantityRepository,
                         HolidayRepository holidayRepository, HolidayService holidayService,
                         CustomerRepository customerRepository) {
        this.toolService = toolService;
        this.toolTypeService = toolTypeService;
        this.toolRepository = toolRepository;
        this.toolTypeRepository = toolTypeRepository;
        this.brandRepository = brandRepository;
        this.inventoryService = inventoryService;
        this.rentalRepository = rentalRepository;
        this.rentalMapper = rentalMapper;
        this.toolQuantityRepository = toolQuantityRepository;
        this.holidayRepository = holidayRepository;
        this.holidayService = holidayService;
        this.customerRepository = customerRepository;
    }

    /**
     * Creates a new rental.
     *
     * @param createRentalDto the DTO containing rental creation data
     * @return the created RentalDto
     * @throws Exception if an error occurs during rental creation
     */
    public RentalDto createRental(CreateRentalDto createRentalDto) throws Exception {
        Rental rental = checkout(createRentalDto.getToolId(), createRentalDto.getCustomerId(),
                createRentalDto.getRentalDays(), createRentalDto.getDiscountPercent(),
                createRentalDto.getCheckoutDateString());
        Rental savedRental = rentalRepository.save(rental);
        return rentalMapper.toDto(savedRental);
    }

    /**
     * Updates an existing rental.
     *
     * @param id         the rental ID
     * @param rentalDto  the DTO containing rental data
     * @return the updated RentalDto
     */
    public RentalDto updateRental(Long id, RentalDto rentalDto) {
        Optional<Rental> existingRentalOpt = rentalRepository.findById(id);
        if (existingRentalOpt.isPresent()) {
            Rental existingRental = existingRentalOpt.get();
            existingRental.setToolId(rentalDto.getToolId());
            existingRental.setCustomerId(rentalDto.getCustomerId());
            existingRental.setCheckoutDate(Utility.stringToDate(rentalDto.getCheckoutDate()));
            Rental updatedRental = rentalRepository.save(existingRental);
            return rentalMapper.toDto(updatedRental);
        } else {
            throw new RuntimeException(AppConstants.RENTAL_NOT_FOUND_WITH_ID + id);
        }
    }

    /**
     * Deletes a rental by ID.
     *
     * @param id the rental ID
     */
    public void deleteRental(Long id) {
        if (rentalRepository.existsById(id)) {
            rentalRepository.deleteById(id);
        } else {
            throw new RuntimeException(AppConstants.RENTAL_NOT_FOUND_WITH_ID + id);
        }
    }

    /**
     * Retrieves a rental by ID.
     *
     * @param id the rental ID
     * @return the RentalDto
     */
    @Transactional(readOnly = true)
    public RentalDto getRentalById(Long id) {
        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(AppConstants.RENTAL_NOT_FOUND_WITH_ID + id));
        return rentalMapper.toDto(rental);
    }

    /**
     * Retrieves all rentals.
     *
     * @return a list of RentalDto
     */
    @Transactional(readOnly = true)
    public List<RentalDto> getAllRentals() {
        return rentalRepository.findAll().stream()
                .map(rentalMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Finds rentals by tool ID.
     *
     * @param toolId the tool ID
     * @return a list of RentalDto
     */
    @Transactional(readOnly = true)
    public List<RentalDto> findRentalsByToolId(Long toolId) {
        return rentalRepository.findByToolId(toolId).stream()
                .map(rentalMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Finds rentals by tool code.
     *
     * @param toolCode the tool code
     * @return a list of RentalDto
     */
    @Transactional(readOnly = true)
    public List<RentalDto> findRentalsByToolCode(String toolCode) {
        return rentalRepository.findRentalsByToolCode(toolCode).stream()
                .map(rentalMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Finds rentals by customer ID.
     *
     * @param customerId the customer ID
     * @return a list of RentalDto
     */
    @Transactional(readOnly = true)
    public List<RentalDto> findRentalsByCustomerId(Long customerId) {
        return rentalRepository.findByCustomerId(customerId).stream()
                .map(rentalMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Finds rentals by customer name.
     *
     * @param name the customer name
     * @return a list of RentalDto
     */
    @Transactional(readOnly = true)
    public List<RentalDto> findRentalsByCustomerName(String name) {
        return rentalRepository.findRentalsByCustomerName(name).stream()
                .map(rentalMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Finds rentals by customer contact.
     *
     * @param contact the customer contact
     * @return a list of RentalDto
     */
    @Transactional(readOnly = true)
    public List<RentalDto> findRentalsByCustomerContact(String contact) {
        return rentalRepository.findRentalsByCustomerContact(contact).stream()
                .map(rentalMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Finds rentals by rental date.
     *
     * @param checkoutDate the rental date
     * @return a list of RentalDto
     */
    @Transactional(readOnly = true)
    public List<RentalDto> findRentalsByRentalDate(LocalDate checkoutDate) {
        return rentalRepository.findByCheckoutDate(checkoutDate).stream()
                .map(rentalMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Validates inputs and returns the corresponding tool.
     *
     * @param toolId             the tool ID
     * @param rentalDays         the rental days
     * @param discountPercent    the discount percent
     * @param checkoutDateString the checkout date string
     * @return the ToolDto
     * @throws Exception if validation fails
     */
    ToolDto validateInputsAndReturnTool(Long toolId, int rentalDays, int discountPercent, String checkoutDateString) throws Exception {
        // Validate inputs
        // min of 1 rentalDays
        if (rentalDays < 1) {
            throw new RentalCreationException(AppConstants.RENTAL_DAY_COUNT_ERROR);
        }
        // don't allow negative discount or greater than 100%
        if (discountPercent < 0 || discountPercent > 100) {
            throw new RentalCreationException(AppConstants.DISCOUNT_PERCENT_ERROR);
        }
        // we need to this check and make sure the date is ok. Just calling LocalDate.parse("02/29/01", ...),
        // (there are no leap day that year) returns 02/28/01, as opposed to an exception
        if (!Utility.isDateValid(checkoutDateString)) {
            throw new RentalCreationException(AppConstants.DATE_NOT_VALID_ERROR);
        }
        // make sure tool exists
        ToolDto toolDto = toolService.getToolById(toolId);
        if (toolDto == null) {
            throw new RentalCreationException(format(AppConstants.TOOL_ID_NOT_RECOGNIZED, toolId));
        }
        return toolDto;
    }

    /**
     * Checks out a tool and creates a rental agreement.
     *
     * @param toolId             the tool ID
     * @param customerId         the customer ID
     * @param rentalDays         the rental days
     * @param discountPercent    the discount percent
     * @param checkoutDateString the checkout date string
     * @return the Rental
     * @throws Exception if an error occurs during checkout
     */
    @Transactional(readOnly = false)
    public Rental checkout(Long toolId, Long customerId, int rentalDays, int discountPercent, String checkoutDateString) throws Exception {
        // validate the inputs and return the tool
        ToolDto toolDto = validateInputsAndReturnTool(toolId, rentalDays, discountPercent, checkoutDateString);
        ToolTypeDto toolTypeDto = toolTypeService.getToolTypeById(toolDto.getToolTypeId());
        ToolQuantity toolQuantity = inventoryService.checkoutTool(toolId);
        // Parse checkout date
        LocalDate checkoutDate = Utility.stringToDate(checkoutDateString);
        // calculate due date
        LocalDate dueDate = checkoutDate.plusDays(rentalDays);
        // Calculate charge days
        int chargeDays = new CalculateChargeDays(holidayService).getChargeDaysCount(toolTypeDto, checkoutDate, rentalDays);
        // Calculate charges
        BigDecimal dailyCharge = BigDecimal.valueOf(toolTypeDto.getDailyCharge());
        BigDecimal preDiscountCharge = dailyCharge.multiply(new BigDecimal(chargeDays));
        BigDecimal discountAmount = preDiscountCharge
                .multiply(new BigDecimal(discountPercent))
                .divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
        BigDecimal finalCharge = preDiscountCharge.subtract(discountAmount);
        // Create and return the rental agreement
        Rental rental =  new Rental(toolId, customerId, rentalDays, checkoutDate, dueDate, chargeDays, preDiscountCharge, discountPercent, discountAmount, finalCharge);
        printRental(rental);
        return rental;
    }

    /**
     * Prints the rental agreement details.
     *
     * @param rental the Rental
     */
    private void printRental(Rental rental) {

        Tool tool = toolRepository.findToolByToolId(rental.getToolId()).orElse(null);
        ToolType toolType = toolTypeRepository.findToolTypeByToolTypeId(tool.getToolTypeId()).orElse(null);
        Brand brand = brandRepository.findBrandByBrandId(tool.getBrandId()).orElse(null);
        String dailyCharge = Utility.currencyFormat(BigDecimal.valueOf(toolType.getDailyCharge()));

        // Format the rental agreement details here
        System.out.printf(
                AppConstants.RENTAL_AGREEMENT_DETAILS,
                tool.getToolCode(), brand.getBrandName(), toolType.getToolTypeName(),
                Utility.dateFormat(rental.getCheckoutDate()), rental.getRentalDays(), Utility.dateFormat(rental.getDueDate()),
                Utility.yesNo(toolType.isWeekendCharge()), Utility.yesNo(toolType.isHolidayCharge()),
                rental.getRentalDays(), Utility.dateFormat(rental.getCheckoutDate()), rental.getChargeDays(), dailyCharge,
                rental.getChargeDays(), dailyCharge, Utility.currencyFormat(rental.getPreDiscountCharge()),
                rental.getDiscountPercent(), Utility.currencyFormat(rental.getDiscountAmount()),
                Utility.currencyFormat(rental.getFinalCharge()));

    }

    /**
     * Returns a rented tool and updates the inventory.
     *
     * @param rentalId the rental ID
     * @return the ToolQuantity
     */
    @Transactional(readOnly = false)
    public ToolQuantity returnRental(Long rentalId) {
        Rental rental = rentalRepository.findRentalByRentalId(rentalId).orElse(null);
        if (rental == null) {
            throw new RentalCreationException(AppConstants.RENTAL_NOT_FOUND_WITH_ID + rentalId);
        }
        Long toolId = rental.getToolId();
        ToolQuantity toolQuantity = inventoryService.returnTool(toolId);
        return toolQuantity;
    }

}
