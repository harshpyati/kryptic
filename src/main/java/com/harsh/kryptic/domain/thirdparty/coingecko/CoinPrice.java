package com.harsh.kryptic.domain.thirdparty.coingecko;

import lombok.Data;

import java.util.Map;

@Data
public class CoinPrice {
    Map<String, CurrencyPriceMap> coinPriceMap;
}
