package com.harsh.kryptic;

import com.harsh.kryptic.service.investments.InvestmentsService;
import com.harsh.kryptic.service.investments.ThirdPartyAPIService;
import com.harsh.kryptic.service.investments.impl.InvestmentsServiceImpl;
import com.harsh.kryptic.service.investments.impl.thirdparty.CoinGeckoAPIServiceImpl;
import com.harsh.kryptic.service.user.UserService;
import com.harsh.kryptic.service.user.impl.UserServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KrypticApplication {

	@Bean
	public ThirdPartyAPIService getThirdPartyAPIService(){
		return new CoinGeckoAPIServiceImpl();
	}

	@Bean
	public InvestmentsService getInvestmentService() {
		return new InvestmentsServiceImpl();
	}

	@Bean
	public UserService getUserService() {
		return new UserServiceImpl();
	}

	public static void main(String[] args) {
		SpringApplication.run(KrypticApplication.class, args);
	}

}
