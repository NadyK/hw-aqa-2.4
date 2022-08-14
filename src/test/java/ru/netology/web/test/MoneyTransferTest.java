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
        String amount = "1000";
        int amountInt = Integer.parseInt(amount);
        var yourCardsPage = new DashboardPage();
        int startBalance1 = yourCardsPage.getCardBalance(idFirstCard);
        int startBalance2 = yourCardsPage.getCardBalance(idSecondCard);
        yourCardsPage.selectOfTopUpFirstCard();
        var topUpCardPage = new TopUpCardPage();
        topUpCardPage.cardTopUp(new DataHelper.DataTopUpCard(amount, DataHelper.cardNumFrom2));
        assertEquals((startBalance1 + amountInt), yourCardsPage.getCardBalance(idFirstCard));
        assertEquals((startBalance2 - amountInt), yourCardsPage.getCardBalance(idSecondCard));
        yourCardsPage.selectOfTopUpSecondCard();
        topUpCardPage.cardTopUp(new DataHelper.DataTopUpCard(amount, DataHelper.cardNumFrom1));
        yourCardsPage.getCardBalance(idSecondCard);
        assertEquals((startBalance1), yourCardsPage.getCardBalance(idFirstCard));
        assertEquals((startBalance2), yourCardsPage.getCardBalance(idSecondCard));

    }
    @Test
    void shouldTransferMoneyBetweenOwnCardsErrorMessage() {
        String amount = "11000";
        int amountInt = Integer.parseInt(amount);
        var yourCardsPage = new DashboardPage();
        int startBalance1 = yourCardsPage.getCardBalance(idFirstCard);
        int startBalance2 = yourCardsPage.getCardBalance(idSecondCard);
        yourCardsPage.selectOfTopUpFirstCard();
        var topUpCardPage = new TopUpCardPage();
        topUpCardPage.cardTopUp(new DataHelper.DataTopUpCard(amount, DataHelper.cardNumFrom2));
        yourCardsPage.errorMessage();
        assertEquals((startBalance1), yourCardsPage.getCardBalance(idFirstCard));
        assertEquals((startBalance2), yourCardsPage.getCardBalance(idSecondCard));
    }
}

