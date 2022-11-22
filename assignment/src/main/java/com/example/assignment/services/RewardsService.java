package com.example.assignment.services;

import com.example.assignment.dto.RewardsDto;
import com.example.assignment.dto.TransactionDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface RewardsService {

    void publishTransaction(TransactionDetails transactionDetails) throws Exception;
    void publishBulkTransactions(List<TransactionDetails> transactionDetailsList);

    List<RewardsDto> getRewardsByMonth(Integer year, Integer month) throws Exception;

    List<RewardsDto> getRewardsByDateRange(LocalDate startDate, LocalDate endDate);

}
