package com.harsh.kryptic.service.investments;

import java.util.List;
import com.harsh.kryptic.domain.Investment;
import com.harsh.kryptic.domain.InvestmentStatistics;

public interface InvestmentsService {
    Investment addInvestment(Investment investments, Long userId);

    List<Investment> getAllInvestments(Long userId);

    Investment getInvestmentById(Long id);

    List<Investment> getInvestmentsByCoin(Long userId,String coinSymbol);

    Investment updateInvestment(Investment investment, Investment dbInvestment);

    void deleteInvestment(Long id, Long userId);

    void deleteAllInvestmentsForUser(Long userId);

    void deleteInvestmentsByCoin(Long userId, String coinSymbol);

    InvestmentStatistics getInvestmentStats(Long userId);
}
