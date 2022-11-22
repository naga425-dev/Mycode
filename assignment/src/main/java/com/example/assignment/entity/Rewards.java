package com.example.assignment.entity;

import lombok.Data;
import lombok.Generated;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "rewards")
public class Rewards {

    @Id
    @GeneratedValue
    private Integer id;
    private Long transactionId;
    private Double transactionAmount;
    private LocalDateTime transactionTime;
    private Double rewardsAmount;
}
