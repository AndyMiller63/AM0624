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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@Transactional
public class RentalService {

    private static final Logger logger = LoggerFactory.getLogger(RentalService.class);

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

    @Autowired
    public RentalService(ToolService toolService, ToolTypeService toolTypeService,
                         ToolRepository toolRepository, ToolTypeRepository toolTypeRepository,
                         BrandRepository brandRepository, InventoryService inventoryService,
                         RentalRepository rentalRepository, RentalMapper rentalMapper,
                         ToolQuantityRepository toolQuantityRepository,
                         HolidayRepository holidayRepository, HolidayService holidayService) {
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
    }

    public RentalDto createRental(CreateRentalDto createRentalDto) throws Exception {
        logger.debug(AppConstants.RENTAL_CREATE_DEBUG, createRentalDto);
        Rental rental = checkout(createRentalDto.getToolId(), createRentalDto.getCustomerId(),
                createRentalDto.getRentalDays(), createRentalDto.getDiscountPercent(),
                createRentalDto.getCheckoutDateString());
        Rental savedRental = rentalRepository.save(rental);
        logger.info(AppConstants.RENTAL_CREATE_INFO, savedRental.getRentalId());
        return rentalMapper.toDto(savedRental);
    }

    public RentalDto updateRental(Long id, RentalDto rentalDto) {
        logger.debug(AppConstants.RENTAL_UPDATE_DEBUG, id, rentalDto);
        Optional<Rental> existingRentalOpt = rentalRepository.findById(id);
        if (existingRentalOpt.isPresent()) {
            Rental existingRental = existingRentalOpt.get();
            existingRental.setToolId(rentalDto.getToolId());
            existingRental.setCustomerId(rentalDto.getCustomerId());
            existingRental.setCheckoutDate(rentalDto.getCheckoutDate());
            Rental updatedRental = rentalRepository.save(existingRental);
            logger.info(AppConstants.RENTAL_UPDATE_INFO, updatedRental.getRentalId());
            return rentalMapper.toDto(updatedRental);
        } else {
            throw new RuntimeException(format(AppConstants.RENTAL_NOT_FOUND, id));
        }
    }

    public void deleteRental(Long id) {
        logger.debug(AppConstants.RENTAL_DELETE_DEBUG, id);
        if (rentalRepository.existsById(id)) {
            rentalRepository.deleteById(id);
            logger.info(AppConstants.RENTAL_DELETE_INFO, id);
        } else {
            throw new RuntimeException(format(AppConstants.RENTAL_NOT_FOUND, id));
        }
    }

