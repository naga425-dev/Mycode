package com.example.assignment.dao;

import com.example.assignment.entity.Rewards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RewardsDao extends JpaRepository<Rewards, Long> {
    //@Query(value = "SELECT t FROM rewards t WHERE t.transactionTime between (:startTime, :endTime)")
    List<Rewards> findAllByTransactionTimeBetween(LocalDateTime startTime, LocalDateTime endTime);

}
