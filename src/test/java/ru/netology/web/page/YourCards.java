package ru.netology.web.page;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class YourCards {
    public void choiceOfRechargeableCard (){
    $( "[data-test-id= 92df3f1c-a033-48e6-8390-206f6b1f56c0]");
    $("[data-test-id=action-deposit]").click();
    }
}
