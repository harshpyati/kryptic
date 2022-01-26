package com.harsh.kryptic.controller.portfolio;

import com.harsh.kryptic.domain.Investment;

import com.harsh.kryptic.domain.InvestmentStatistics;
import com.harsh.kryptic.exceptions.NoRecordsFoundException;
import com.harsh.kryptic.service.investments.InvestmentsService;
import com.harsh.kryptic.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.harsh.kryptic.utils.Utils.isNull;
import static com.harsh.kryptic.utils.Utils.isNullOrEmpty;

@RestController
@RequestMapping("/investments")
public class InvestmentsController {

    @Autowired
    InvestmentsService investmentsService;

    @PostMapping("/{userId}")
    public ResponseEntity<?> addInvestment(@RequestBody Investment investment, @PathVariable Long userId) {
        return ResponseEntity.ok().body(investmentsService.addInvestment(investment, userId));
    }

    @PutMapping("/{userId}/{investmentId}")
    public ResponseEntity<?> updateInvestment(
            @PathVariable Long userId,
            @PathVariable Long investmentId,
            @RequestBody Investment investment
    ) {
        Investment dbInvestment = investmentsService.getInvestmentById(investmentId);
        if (isNull(dbInvestment)){
            throw new NoRecordsFoundException("No investments found for this user with id " + investmentId);
        }
        return ResponseEntity.ok().body(investmentsService.updateInvestment(investment, dbInvestment));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getInvestments(
            @PathVariable Long userId
    ) {
        List<Investment> investments = investmentsService.getAllInvestments(userId);
        if (isNullOrEmpty(investments)){
            throw new NoRecordsFoundException("No Investments found for this user");
        }
        return ResponseEntity.ok(investments);
    }

    @GetMapping("/{userId}/{coin}")
    public ResponseEntity<?> getInvestmentsByCoin(
            @PathVariable Long userId,
            @PathVariable String coin
    ) {
        List<Investment> investmentsByCoin = investmentsService.getInvestmentsByCoin(userId, coin);
        if (isNullOrEmpty(investmentsByCoin)){
            throw new NoRecordsFoundException("No investments in " + coin + " found for this user");
        }
        return ResponseEntity.ok(investmentsByCoin);
    }

    @GetMapping("/investment/{investmentId}")
    public ResponseEntity<?> getInvestmentById(
            @PathVariable Long investmentId
    ) {
        Investment investment = investmentsService.getInvestmentById(investmentId);
        if (isNull(investment)){
            throw new NoRecordsFoundException("No investment found for this id");
        }
        return ResponseEntity.ok(investment);
    }

    @DeleteMapping("/{userId}/investment/{investmentId}")
    public void deleteInvestmentById(
            @PathVariable Long userId,
            @PathVariable Long investmentId
    ) {
        Investment investment = investmentsService.getInvestmentById(investmentId);
        if (isNull(investment)){
            throw new NoRecordsFoundException("No investment found for this id");
        }
        investmentsService.deleteInvestment(userId, investmentId);
    }

    @DeleteMapping("/{userId}")
    public void deleteAllInvestments(
            @PathVariable Long userId
    ) {
        investmentsService.deleteAllInvestmentsForUser(userId);
    }

    @DeleteMapping("/{userId}/coin/{coin}")
    public void deleteInvestmentsByCoin(
            @PathVariable Long userId,
            @PathVariable String coin
    ) {
        investmentsService.deleteInvestmentsByCoin(userId, coin);
    }

    @GetMapping("/statistics/{userId}")
    public ResponseEntity<?> getInvestmentStats(
            @PathVariable Long userId
    ){
        return ResponseEntity.ok(investmentsService.getInvestmentStats(userId));
    }
}
