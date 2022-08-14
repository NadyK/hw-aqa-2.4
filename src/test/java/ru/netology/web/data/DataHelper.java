package ru.netology.web.data;

import lombok.Value;


public class DataHelper {
    public static String idFirstCard = "0001";
    public static String idSecondCard = "0002";
    public static String cardNumFrom1 = "5559000000000001";
    public static String cardNumFrom2 = "5559000000000002";


    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }
    public static AuthInfo getOtherAuthInfo(AuthInfo original) {return new AuthInfo("petya", "123qwerty");}

    @Value
    public static class VerificationCode { private String code;}

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {return new VerificationCode("12345");}

    @Value
      public static class DataTopUpCard {
        private String amount;
        private String from;
    }

}



