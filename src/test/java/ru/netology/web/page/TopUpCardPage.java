package ru.netology.web.page;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Timeout;
import org.openqa.selenium.Keys;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class TopUpCardPage {

    public TopUpCardPage firstCardTopUp(DataHelper.DataTopUpCard info) {
        $("[data-test-id= amount] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id= amount] input").setValue(info.getAmount());
        $("[data-test-id= from] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id= from] input").setValue(info.getFrom2());
        $("[data-test-id=action-transfer]").click();
        return new TopUpCardPage();
    }

    public TopUpCardPage secondCardTopUp(DataHelper.DataTopUpCard info) {
        $("[data-test-id= amount] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id= amount] input").setValue(info.getAmount());
        $("[data-test-id= from] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id= from] input").setValue(info.getFrom1());
        $("[data-test-id=action-transfer]").click();
        return new TopUpCardPage();
    }
}

