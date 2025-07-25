package com.harsh.kryptic.service.investments.thirdparty;

import java.util.List;

public interface ThirdPartyInvestmentService {

    public List<Object> getInvestments();

    public Object ping();

    public Object getWalletInformation();
}
