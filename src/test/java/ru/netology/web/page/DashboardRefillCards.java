package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class DashboardRefillCards {
  private static SelenideElement button = $("[data-test-id=action-transfer]");
  private static SelenideElement error = $("[data-test-id=error-notification]");

  public static DashboardCards fillInfo(String cardNumber, Integer amount) {
    $("[data-test-id=amount] input").setValue(String.valueOf(amount));
    $("[data-test-id=from] input").setValue(cardNumber);
    button.click();
    return new DashboardCards();
  }

  public static void checkError() { error.shouldBe(text("Ошибка! Произошла ошибка")); }

}
