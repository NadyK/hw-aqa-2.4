package ru.netology.web.data;

import lombok.Value;


public class DataHelper {
    // public static String idFirstCard="92df3f1c-a033-48e6-8390-206f6b1f56c0";
    public static String idFirstCard = "0001";
    public static String idSecondCard = "0002";
    public static int amount = 1000;
    private static String numberFirstCard = "5559000000000001";
    private static String numberSecondCard = "5559000000000002";

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

    public static AuthInfo getOtherAuthInfo(AuthInfo original) {
        return new AuthInfo("petya", "123qwerty");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {

        return new VerificationCode("12345");
    }

    @Value
    public static class DataTopUpFirstCard {

        private String amount;
        private String from2;
    }

    public static DataTopUpFirstCard getDataTopUpFirstCard() {
        return new DataTopUpFirstCard("1000", numberSecondCard);
    }

    @Value
    public static class DataTopUpSecondCard {

        private String amount;
        private String from1;
    }

    public static DataTopUpSecondCard getDataTopUpSecondCard() {
        return new DataTopUpSecondCard("1000", numberFirstCard);
    }


}


