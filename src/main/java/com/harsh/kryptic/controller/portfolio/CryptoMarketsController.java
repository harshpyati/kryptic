package com.harsh.kryptic.controller.portfolio;

import com.harsh.kryptic.domain.thirdparty.coingecko.CoinDetails;
import com.harsh.kryptic.service.investments.ThirdPartyAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/markets")
public class CryptoMarketsController {
    @Autowired
    private ThirdPartyAPIService thirdPartyAPIService;

    @GetMapping("/current/price")
    public Mono<Object> getCurrentPrice(
            @RequestParam String currency,
            @RequestParam final List<String> coins
    ) {
        return thirdPartyAPIService.getCurrentCoinPrice(coins, currency);
    }

    @GetMapping("/coins/details")
    public Mono<Object[]> getCoinDetails(
            @RequestParam String currency,
            @RequestParam final List<String> coins
    ) {
        return thirdPartyAPIService.getCoinDetails(coins, currency);
    }

    @GetMapping("/coins")
    public Mono<CoinDetails[]> getCoinsSupported() {
        return thirdPartyAPIService.getListOfCoinsSupported();
    }

    @GetMapping("/currencies")
    public Mono<String[]> getCurrenciesSupported() {
        return thirdPartyAPIService.getListOfCurrenciesSupported();
    }

    @GetMapping("/coin/{coinId}")
    public Mono<Object> getCoinDetailById(@PathVariable String coinId){
        return thirdPartyAPIService.getCoinInfoByCoinId(coinId);
    }
}
