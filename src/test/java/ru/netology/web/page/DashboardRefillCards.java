package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class DashboardRefillCards {
  private static SelenideElement sumField = $("[class=input__control]");
  private static SelenideElement cardField = $("[type=tel]");
  private static SelenideElement replenishButton = $("[class=button__text]");
  private SelenideElement heading = $("[data-test-id=dashboard]");


  public DashboardRefillCards() {
    heading.shouldBe(visible);
  }

  public static DashboardCards refill(String sum, DataHelper.CardInfo cardInfo, int number) {
    sumField.setValue(sum);
    if (number != 1) {
      cardField.setValue(cardInfo.getFirstCard());
    } else {
      cardField.setValue(cardInfo.getSecondCard());
    }
    replenishButton.click();
    return new DashboardCards();
  }

}
