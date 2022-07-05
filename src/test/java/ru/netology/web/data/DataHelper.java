package ru.netology.web.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;


public class DataHelper {
    public static String idFirstCard = "0001";
    public static String idSecondCard = "0002";

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
    @AllArgsConstructor
    @Data
    public static class DataTopUpCard {
        private String amount;
        private String from1;
        private String from2;
    }
}



