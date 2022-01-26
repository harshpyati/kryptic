package com.harsh.kryptic.domain;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class InvestmentStatistics {
    Map<String, Double> currentGains;

    Map<String, InvestmentLite> investedAt;

    Map<String, Double> marketPrice;
}
