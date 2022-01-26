package com.harsh.kryptic.service.investments.impl.thirdparty;

import com.harsh.kryptic.domain.thirdparty.coingecko.CoinDetails;
import com.harsh.kryptic.service.investments.ThirdPartyAPIService;
import com.harsh.kryptic.thirpartyapi.ApiClient;
import com.harsh.kryptic.utils.ApiEndpoints;
import com.harsh.kryptic.utils.Utils;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;
import reactor.core.publisher.Mono;


public class CoinGeckoAPIServiceImpl implements ThirdPartyAPIService {
    private static final WebClient client = ApiClient.getApiClient();

    @Override
    public Mono<Object> getCurrentCoinPrice(List<String> coins, String currency) {
        return client.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path(ApiEndpoints.CG_PRICE_LIST)
                                .queryParam(ApiEndpoints.CG_QUERY_CURRENCIES, currency)
                                .queryParam(ApiEndpoints.CG_QUERY_COINS, Utils.getCommaSeparatedCoinsFromListOfCoins(coins))
                                .build()
                )
                .retrieve()
                .bodyToMono(Object.class);
    }

    @Override
    public Mono<Object[]> getCoinDetails(List<String> coins, String currency) {
        return client
                .get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path(ApiEndpoints.CG_COIN_DETAILS)
                                .queryParam(ApiEndpoints.CG_QUERY_CURRENCY, currency)
                                .queryParam(ApiEndpoints.CG_QUERY_COINS, Utils.getCommaSeparatedCoinsFromListOfCoins(coins))
                                .build()
                )
                .retrieve()
                .bodyToMono(Object[].class);
    }

    @Override
    public Mono<CoinDetails[]> getListOfCoinsSupported() {
        return client
                .get()
                .uri(ApiEndpoints.CG_COIN_SUPPORTED)
                .retrieve()
                .bodyToMono(CoinDetails[].class);

    }

    @Override
    public Mono<String[]> getListOfCurrenciesSupported() {
        return client
                .get()
                .uri(ApiEndpoints.CG_CURRENCIES_SUPPORTED)
                .retrieve()
                .bodyToMono(String[].class);

    }

    @Override
    public Mono<Object> getCoinInfoByCoinId(String coinId) {
        String url = ApiEndpoints.CG_COIN_DETAIL_BY_ID.replace("{}", coinId);
        return client.get()
                .uri(url)
                .retrieve()
                .bodyToMono(Object.class);
    }
}
