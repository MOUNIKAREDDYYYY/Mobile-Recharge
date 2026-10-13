package com.example.mobile_recharge.exception;

// Custom runtime exception thrown when a mobile recharge is not found in the database
public class MobileRechargeNotFoundException extends RuntimeException {

    // Constructor to pass custom error message to RuntimeException superclass
    public MobileRechargeNotFoundException(String message) {
        super(message);
    }
}
