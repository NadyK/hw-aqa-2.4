package ru.netology.web.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.*;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.web.data.DataHelper.idFirstCard;
import static ru.netology.web.data.DataHelper.idSecondCard;

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
    void shouldTransferMoneyBetweenOwnCards() {
        String amount = "11000";
        int amountInt = Integer.parseInt(amount);
        var yourCardsPage = new DashboardPage();
        int startBalance1 = yourCardsPage.getCardBalance(idFirstCard);
        int startBalance2 = yourCardsPage.getCardBalance(idSecondCard);
        yourCardsPage.selectOfTopUpFirstCard();
        var topUpCardPage = new TopUpCardPage();
        topUpCardPage.firstCardTopUp(new DataHelper.DataTopUpCard(amount, "5559000000000001", "5559000000000002"));
        assertEquals((startBalance1 + amountInt), yourCardsPage.getCardBalance(idFirstCard));
        System.out.println("Ожидаемый баланс пополняемой карты: " + (startBalance1 + amountInt));
        System.out.println("Фактический баланс пополняемой карты: " + (yourCardsPage.getCardBalance(idFirstCard)));
        assertEquals((startBalance2 - amountInt), yourCardsPage.getCardBalance(idSecondCard));
        System.out.println("Ожидаемый баланс карты списания: " + (startBalance2 - amountInt));
        System.out.println("Фактический баланс карты списания: " + (yourCardsPage.getCardBalance(idSecondCard)));

        yourCardsPage.selectOfTopUpSecondCard();
        topUpCardPage.secondCardTopUp(new DataHelper.DataTopUpCard(amount, "5559000000000001", "5559000000000002"));
        yourCardsPage.getCardBalance(idSecondCard);
        assertEquals((startBalance1), yourCardsPage.getCardBalance(idFirstCard));
        assertEquals((startBalance2), yourCardsPage.getCardBalance(idSecondCard));

    }

//    @Test
//    //BetweenOwnCardsOnFirstBalanceSecond
//    void shouldTransferMoney2() {
//       var yourCardsPage = new DashboardPage();
//        assertEquals(9000, yourCardsPage.getCardBalance(DataHelper.idSecondCard));
//    }
//
//    @Test
//    //BetweenOwnCardsOnSecondBalanceSecond
//    void shouldTransferMoney3() {
//       var yourCardsPage = new DashboardPage();
//        yourCardsPage.selectOfTopUpSecondCard();
//        var topUpCardPage2 = new TopUpCardPage();
//        topUpCardPage2.secondCardTopUp(new DataHelper.DataTopUpCard("1000", "5559000000000001","5559000000000002"));
//        yourCardsPage.getCardBalance(DataHelper.idSecondCard);
//       assertEquals(10000, yourCardsPage.getCardBalance(DataHelper.idSecondCard));
//       assertEquals(10000, yourCardsPage.getCardBalance(DataHelper.idFirstCard));
//    }
//    @Test
//    //BetweenOwnCardsOnSecondBalanceFirst
//    void shouldTransferMoney4() {
//       var yourCardsPage = new DashboardPage();
//        assertEquals(10000, yourCardsPage.getCardBalance(DataHelper.idFirstCard));
//    }

}

