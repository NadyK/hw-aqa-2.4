package ru.netology.web.page;

import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class TopUpCardPage {
    //public VerificationPage validLogin(DataHelper.AuthInfo info) {
    public TopUpCardPage card1 (DataHelper.CardInfo info){
        $("[data-test-id= amount] input").setValue(info.getAmount());
        $("[data-test-id= from] input").setValue(info.getFrom2());
        $("[data-test-id=action-transfer]").click();

        return new TopUpCardPage();
    }

}
