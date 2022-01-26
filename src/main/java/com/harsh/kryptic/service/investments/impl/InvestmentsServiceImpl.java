package com.harsh.kryptic.service.investments.impl;

import com.harsh.kryptic.domain.Investment;
import com.harsh.kryptic.domain.InvestmentLite;
import com.harsh.kryptic.domain.InvestmentStatistics;
import com.harsh.kryptic.repository.investments.InvestmentsRepo;
import com.harsh.kryptic.service.investments.InvestmentsService;
import com.harsh.kryptic.service.investments.ThirdPartyAPIService;
import com.harsh.kryptic.utils.Utils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

public class InvestmentsServiceImpl implements InvestmentsService {
    @Autowired
    private InvestmentsRepo investmentRepo;

    @Autowired
    private ThirdPartyAPIService thirdPartyAPIService;

    @Override
    public Investment addInvestment(Investment investments, Long userId) {
        return investmentRepo.saveAndFlush(investments);
    }

    @Override
    public List<Investment> getAllInvestments(Long userId) {
        return investmentRepo.findAllByUserId(userId);
    }

    @Override
    public Investment getInvestmentById(Long id) {
        var investment = investmentRepo.findById(id);
        return investment.orElse(null);
    }

    @Override
    public List<Investment> getInvestmentsByCoin(Long userId, String coinSymbol) {
        return investmentRepo.findAllByUserIdAndCoinSymbol(userId, coinSymbol);
    }

    @Override
    public Investment updateInvestment(Investment investment, Investment dbInvestment) {
        dbInvestment.setAmountInvested(investment.getAmountInvested());
        dbInvestment.setInvestmentDate(investment.getInvestmentDate());
        dbInvestment.setQuantityInvested(investment.getQuantityInvested());
        return investmentRepo.saveAndFlush(dbInvestment);
    }

    @Override
    @Transactional
    public void deleteInvestment(Long userId, Long id) {
        investmentRepo.deleteByUserIdAndId(userId, id);
    }

    @Override
    @Transactional
    public void deleteAllInvestmentsForUser(Long userId) {
        investmentRepo.deleteAllByUserId(userId);
    }

    @Override
    @Transactional
    public void deleteInvestmentsByCoin(Long userId, String coinSymbol) {
        investmentRepo.deleteAllByUserIdAndCoinSymbol(userId, coinSymbol);
    }

    public InvestmentStatistics getInvestmentStats(Long userId) {
        List<Investment> userInvestments = getAllInvestments(userId);
        InvestmentStatistics.InvestmentStatisticsBuilder statistics = InvestmentStatistics.builder();
        Set<String> coinsInvested = userInvestments.stream()
                .map(Investment::getCoinSymbol).collect(Collectors.toSet());
        Object marketValue = thirdPartyAPIService.getCurrentCoinPrice(new ArrayList<>(coinsInvested), "inr").block();
        JSONObject marketJSONData = Utils.getJsonObjectFromObject(marketValue);
        Map<String, InvestmentLite> coinsAndInvestedValueMap = new HashMap<>();
        Map<String, Double> currentInvestments = new HashMap<>();
        Map<String, Double> marketValues = new HashMap<>();
        userInvestments
                .forEach(userInvestment -> {
                    String coinSymbol = userInvestment.getCoinSymbol();
                    if (coinsAndInvestedValueMap.get(coinSymbol) != null) {
                        InvestmentLite investment = coinsAndInvestedValueMap.get(coinSymbol);
                        Double totalInvestment = userInvestment.getAmountInvested() + investment.getAmount();
                        Double totalQuantity = userInvestment.getQuantityInvested() + investment.getQuantity();
                        investment.setQuantity(totalQuantity);
                        investment.setAmount(totalInvestment);
                        coinsAndInvestedValueMap.put(coinSymbol, investment);
                    } else {
                        InvestmentLite.InvestmentLiteBuilder investment = InvestmentLite.builder();
                        investment
                                .amount(userInvestment.getAmountInvested())
                                .quantity(userInvestment.getQuantityInvested());
                        coinsAndInvestedValueMap.put(coinSymbol, investment.build());
                    }
                });

        coinsInvested
                .forEach(coin -> {
                    if (marketJSONData != null) {
                        marketValues.put(coin, marketJSONData.getJSONObject(coin).getDouble("inr"));
                    }
                });

        coinsInvested
                .forEach(coin -> currentInvestments.put(coin, ((marketValues.get(coin) * coinsAndInvestedValueMap.get(coin).getQuantity()) - coinsAndInvestedValueMap.get(coin).getQuantity())));

        return statistics
                .marketPrice(marketValues)
                .currentGains(currentInvestments)
                .investedAt(coinsAndInvestedValueMap)
                .build();
    }
}
