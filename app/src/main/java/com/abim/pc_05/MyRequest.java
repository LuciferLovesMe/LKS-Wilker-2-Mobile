package com.abim.pc_05;

public class MyRequest {
    private static String baseURL = "http://192.168.71.182:9033/";
    private static String loginURL = "api/login2";
    private static String vaksinURL = "api/vaksin/";

    public static String getBaseURL() {
        return baseURL;
    }

    public static String getLoginURL() {
        return getBaseURL() + loginURL;
    }

    public static String getVaksinURL() {
        return getBaseURL() + vaksinURL;
    }
}
