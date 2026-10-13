package com.example.mobile_recharge.controller;


import com.example.mobile_recharge.models.MobileRecharge;
import com.example.mobile_recharge.services.MobileRechargeService;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/recharges")
public class MobileRechargeController {

    // Service dependency injected via constructor
    private final MobileRechargeService rechargeService;

    // Constructor injection for loose coupling and easy unit testing
    public MobileRechargeController(MobileRechargeService rechargeService) {
        this.rechargeService = rechargeService;
    }

    // 1. Register a new mobile recharge
    @PostMapping
    public ResponseEntity<MobileRecharge> registerRecharge(@Valid @RequestBody MobileRecharge recharge) {
        MobileRecharge savedRecharge = rechargeService.registerRecharge(recharge);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRecharge);
    }

    // 2. Retrieve all recharge records
    @GetMapping
    public ResponseEntity<List<MobileRecharge>> getAllRecharges() {
        // Retrieve list of all recharges from service
        List<MobileRecharge> recharges = rechargeService.getAllRecharges();
        return ResponseEntity.ok(recharges);
    }

    // 3. Get a specific recharge by ID
    @GetMapping("/{id}")
    public ResponseEntity<MobileRecharge> getRechargeById(@PathVariable Long id) {
        MobileRecharge recharge = rechargeService.getRechargeById(id);
        return ResponseEntity.ok(recharge);
    }

    // 4.  Delete a recharge by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecharge(@PathVariable Long id) {
        rechargeService.deleteRecharge(id);
        return ResponseEntity.noContent().build();
    }

    // 5. Update an existing recharge by ID
    @PutMapping("/{id}")
    public ResponseEntity<MobileRecharge> updateRecharge(@PathVariable Long id, @Valid @RequestBody MobileRecharge recharge) {
        MobileRecharge updatedRecharge = rechargeService.updateRecharge(id, recharge);
        return ResponseEntity.ok(updatedRecharge);
    }

    // 6. Partially update an existing recharge by ID
    @PatchMapping("/{id}")
    public ResponseEntity<MobileRecharge> patchRecharge(@PathVariable Long id, @RequestBody MobileRecharge recharge) {
        MobileRecharge patchedRecharge = rechargeService.patchRecharge(id, recharge);
        return ResponseEntity.ok(patchedRecharge);
    }

    // 6. Search recharges by mobile number
    @GetMapping("/search/mobile")
    public ResponseEntity<List<MobileRecharge>> searchByMobileNumber(@RequestParam String mobileNumber) {
        return ResponseEntity.ok(rechargeService.searchByMobileNumber(mobileNumber));
    }

    // 7. Search recharges by operator
    @GetMapping("/search/operator")
    public ResponseEntity<List<MobileRecharge>> searchByOperator(@RequestParam String operator) {
        return ResponseEntity.ok(rechargeService.searchByOperator(operator));
    }

    // 8.  Search recharges by plan type
    @GetMapping("/search/plan-type")
    public ResponseEntity<List<MobileRecharge>> searchByPlanType(@RequestParam String planType) {
        return ResponseEntity.ok(rechargeService.searchByPlanType(planType));
    }

    // 9. Search recharges by amount
    @GetMapping("/search/amount")
    public ResponseEntity<List<MobileRecharge>> searchByAmount(@RequestParam double amount) {
        return ResponseEntity.ok(rechargeService.searchByAmount(amount));
    }

    // 10. Search by operator AND amount
    @GetMapping("/search/operator-amount")
    public ResponseEntity<List<MobileRecharge>> searchByOperatorAndAmount(@RequestParam String operator, @RequestParam double amount) {
        return ResponseEntity.ok(rechargeService.searchByOperatorAndAmount(operator, amount));
    }

    // 11.  Search by operator OR plan type
    @GetMapping("/search/operator-or-plan")
    public ResponseEntity<List<MobileRecharge>> searchByOperatorOrPlanType(
            @RequestParam String operator, @RequestParam String planType) {
        return ResponseEntity.ok(rechargeService.searchByOperatorOrPlanType(operator, planType));
    }

    // 12.Find recharges with amount greater than given amount
    @GetMapping("/search/higher")
    public ResponseEntity<List<MobileRecharge>> searchHigherRecharges(@RequestParam double amount) {
        return ResponseEntity.ok(rechargeService.searchByAmountGreaterThan(amount));
    }

    // 13.  Find recharges in amount range
    @GetMapping("/search/amount-range")
    public ResponseEntity<List<MobileRecharge>> searchByAmountRange(@RequestParam double minAmount, @RequestParam double maxAmount) {
        return ResponseEntity.ok(rechargeService.searchByAmountBetween(minAmount, maxAmount));
    }

    // 14.  Search by partial operator keyword
    @GetMapping("/search/keyword")
    public ResponseEntity<List<MobileRecharge>> searchByKeyword(@RequestParam String keyword) {
        return ResponseEntity.ok(rechargeService.searchByOperatorKeyword(keyword));
    }

    // 15. Get all recharges sorted by amount
    @GetMapping("/search/sorted")
    public ResponseEntity<List<MobileRecharge>> getRechargesSortedByAmount() {
        return ResponseEntity.ok(rechargeService.getRechargesSortedByAmount());
    }

    // 16. Count recharges by operator
    @GetMapping("/operator-count")
    public ResponseEntity<Long> countRechargesByOperator(@RequestParam String operator) {
        return ResponseEntity.ok(rechargeService.countRechargesByOperator(operator));
    }

    // 17.Register multiple mobile recharges in a batch
    @PostMapping("/bulk")
    public ResponseEntity<List<MobileRecharge>> registerAll(@Valid @RequestBody List<MobileRecharge> recharges) {
        List<MobileRecharge> savedRecharges = rechargeService.registerAll(recharges);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRecharges);
    }

    // 18.Delete all recharges from database
    @DeleteMapping("/clear-all")
    public ResponseEntity<Void> clearAll() {
        rechargeService.clearAll();
        return ResponseEntity.noContent().build();
    }

    // 19.  Delete multiple recharges by list of IDs
    @DeleteMapping("/bulk")
    public ResponseEntity<Void> deleteByIds(@RequestBody List<Long> ids) {
        rechargeService.deleteByIds(ids);
        return ResponseEntity.noContent().build();
    }
}
