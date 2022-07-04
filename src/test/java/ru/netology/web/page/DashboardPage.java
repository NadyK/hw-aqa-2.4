package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public void selectOfTopUpFirstCard() {
        $("[data-test-id=action-reload]").click();
        $(withText(DataHelper.idFirstCard)).$("button").click();
    }

    public void selectOfTopUpSecondCard() {
        $("[data-test-id=action-reload]").click();
        $(withText(DataHelper.idSecondCard)).$("button").click();
    }

    private static ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";


    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);

        return Integer.parseInt(value);
    }

    public int getCardBalance(String id) {
        val text = cards.find(Condition.ownText(id)).text();
        return extractBalance(text);
    }
}







