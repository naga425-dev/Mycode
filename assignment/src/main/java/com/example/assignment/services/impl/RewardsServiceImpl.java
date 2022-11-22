package com.example.assignment.services.impl;

import com.example.assignment.dao.RewardsDao;
import com.example.assignment.dto.RewardsDto;
import com.example.assignment.dto.TransactionDetails;
import com.example.assignment.entity.Rewards;
import com.example.assignment.error.BadRequestError;
import com.example.assignment.services.RewardsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public class RewardsServiceImpl implements RewardsService {

    @Autowired
    RewardsDao rewardsDao;

    @Override
    public void publishTransaction(TransactionDetails transactionDetails) throws Exception{
        if(transactionDetails == null) {
            throw new BadRequestError("Invalid TransactionDetails");
        }
        Rewards rewards = new Rewards();
        rewards.setTransactionId(transactionDetails.getTransactionId());
        rewards.setTransactionTime(transactionDetails.getTransactionTime());
        rewards.setTransactionAmount(transactionDetails.getTransactionAmount());
        Double txnAmount = transactionDetails.getTransactionAmount();
        rewards.setRewardsAmount(getRewardAmount(txnAmount));
        rewardsDao.save(rewards);
    }

    private double getRewardAmount(Double txnAmount) {
        double reward = 0.0;
        if(txnAmount > 50 && txnAmount <= 100) {
            reward = (txnAmount -50);
        } else if (txnAmount > 100) {
            reward = (txnAmount -50) + ((txnAmount -100)*2);
        }
        return reward;
    }

    @Override
    public void publishBulkTransactions(List<TransactionDetails> transactionDetailsList) {
        List<Rewards> rewardsList = new ArrayList<>();
        for(TransactionDetails txnDetails : transactionDetailsList) {
            Rewards rewards = new Rewards();
            rewards.setTransactionId(txnDetails.getTransactionId());
            rewards.setTransactionTime(txnDetails.getTransactionTime());
            rewards.setTransactionAmount(txnDetails.getTransactionAmount());
            Double txnAmount = txnDetails.getTransactionAmount();
            rewards.setRewardsAmount(getRewardAmount(txnAmount));
            rewardsList.add(rewards);
        }
        rewardsDao.saveAll(rewardsList);
    }

    @Override
    public List<RewardsDto> getRewardsByMonth(Integer year, Integer month) throws Exception{
        log.info("year {}, month {}", year, month);
        LocalDate currentDate = LocalDate.now();
        if(year == null || year == 0) {
            year = currentDate.getYear();
        }
        if(month == null || month == 0) {
            month = currentDate.getMonthValue();
        }
        if( year < 0|| month < 0 || month > 12) {
            throw new Exception("Invalid Year/Month is given in the request" + year + " : " + month);
        }
        if(year > (currentDate.getYear())) {
            year = currentDate.getYear();
            month = currentDate.getMonthValue();
        }
        if(month > currentDate.getMonthValue()) {
            month = currentDate.getMonthValue();
        }
        List<RewardsDto> rewardsDtoList = new ArrayList<>();
        LocalDateTime startDate, endDate = null;
        if(month <=11) {
            startDate = LocalDateTime.of(year, month, 1, 00,0,0,0);
            endDate = LocalDateTime.of(year, month+1, 1, 00,0,0,0);
        }else {
            startDate = LocalDateTime.of(year, month, 1, 00,0,0,0);
            endDate = LocalDateTime.of(year, month, 31, 23,59,59);
        }
        List<Rewards> rewardsList = rewardsDao.findAllByTransactionTimeBetween(startDate, endDate);

        Map<Integer, Double> rewardsPerMonth = new HashMap<>();

        for(Rewards reward : rewardsList) {
            Integer m = reward.getTransactionTime().getMonthValue();
            rewardsPerMonth.putIfAbsent(m,0.0);
            rewardsPerMonth.put(m,rewardsPerMonth.get(m) + reward.getRewardsAmount());
        }
        for(Map.Entry<Integer, Double> entry: rewardsPerMonth.entrySet()) {
            RewardsDto rewardsDto = new RewardsDto();
            rewardsDto.setMonth(entry.getKey());
            rewardsDto.setTotalRewards(entry.getValue());
            rewardsDtoList.add(rewardsDto);
        }
        return rewardsDtoList;
    }

    @Override
    public List<RewardsDto> getRewardsByDateRange(LocalDate startDate, LocalDate endDate) {
        List<RewardsDto> rewardsDtoList = new ArrayList<>();
        List<Rewards> rewardsList = rewardsDao.findAllByTransactionTimeBetween(startDate.atStartOfDay(), endDate.atTime(LocalTime.MAX));
        Map<Integer, Double> rewardsPerMonth = new HashMap<>();

        for(Rewards reward : rewardsList) {
            Integer m = reward.getTransactionTime().getMonthValue();
            rewardsPerMonth.putIfAbsent(m,0.0);
            rewardsPerMonth.put(m,rewardsPerMonth.get(m) + reward.getRewardsAmount());
        }
        for(Map.Entry<Integer, Double> entry: rewardsPerMonth.entrySet()) {
            RewardsDto rewardsDto = new RewardsDto();
            rewardsDto.setMonth(entry.getKey());
            rewardsDto.setTotalRewards(entry.getValue());
            rewardsDtoList.add(rewardsDto);
        }
        return rewardsDtoList;
    }
}
