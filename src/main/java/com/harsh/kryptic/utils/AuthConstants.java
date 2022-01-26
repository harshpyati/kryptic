package com.harsh.kryptic.utils;

public class AuthConstants {
    public static final String SECRET = "4gfeTIHZjR";
    public static final long EXPIRATION_TIME = 900_000; // 15 minute
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER = "Authorization";
    public static final String SIGN_UP_URL = "/user";
    public static final String LOGIN_URL = "/user/login";
    public static final String HOME_URL = "/";
}
