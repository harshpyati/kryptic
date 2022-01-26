package com.harsh.kryptic.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InvestmentLite {
    Double quantity;

    Double amount;
}
