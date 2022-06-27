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
    $(withText(DataHelper.idFirstCard)).$("button").click();
    //$$("[data-test-id=action-deposit]").first().click();
  }

  public void selectOfTopUpSecondCard() {
    $(withText(DataHelper.idSecondCard)).$("button").click();
    //$$("[data-test-id=action-deposit]").last().click();

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
//    public int getInitialCardBalance(String id) {
//        int initialCardBalance =YourCardsPage.getCardBalance(id) - new DataHelper.DataTopUpFirstCard(amount);
//    }


  public int getCardBalance(String id) {
    val text = cards.find(Condition.ownText(id)).text();
    return extractBalance(text);
//val text1=cards.first().text();
    //               val text = cards.get(Integer.parseInt(id)).text();


  }

  // TODO: перебрать все карты и найти по атрибуту data-test-id
//        val cardsById = cards.stream().filter(card -> id.equals(card.attr("data-test-id"))).collect(Collectors.toList());
//        if (!cardsById.isEmpty()) {
//            String text = cardsById.get(0).text();
//            return extractBalance(text);
//        }
//
//        //   throw new RuntimeException("Карты с data-test-id=" + id + " не существует");
//        return extractBalance(text);
//    }



}
//        val text = cards.find(Condition.id(id)).text();
//        return extractBalance(text);
//val text1=cards.first().text();
//               val text = cards.get(Integer.parseInt(id)).text();


// массив
//       String[] textCards = cards.texts().toArray(new String[0]);
//      String[] extractText = new String[2];
//        for (int i = 0; i < extractText.length; i++) {
//           String text = textCards[i];
//           val start = text.indexOf(balanceStart);
//           val finish = text.indexOf(balanceFinish);
//           val value = text.substring(start + balanceStart.length(), finish);
//           extractText[i] = value;
//           }
//        int[] balances = Arrays.stream(extractText).mapToInt(Integer::parseInt).toArray();
//        for (int i = 0; i < balances.length; i++) {
//          System.out.println(balances[i]);
//       }






