package com.example.assignment.controllers;

import com.example.assignment.dto.RewardsDto;
import com.example.assignment.dto.TransactionDetails;
import com.example.assignment.services.RewardsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/rewards")
@Validated
public class RewardController {

    @Autowired
    RewardsService rewardsService;

    @PostMapping(value = "/transaction/publish")
    public ResponseEntity<String> publishTransaction(@RequestBody(required = true) TransactionDetails transactionDetails) {
        try {
            rewardsService.publishTransaction(transactionDetails);
            return new ResponseEntity<>("published Successfully", HttpStatus.OK);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "/transaction/bulk/publish")
    public ResponseEntity<String> publishBulkTransaction(@RequestBody(required = true) List<TransactionDetails> transactionDetails) {
        try {
            rewardsService.publishBulkTransactions(transactionDetails);
            return new ResponseEntity<>("bulk published Successfully", HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "/monthly")
    public ResponseEntity<List<RewardsDto>> getRewardsByMonth(@RequestParam @Min(1) @Max(12) Integer month, @RequestParam Integer year) {
        try {
            return new ResponseEntity<>(rewardsService.getRewardsByMonth(year, month), HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "/duration")
    public ResponseEntity<List<RewardsDto>> getRewardsInDuration(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate, @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        try {
            return new ResponseEntity<>(rewardsService.getRewardsByDateRange(startDate, endDate), HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
