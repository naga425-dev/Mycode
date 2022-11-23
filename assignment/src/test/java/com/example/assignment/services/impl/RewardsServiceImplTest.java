package com.example.assignment.services.impl;

import com.example.assignment.dao.RewardsDao;
import com.example.assignment.dto.TransactionDetails;
import com.example.assignment.entity.Rewards;
import com.example.assignment.services.RewardsService;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;


@ExtendWith(MockitoExtension.class)
class RewardsServiceImplTest {

    @InjectMocks
    private RewardsServiceImpl rewardsService;
    @Mock
    private RewardsDao rewardsDao;

    @Test
    void publishTransactionSuccess() throws Exception {

        TransactionDetails transactionDetails = new TransactionDetails();
        Rewards rewards = new Rewards();
//        Mock(rewardsDao.save(Mockito.any(Rewards.class))).thenReturn(rewards);
        //Mockito.verify(rewardsDao, Mockito.times(1)).save(new Rewards());
    }

    @Test
    void publishTransactionFailure() throws Exception {
    }

    @Test
    void publishBulkTransactions() {
    }

    @Test
    void getRewardsByMonth() {
//        Mockito.doReturn(new ArrayList<>()).when(rewardsDao).findAllByTransactionTimeBetween(Mockito.any(), Mockito.any());
//        Assertions.assertEquals(0, rewardsService.getRewardsByDateRange(Mockito.any(),Mockito.any()));
    }

    @Test
    void getRewardsByDateRange() {
    }
}