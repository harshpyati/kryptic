package com.harsh.kryptic.domain;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity(name = "investments")
public class Investment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    Long userId;

    String coinSymbol;

    Double amountInvested;

    Date investmentDate;

    String currency;

    Double quantityInvested;
}
