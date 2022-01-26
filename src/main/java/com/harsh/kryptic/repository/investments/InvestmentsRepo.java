package com.harsh.kryptic.repository.investments;

import com.harsh.kryptic.domain.Investment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvestmentsRepo extends JpaRepository<Investment,Long>{
    List<Investment> findAllByUserId(Long userId);

    void deleteAllByUserId(Long userId);

    void deleteByUserIdAndId(Long userId, Long id);

    void deleteAllByUserIdAndCoinSymbol(Long userId, String coinSymbol);

    List<Investment> findAllByUserIdAndCoinSymbol(Long userId, String coinSymbol);
}
