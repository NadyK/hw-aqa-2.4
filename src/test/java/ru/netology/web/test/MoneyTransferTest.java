package ru.netology.web.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.*;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTransferTest {


   @BeforeEach
    @Test
    void shouldAuthVerif() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        loginPage = open("http://localhost:9999", LoginPageV1.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);

    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsOnFirst() {
       var yourCardsPage = new DashboardPage();
        yourCardsPage.selectOfTopUpFirstCard();
        var topUpCardPage = new TopUpCardPage();
        topUpCardPage.firstCardTopUp(new DataHelper.DataTopUpCard("1000","5559000000000001","5559000000000002"));
        assertEquals(11000, yourCardsPage.getCardBalance(DataHelper.idFirstCard));

    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsOnFirstBalanceSecond() {
       var yourCardsPage = new DashboardPage();
        assertEquals(9000, yourCardsPage.getCardBalance(DataHelper.idSecondCard));
    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsOnSecondBalanceSecond() {
       var yourCardsPage = new DashboardPage();
        yourCardsPage.selectOfTopUpSecondCard();
        var topUpCardPage2 = new TopUpCardPage();
        topUpCardPage2.secondCardTopUp(new DataHelper.DataTopUpCard("1000", "5559000000000001","5559000000000002"));
        yourCardsPage.getCardBalance(DataHelper.idSecondCard);
       assertEquals(10000, yourCardsPage.getCardBalance(DataHelper.idSecondCard));
    }
    @Test
    void shouldTransferMoneyBetweenOwnCardsOnSecondBalanceFirst() {
       var yourCardsPage = new DashboardPage();
        assertEquals(10000, yourCardsPage.getCardBalance(DataHelper.idFirstCard));
    }

}

