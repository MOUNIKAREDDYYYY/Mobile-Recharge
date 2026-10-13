package com.example.mobile_recharge.services;

import com.example.mobile_recharge.exception.MobileRechargeNotFoundException;
import com.example.mobile_recharge.models.MobileRecharge;
import com.example.mobile_recharge.repository.MobileRechargeRepository;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class MobileRechargeServiceImp implements MobileRechargeService {

    // Dependency injection of repository via constructor
    private final MobileRechargeRepository rechargeRepository;

    // Constructor injection (recommended over @Autowired field injection)
    public MobileRechargeServiceImp(MobileRechargeRepository rechargeRepository) {
        this.rechargeRepository = rechargeRepository;
    }

    // 1. Save and register a new recharge record into MySQL database
    @Override
    public MobileRecharge registerRecharge(MobileRecharge recharge) {
        // Calls JPA repository save method to insert record
        return rechargeRepository.save(recharge);
    }

    // 2. Fetch all recharge records currently stored in the database
    @Override
    public List<MobileRecharge> getAllRecharges() {
        // Calls JPA repository findAll method to return all rows
        return rechargeRepository.findAll();
    }

    // 3. Find a single recharge by primary key ID
    @Override
    public MobileRecharge getRechargeById(Long rechargeId) {
        // Find by ID, or throw MobileRechargeNotFoundException if record does not exist
        return rechargeRepository.findById(rechargeId)
                .orElseThrow(() -> new MobileRechargeNotFoundException(
                        "Recharge not found with id: " + rechargeId
                ));
    }

    // 4. Delete recharge by ID from the database
    @Override
    public void deleteRecharge(Long rechargeId) {
        // Check if recharge exists before attempting deletion
        if (!rechargeRepository.existsById(rechargeId)) {
            // Throw exception if recharge does not exist
            throw new MobileRechargeNotFoundException(
                    "Recharge not found with id: " + rechargeId
            );
        }
        // Delete recharge from repository
        rechargeRepository.deleteById(rechargeId);
    }

    // 5. Update an existing recharge record by ID
    @Override
    public MobileRecharge updateRecharge(Long rechargeId, MobileRecharge recharge) {
        // Verify that the recharge exists in the database
        MobileRecharge existingRecharge = rechargeRepository.findById(rechargeId)
                .orElseThrow(() -> new MobileRechargeNotFoundException(
                        "Recharge not found with ID: " + rechargeId
                ));

        // Update fields with new values provided in the request
        existingRecharge.setMobileNumber(recharge.getMobileNumber());
        existingRecharge.setOperator(recharge.getOperator());
        existingRecharge.setAmount(recharge.getAmount());
        existingRecharge.setPlanType(recharge.getPlanType());

        // Save updated entity back to the database
        return rechargeRepository.save(existingRecharge);
    }

    // 6. Partially update specific fields of an existing recharge by ID
    @Override
    public MobileRecharge patchRecharge(Long rechargeId, MobileRecharge partialRecharge) {
        // Fetch existing recharge from database or throw exception if not found
        MobileRecharge existingRecharge = rechargeRepository.findById(rechargeId)
                .orElseThrow(() -> new MobileRechargeNotFoundException(
                        "Recharge not found with ID: " + rechargeId
                ));

        // Update mobile number only if provided in request
        if (partialRecharge.getMobileNumber() != null && !partialRecharge.getMobileNumber().isBlank()) {
            existingRecharge.setMobileNumber(partialRecharge.getMobileNumber());
        }

        // Update operator only if provided in request
        if (partialRecharge.getOperator() != null && !partialRecharge.getOperator().isBlank()) {
            existingRecharge.setOperator(partialRecharge.getOperator());
        }

        // Update amount only if a positive value is provided in request
        if (partialRecharge.getAmount() > 0) {
            existingRecharge.setAmount(partialRecharge.getAmount());
        }

        // Update plan type only if provided in request
        if (partialRecharge.getPlanType() != null && !partialRecharge.getPlanType().isBlank()) {
            existingRecharge.setPlanType(partialRecharge.getPlanType());
        }

        // Save and return the updated entity
        return rechargeRepository.save(existingRecharge);
    }

    // 6. Search recharges matching an exact mobile number
    @Override
    public List<MobileRecharge> searchByMobileNumber(String mobileNumber) {
        // Query repository by mobile number
        List<MobileRecharge> recharges = rechargeRepository.findByMobileNumber(mobileNumber);
        // Throw exception if no recharges match
        if (recharges.isEmpty()) {
            throw new MobileRechargeNotFoundException(
                    "No recharges found for mobile number: " + mobileNumber
            );
        }
        return recharges;
    }

    // 7. Search recharges matching an exact telecom operator
    @Override
    public List<MobileRecharge> searchByOperator(String operator) {
        // Query repository by operator
        List<MobileRecharge> recharges = rechargeRepository.findByOperator(operator);
        // Throw exception if no matching recharges are found
        if (recharges.isEmpty()) {
            throw new MobileRechargeNotFoundException(
                    "No recharges found for operator: " + operator
            );
        }
        return recharges;
    }

    // 8. Search recharges matching a specific plan type
    @Override
    public List<MobileRecharge> searchByPlanType(String planType) {
        // Query repository by plan type
        List<MobileRecharge> recharges = rechargeRepository.findByPlanType(planType);
        // Throw exception if no recharges match plan type
        if (recharges.isEmpty()) {
            throw new MobileRechargeNotFoundException(
                    "No recharges found for plan type: " + planType
            );
        }
        return recharges;
    }

    // 9. Search recharges matching an exact amount
    @Override
    public List<MobileRecharge> searchByAmount(double amount) {
        // Query repository by amount
        List<MobileRecharge> recharges = rechargeRepository.findByAmount(amount);
        // Throw exception if no recharges match amount
        if (recharges.isEmpty()) {
            throw new MobileRechargeNotFoundException(
                    "No recharges found for amount: " + amount
            );
        }
        return recharges;
    }

    // 10. Search recharges matching both operator AND amount
    @Override
    public List<MobileRecharge> searchByOperatorAndAmount(String operator, double amount) {
        // Query repository with AND condition
        List<MobileRecharge> recharges = rechargeRepository.findByOperatorAndAmount(operator, amount);
        // Throw exception if no recharges match both criteria
        if (recharges.isEmpty()) {
            throw new MobileRechargeNotFoundException(
                    "No recharges found for operator: " + operator + " with amount: " + amount
            );
        }
        return recharges;
    }

    // 11. Search recharges matching operator OR plan type
    @Override
    public List<MobileRecharge> searchByOperatorOrPlanType(String operator, String planType) {
        // Query repository with OR condition
        List<MobileRecharge> recharges = rechargeRepository.findByOperatorOrPlanType(operator, planType);
        // Throw exception if no recharges match either criterion
        if (recharges.isEmpty()) {
            throw new MobileRechargeNotFoundException(
                    "No recharges found for operator: " + operator + " or plan type: " + planType
            );
        }
        return recharges;
    }

    // 12. Search recharges strictly greater than a given amount
    @Override
    public List<MobileRecharge> searchByAmountGreaterThan(double amount) {
        // Query repository for recharges with amount > given amount
        List<MobileRecharge> recharges = rechargeRepository.findByAmountGreaterThan(amount);
        // Throw exception if no recharges match
        if (recharges.isEmpty()) {
            throw new MobileRechargeNotFoundException(
                    "No recharges found with amount greater than: " + amount
            );
        }
        return recharges;
    }

    // 13. Search recharges within an amount range
    @Override
    public List<MobileRecharge> searchByAmountBetween(double minAmount, double maxAmount) {
        // Query repository for amount between minAmount and maxAmount
        List<MobileRecharge> recharges = rechargeRepository.findByAmountBetween(minAmount, maxAmount);
        // Throw exception if no recharges fall within the range
        if (recharges.isEmpty()) {
            throw new MobileRechargeNotFoundException(
                    "No recharges found between amounts " + minAmount + " and " + maxAmount
            );
        }
        return recharges;
    }

    // 14. Search recharges by keyword in operator (case-insensitive)
    @Override
    public List<MobileRecharge> searchByOperatorKeyword(String keyword) {
        // Query repository for partial operator match ignoring case
        List<MobileRecharge> recharges = rechargeRepository.findByOperatorContainingIgnoreCase(keyword);
        // Throw exception if keyword does not match any recharge
        if (recharges.isEmpty()) {
            throw new MobileRechargeNotFoundException(
                    "No recharges found matching operator keyword: " + keyword
            );
        }
        return recharges;
    }

    // 15. Get all recharges sorted in ascending order by amount
    @Override
    public List<MobileRecharge> getRechargesSortedByAmount() {
        // Query repository sorted by amount ascending
        List<MobileRecharge> recharges = rechargeRepository.findAllByOrderByAmountAsc();
        // Throw exception if database is empty
        if (recharges.isEmpty()) {
            throw new MobileRechargeNotFoundException(
                    "No recharges are available to display"
            );
        }
        return recharges;
    }

    // 16. Count total recharges for a particular operator
    @Override
    public long countRechargesByOperator(String operator) {
        // Query repository count by operator
        return rechargeRepository.countByOperator(operator);
    }

    // 17. Register multiple recharge records in a single batch
    @Override
    public List<MobileRecharge> registerAll(List<MobileRecharge> recharges) {
        // Calls JPA repository saveAll method to insert all records in batch
        return rechargeRepository.saveAll(recharges);
    }

    // 18. Delete all recharge records from the database
    @Override
    public void clearAll() {
        // Calls JPA repository deleteAll to clear all rows
        rechargeRepository.deleteAll();
    }

    // 19. Delete multiple recharges by list of IDs
    @Override
    public void deleteByIds(List<Long> ids) {
        // Calls JPA repository deleteAllById to delete matching rows
        rechargeRepository.deleteAllById(ids);
    }
}
