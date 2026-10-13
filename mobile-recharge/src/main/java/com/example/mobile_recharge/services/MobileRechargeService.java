package com.example.mobile_recharge.services;
import com.example.mobile_recharge.models.MobileRecharge;
import java.util.List;


public interface MobileRechargeService {

    // Register and save a new mobile recharge in the system
    MobileRecharge registerRecharge(MobileRecharge recharge);

    // Retrieve all recharge records
    List<MobileRecharge> getAllRecharges();

    // Find a recharge by its unique ID
    MobileRecharge getRechargeById(Long rechargeId);

    // Delete a recharge record from the database by ID
    void deleteRecharge(Long rechargeId);

    // Update details of an existing recharge by ID
    MobileRecharge updateRecharge(Long id, MobileRecharge recharge);

    // Partially update specific fields of an existing recharge by ID
    MobileRecharge patchRecharge(Long id, MobileRecharge recharge);

    // Search recharges by exact mobile number
    List<MobileRecharge> searchByMobileNumber(String mobileNumber);

    // Search recharges by telecom operator
    List<MobileRecharge> searchByOperator(String operator);

    // Search recharges by plan type
    List<MobileRecharge> searchByPlanType(String planType);

    // Search recharges by exact amount
    List<MobileRecharge> searchByAmount(double amount);

    // Search recharges matching both operator AND amount
    List<MobileRecharge> searchByOperatorAndAmount(String operator, double amount);

    // Search recharges matching either operator OR plan type
    List<MobileRecharge> searchByOperatorOrPlanType(String operator, String planType);

    // Search recharges strictly greater than a given amount
    List<MobileRecharge> searchByAmountGreaterThan(double amount);

    // Search recharges within an amount range (inclusive)
    List<MobileRecharge> searchByAmountBetween(double minAmount, double maxAmount);

    // Search recharges by keyword in operator name (case-insensitive)
    List<MobileRecharge> searchByOperatorKeyword(String keyword);

    // Get all recharges sorted in ascending order by amount
    List<MobileRecharge> getRechargesSortedByAmount();

    // Count how many recharges were processed for a specific operator
    long countRechargesByOperator(String operator);

    // Register multiple mobile recharges in batch
    List<MobileRecharge> registerAll(List<MobileRecharge> recharges);

    // Delete all recharge records from the database
    void clearAll();

    // Delete multiple recharges by list of IDs
    void deleteByIds(List<Long> ids);
}
