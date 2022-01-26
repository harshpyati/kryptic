package com.harsh.kryptic.utils;

public class ApiEndpoints {
    public static final String CG_PRICE_LIST = "/simple/price";
    public static final String CG_COIN_DETAILS = "/coins/markets";
    public static final String CG_COIN_SUPPORTED = "/coins/list";
    public static final String CG_CURRENCIES_SUPPORTED = "/simple/supported_vs_currencies";
    public static final String CG_COIN_DETAIL_BY_ID = "/coins/{}";
    public static final String CG_COIN_STATUS_UPDATES = "/coins/{}/status_updates";

    // query params
    public static final String CG_QUERY_COINS = "ids";
    public static final String CG_QUERY_CURRENCIES = "vs_currencies";
    public static final String CG_QUERY_CURRENCY = "vs_currency";
}
