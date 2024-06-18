package com.windmill.rentalservice.util;

public class AppConstants {
    // RentalService exception messages
    public static final String RENTAL_DAY_COUNT_INVALID = "Rental day count must be 1 or greater.";
    public static final String DISCOUNT_PERCENT_INVALID = "Discount percent must be in the range 0-100.";
    public static final String DATE_NOT_VALID = "Date is not valid.";
    public static final String TOOL_NOT_RECOGNIZED = "Tool id %d is not recognized.";

    // RentalService printRental strings
    public static final String RENTAL_NOT_FOUND_WITH_ID = "Rental not found with id: ";
    public static final String RENTAL_DAY_COUNT_ERROR = "Rental day count must be 1 or greater.";
    public static final String DISCOUNT_PERCENT_ERROR = "Discount percent must be in the range 0-100.";
    public static final String DATE_NOT_VALID_ERROR = "Date is not valid.";
    public static final String TOOL_ID_NOT_RECOGNIZED = "Tool id %d is not recognized.";
    public static final String RENTAL_AGREEMENT_DETAILS = "\nFor one %s, a %s brand %s\n" +
            "Checkout date: %s. with a rental period of %d days makes your due date %s.\n" +
            "Incurs cost over weekends: %s, incurs cost over holidays: %s.\n" +
            "Over the %d days following the date of %s you will be charged for %d of those days at %s per day, before any discounts.\n" +
            "Pre-discount charge over %d days @ %s per day, is %s.\n" +
            "Discount percent: %d%% gives you a discount of %s.\n" +
            "Your final charge will be %s.%n";

    // InventoryService messages
    public static final String TOOL_NOT_AVAILABLE = "Tool is not available for checkout. Id: ";
    public static final String TOOL_NOT_FOUND = "Tool not found for id: ";
    // ToolQuantityService messages
    public static final String TOOL_QUANTITY_NOT_FOUND = "Tool quantity not found for tool id: ";

    // ToolTypeService messages
    public static final String TOOL_TYPE_NOT_FOUND = "ToolType not found with id: ";

    // General messages
   public static final String INVALID_DATE_ERROR = "Date is not valid.";
    public static final String TOOL_ID_ERROR = "Tool id %d is not recognized.";
    public static final String USERNAME_EXISTS_ERROR = "Username already exists";
    public static final String USER_NOT_FOUND_ERROR = "User not found with id: ";
    public static final String RENTAL_NOT_FOUND_ERROR = "Rental not found with id: ";
    public static final String BRAND_NOT_FOUND_ERROR = "Brand not found with id: ";
    public static final String HOLIDAY_NOT_FOUND_ERROR = "Holiday not found with id: ";
    public static final String CUSTOMER_NOT_FOUND_ERROR = "Customer not found with id: ";
    public static final String TOOL_NOT_FOUND_ERROR = "Tool not found with id: ";

    // Other messages
    public static final String AUTHENTICATION_SUCCESS = "User authenticated successfully: ";
    public static final String LOADING_USER_BY_USERNAME = "Loading user by username: ";
    public static final String ATTEMPTING_AUTHENTICATION = "Attempting to authenticate user: ";

    // Utility messages
    public static final String RENTAL_CHECKOUT_DATE_FORMAT = "MM/dd/yy";

}
