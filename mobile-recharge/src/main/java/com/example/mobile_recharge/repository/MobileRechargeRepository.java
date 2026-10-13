package com.example.mobile_recharge.repository;


import com.example.mobile_recharge.models.MobileRecharge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface MobileRechargeRepository extends JpaRepository<MobileRecharge, Long> {

    // 1. Find all recharges matching an exact 10-digit mobile number
    List<MobileRecharge> findByMobileNumber(String mobileNumber);

    // 2. Find all recharges for a specific telecom operator
    List<MobileRecharge> findByOperator(String operator);

    // 3. Find all recharges for a specific plan type
    List<MobileRecharge> findByPlanType(String planType);

    // 4. Find all recharges matching an exact amount
    List<MobileRecharge> findByAmount(double amount);

    // 5. Find recharges matching both operator AND amount (AND condition)
    List<MobileRecharge> findByOperatorAndAmount(String operator, double amount);

    // 6. Find recharges matching operator OR plan type (OR condition)
    List<MobileRecharge> findByOperatorOrPlanType(String operator, String planType);

    // 7. Find recharges strictly greater than a given amount
    List<MobileRecharge> findByAmountGreaterThan(double amount);

    // 8. Find recharges between minAmount and maxAmount (inclusive range)
    List<MobileRecharge> findByAmountBetween(double minAmount, double maxAmount);

    // 9. Search recharges whose operator contains a keyword, ignoring uppercase/lowercase differences
    List<MobileRecharge> findByOperatorContainingIgnoreCase(String keyword);

    // 10. Fetch all recharges sorted in ascending order by amount
    List<MobileRecharge> findAllByOrderByAmountAsc();

    // 11. Count total number of recharge transactions for a specific operator
    long countByOperator(String operator);
}
