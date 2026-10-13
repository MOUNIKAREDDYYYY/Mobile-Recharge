package com.example.mobile_recharge.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "mobile_recharges")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MobileRecharge {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recharge_id")
    private Long rechargeId;


    @Pattern(regexp = "[0-9]{10}", message = "Mobile number must contain 10 digits")
    @Column(name = "mobile_number", nullable = false, length = 10)
    private String mobileNumber;


    @NotBlank(message = "Operator is required")
    @Column(name = "operator", nullable = false, length = 50)
    private String operator;


    @Min(value = 10, message = "Minimum recharge amount is 10")
    @Column(name = "amount", nullable = false)
    private double amount;


    @Column(name = "plan_type", length = 50)
    private String planType;
}
