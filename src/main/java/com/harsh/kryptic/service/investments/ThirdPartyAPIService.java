package com.harsh.kryptic.service.investments;

import com.harsh.kryptic.domain.thirdparty.coingecko.CoinDetails;
import reactor.core.publisher.Mono;
import java.util.List;

public interface ThirdPartyAPIService {
    Mono<Object> getCurrentCoinPrice(List<String> coins, String currency);

    Mono<Object[]> getCoinDetails(List<String> coins, String currency);

    Mono<CoinDetails[]> getListOfCoinsSupported();

    Mono<String[]> getListOfCurrenciesSupported();

    Mono<Object> getCoinInfoByCoinId(String coinId);
}
