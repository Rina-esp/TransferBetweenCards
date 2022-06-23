package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardCards {
  private SelenideElement heading = $("[data-test-id=dashboard]");

  private static ElementsCollection cards = $$(".list__item>div");
  private static final String balanceStart = "баланс: ";
  private static final String balanceFinish = " р.";
  private final int numberLength = 19;

  public DashboardCards() {
    heading.shouldBe(visible);
  }

  public static String getCardID(String cardNumber) {
    String cardLastFourNumbers = cardNumber.substring(15);
    return cards.find(text(cardLastFourNumbers))
        .getAttribute("data-test-id");
  }

  public static int getCardBalance(String cardNumber) {
    var cardId = getCardID(cardNumber);
    var cardElement = $(".list__item>div[data-test-id='" + cardId + "']");
    return extractBalance(cardElement.getText());
  }

  private static int extractBalance(String text) {
    var start = text.indexOf(balanceStart);
    var finish = text.indexOf(balanceFinish);
    var value = text.substring(start + balanceStart.length(), finish);
    return Integer.parseInt(value);
  }

  public static DashboardRefillCards selectCard(String cardNumber) {
    var cardId = getCardID(cardNumber);
    var cardElement = $(".list__item>div[data-test-id='" + cardId + "']");
    cardElement.$("button[data-test-id=action-deposit]").click();
    return new DashboardRefillCards();
  }

}