    @Transactional(readOnly = true)
    public RentalDto getRentalById(Long id) {
        logger.debug(AppConstants.RENTAL_FETCH_DEBUG, id);
        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(format(AppConstants.RENTAL_NOT_FOUND, id)));
        logger.info(AppConstants.RENTAL_FETCH_INFO, id);
        return rentalMapper.toDto(rental);
    }

    @Transactional(readOnly = true)
    public List<RentalDto> getAllRentals() {
        logger.debug(AppConstants.RENTAL_FETCH_ALL_DEBUG);
        List<RentalDto> rentals = rentalRepository.findAll().stream()
                .map(rentalMapper::toDto)
                .collect(Collectors.toList());
        logger.info(AppConstants.RENTAL_FETCH_ALL_INFO, rentals.size());
        return rentals;
    }

    @Transactional(readOnly = true)
    public List<RentalDto> findRentalsByToolId(Long toolId) {
        logger.debug(AppConstants.RENTAL_FETCH_TOOLID_DEBUG, toolId);
        List<RentalDto> rentals = rentalRepository.findByToolId(toolId).stream()
                .map(rentalMapper::toDto)
                .collect(Collectors.toList());
        logger.info(AppConstants.RENTAL_FETCH_TOOLID_INFO, rentals.size(), toolId);
        return rentals;
    }

    @Transactional(readOnly = true)
    public List<RentalDto> findRentalsByToolCode(String toolCode) {
        logger.debug(AppConstants.RENTAL_FETCH_TOOLCODE_DEBUG, toolCode);
        List<RentalDto> rentals = rentalRepository.findRentalsByToolCode(toolCode).stream()
                .map(rentalMapper::toDto)
                .collect(Collectors.toList());
        logger.info(AppConstants.RENTAL_FETCH_TOOLCODE_INFO, rentals.size(), toolCode);
        return rentals;
    }

    @Transactional(readOnly = true)
    public List<RentalDto> findRentalsByCustomerId(Long customerId) {
        logger.debug(AppConstants.RENTAL_FETCH_CUSTOMERID_DEBUG, customerId);
        List<RentalDto> rentals = rentalRepository.findByCustomerId(customerId).stream()
                .map(rentalMapper::toDto)
                .collect(Collectors.toList());
        logger.info(AppConstants.RENTAL_FETCH_CUSTOMERID_INFO, rentals.size(), customerId);
        return rentals;
    }

    @Transactional(readOnly = true)
    public List<RentalDto> findRentalsByCustomerName(String name) {
        logger.debug(AppConstants.RENTAL_FETCH_CUSTOMERNAME_DEBUG, name);
        List<RentalDto> rentals = rentalRepository.findRentalsByCustomerName(name).stream()
                .map(rentalMapper::toDto)
                .collect(Collectors.toList());
        logger.info(AppConstants.RENTAL_FETCH_CUSTOMERNAME_INFO, rentals.size(), name);
        return rentals;
    }

    @Transactional(readOnly = true)
    public List<RentalDto> findRentalsByCustomerContact(String contact) {
        logger.debug(AppConstants.RENTAL_FETCH_CUSTOMERCONTACT_DEBUG, contact);
        List<RentalDto> rentals = rentalRepository.findRentalsByCustomerContact(contact).stream()
                .map(rentalMapper::toDto)
                .collect(Collectors.toList());
        logger.info(AppConstants.RENTAL_FETCH_CUSTOMERCONTACT_INFO, rentals.size(), contact);
        return rentals;
    }

    @Transactional(readOnly = true)
    public List<RentalDto> findRentalsByRentalDate(LocalDate checkoutDate) {
        logger.debug(AppConstants.RENTAL_FETCH_RENTALDATE_DEBUG, checkoutDate);
        List<RentalDto> rentals = rentalRepository.findByCheckoutDate(checkoutDate).stream()
                .map(rentalMapper::toDto)
                .collect(Collectors.toList());
        logger.info(AppConstants.RENTAL_FETCH_RENTALDATE_INFO, rentals.size(), checkoutDate);
        return rentals;
    }

    public ToolDto validateInputsAndReturnTool(Long toolId, int rentalDays, int discountPercent, String checkoutDateString) throws Exception {
        logger.debug(AppConstants.VALIDATING_INPUTS, toolId);
        if (rentalDays < 1) {
            throw new RentalCreationException(AppConstants.RENTAL_DAY_COUNT_INVALID);
        }
        if (discountPercent < 0 || discountPercent > 100) {
            throw new RentalCreationException(AppConstants.DISCOUNT_PERCENT_INVALID);
        }
        if (!Utility.isDateValid(checkoutDateString)) {
            throw new RentalCreationException(AppConstants.DATE_NOT_VALID);
        }
        ToolDto toolDto = toolService.getToolById(toolId);
        if (toolDto == null) {
            throw new RentalCreationException(format(AppConstants.TOOL_NOT_RECOGNIZED, toolId));
        }
        return toolDto;
    }

    @Transactional(readOnly = false)
    public Rental checkout(Long toolId, Long customerId, int rentalDays, int discountPercent, String checkoutDateString) throws Exception {
        ToolDto toolDto = validateInputsAndReturnTool(toolId, rentalDays, discountPercent, checkoutDateString);
        ToolTypeDto toolTypeDto = toolTypeService.getToolTypeById(toolDto.getToolTypeId());
        ToolQuantity toolQuantity = inventoryService.checkoutTool(toolId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(AppConstants.RENTAL_CHECKOUT_DATE_FORMAT);
        LocalDate checkoutDate = LocalDate.parse(checkoutDateString, formatter);
        LocalDate dueDate = checkoutDate.plusDays(rentalDays);
        int chargeDays = new CalculateChargeDays().getChargeDaysCount(toolTypeDto, checkoutDate, rentalDays);
        BigDecimal dailyCharge = BigDecimal.valueOf(toolTypeDto.getDailyCharge());
        BigDecimal preDiscountCharge = dailyCharge.multiply(new BigDecimal(chargeDays));
        BigDecimal discountAmount = preDiscountCharge
                .multiply(new BigDecimal(discountPercent))
                .divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
        BigDecimal finalCharge = preDiscountCharge.subtract(discountAmount);
        Rental rental = new Rental(toolId, customerId, rentalDays, checkoutDate, dueDate, chargeDays, preDiscountCharge, discountPercent, discountAmount, finalCharge);
        printRental(rental);
        return rental;
    }

    private void printRental(Rental rental) {
        Tool tool = toolRepository.findToolByToolId(rental.getToolId()).orElse(null);
        ToolType toolType = toolTypeRepository.findToolTypeByToolTypeId(tool.getToolTypeId()).orElse(null);
        Brand brand = brandRepository.findBrandByBrandId(tool.getBrandId()).orElse(null);
        String dailyCharge = Utility.currencyFormat(BigDecimal.valueOf(toolType.getDailyCharge()));

        System.out.printf(
                AppConstants.RENTAL_PRINT_HEADER +
                        AppConstants.RENTAL_PRINT_CHECKOUT_DATE +
                        AppConstants.RENTAL_PRINT_INCURS_COSTS +
                        AppConstants.RENTAL_PRINT_RENTAL_DAYS +
                        AppConstants.RENTAL_PRINT_PRE_DISCOUNT_CHARGE +
                        AppConstants.RENTAL_PRINT_DISCOUNT +
                        AppConstants.RENTAL_PRINT_FINAL_CHARGE,
                tool.getToolCode(), brand.getBrandName(), toolType.getToolTypeName(),
                Utility.dateFormat(rental.getCheckoutDate()), rental.getRentalDays(), Utility.dateFormat(rental.getDueDate()),
                Utility.yesNo(toolType.isWeekendCharge()), Utility.yesNo(toolType.isHolidayCharge()),
                rental.getRentalDays(), Utility.dateFormat(rental.getCheckoutDate()), rental.getChargeDays(), dailyCharge,
                rental.getChargeDays(), dailyCharge, Utility.currencyFormat(rental.getPreDiscountCharge()),
                rental.getDiscountPercent(), Utility.currencyFormat(rental.getDiscountAmount()),
                Utility.currencyFormat(rental.getFinalCharge()));
    }

    @Transactional(readOnly = false)
    public ToolQuantity returnRental(Long rentalId) {
        Rental rental = rentalRepository.findRentalByRentalId(rentalId).orElse(null);
        if (rental == null) {
            throw new RentalCreationException(AppConstants.UNABLE_TO_FIND_RENTAL + rentalId);
        }
        Long toolId = rental.getToolId();
        ToolQuantity toolQuantity = inventoryService.returnTool(toolId);
        return toolQuantity;
    }
}
