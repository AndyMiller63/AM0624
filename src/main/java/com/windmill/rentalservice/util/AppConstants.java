package com.windmill.rentalservice.util;

public class AppConstants {
    // RentalService logging messages
    public static final String RENTAL_CREATE_DEBUG = "Creating rental with DTO: {}";
    public static final String RENTAL_CREATE_INFO = "Rental created successfully with ID: {}";
    public static final String RENTAL_UPDATE_DEBUG = "Updating rental with ID: {} using DTO: {}";
    public static final String RENTAL_UPDATE_INFO = "Rental updated successfully with ID: {}";
    public static final String RENTAL_DELETE_DEBUG = "Deleting rental with ID: {}";
    public static final String RENTAL_DELETE_INFO = "Rental deleted successfully with ID: {}";
    public static final String RENTAL_FETCH_DEBUG = "Fetching rental by ID: {}";
    public static final String RENTAL_FETCH_INFO = "Rental fetched successfully with ID: {}";
    public static final String RENTAL_FETCH_ALL_DEBUG = "Fetching all rentals";
    public static final String RENTAL_FETCH_ALL_INFO = "Fetched {} rentals";
    public static final String RENTAL_FETCH_TOOLID_DEBUG = "Fetching rentals by tool ID: {}";
    public static final String RENTAL_FETCH_TOOLID_INFO = "Fetched {} rentals by tool ID: {}";
    public static final String RENTAL_FETCH_TOOLCODE_DEBUG = "Fetching rentals by tool code: {}";
    public static final String RENTAL_FETCH_TOOLCODE_INFO = "Fetched {} rentals by tool code: {}";
    public static final String RENTAL_FETCH_CUSTOMERID_DEBUG = "Fetching rentals by customer ID: {}";
    public static final String RENTAL_FETCH_CUSTOMERID_INFO = "Fetched {} rentals by customer ID: {}";
    public static final String RENTAL_FETCH_CUSTOMERNAME_DEBUG = "Fetching rentals by customer name: {}";
    public static final String RENTAL_FETCH_CUSTOMERNAME_INFO = "Fetched {} rentals by customer name: {}";
    public static final String RENTAL_FETCH_CUSTOMERCONTACT_DEBUG = "Fetching rentals by customer contact: {}";
    public static final String RENTAL_FETCH_CUSTOMERCONTACT_INFO = "Fetched {} rentals by customer contact: {}";
    public static final String RENTAL_FETCH_RENTALDATE_DEBUG = "Fetching rentals by rental date: {}";
    public static final String RENTAL_FETCH_RENTALDATE_INFO = "Fetched {} rentals by rental date: {}";
    public static final String VALIDATING_INPUTS = "Validating inputs and returning tool for tool ID: {}";

    // RentalService exception messages
    public static final String RENTAL_NOT_FOUND = "Rental not found with id: {}";
    public static final String RENTAL_DAY_COUNT_INVALID = "Rental day count must be 1 or greater.";
    public static final String DISCOUNT_PERCENT_INVALID = "Discount percent must be in the range 0-100.";
    public static final String DATE_NOT_VALID = "Date is not valid.";
    public static final String TOOL_NOT_RECOGNIZED = "Tool id %d is not recognized.";
    public static final String UNABLE_TO_FIND_RENTAL = "Unable to find rental with rentalId=";

    // RentalService printRental strings
    public static final String RENTAL_PRINT_HEADER = "\nFor one %s, a %s brand %s\n";
    public static final String RENTAL_PRINT_CHECKOUT_DATE = "Checkout date: %s. with a rental period of %d days makes your due date %s.\n";
    public static final String RENTAL_PRINT_INCURS_COSTS = "Incurs cost over weekends: %s, incurs cost over holidays: %s.\n";
    public static final String RENTAL_PRINT_RENTAL_DAYS = "Over the %d days following the date of %s you will be charged for %d of those days at %s per day, before any discounts.\n";
    public static final String RENTAL_PRINT_PRE_DISCOUNT_CHARGE = "Pre-discount charge over %d days @ %s per day, is %s.\n";
    public static final String RENTAL_PRINT_DISCOUNT = "Discount percent: %d%% gives you a discount of %s.\n";
    public static final String RENTAL_PRINT_FINAL_CHARGE = "Your final charge will be %s.%n";

    // InventoryService messages
    public static final String TOOL_NOT_AVAILABLE = "Tool is not available for checkout. Id: ";
    public static final String TOOL_NOT_FOUND = "Tool not found for id: ";
    // ToolQuantityService messages
    public static final String TOOL_QUANTITY_NOT_FOUND = "Tool quantity not found for tool id: ";

    // ToolTypeService messages
    public static final String TOOL_TYPE_NOT_FOUND = "ToolType not found with id: ";

    // General messages
    public static final String RENTAL_DAY_COUNT_ERROR = "Rental day count must be 1 or greater.";
    public static final String DISCOUNT_PERCENT_ERROR = "Discount percent must be in the range 0-100.";
    public static final String INVALID_DATE_ERROR = "Date is not valid.";
    public static final String TOOL_ID_ERROR = "Tool id %d is not recognized.";
    public static final String USERNAME_EXISTS_ERROR = "Username already exists";
    public static final String USER_NOT_FOUND_ERROR = "User not found with id: ";
    public static final String RENTAL_NOT_FOUND_ERROR = "Rental not found with id: ";
    public static final String BRAND_NOT_FOUND_ERROR = "Brand not found with id: ";
    public static final String HOLIDAY_NOT_FOUND_ERROR = "Holiday not found with id: ";
    public static final String CUSTOMER_NOT_FOUND_ERROR = "Customer not found with id: ";
    public static final String TOOL_NOT_FOUND_ERROR = "Tool not found with id: ";

    // Success messages
    public static final String USER_REGISTERED_SUCCESS = "User registered successfully.";
    public static final String RENTAL_CREATED_SUCCESS = "Rental created successfully.";
    public static final String BRAND_CREATED_SUCCESS = "Brand created successfully.";
    public static final String HOLIDAY_CREATED_SUCCESS = "Holiday created successfully.";
    public static final String CUSTOMER_CREATED_SUCCESS = "Customer created successfully.";
    public static final String TOOL_CREATED_SUCCESS = "Tool created successfully.";

    // Other messages
    public static final String AUTHENTICATION_SUCCESS = "User authenticated successfully: ";
    public static final String LOADING_USER_BY_USERNAME = "Loading user by username: ";
    public static final String ATTEMPTING_AUTHENTICATION = "Attempting to authenticate user: ";

    // Utility messages
    public static final String RENTAL_CHECKOUT_DATE_FORMAT = "MM/dd/yy";

}
