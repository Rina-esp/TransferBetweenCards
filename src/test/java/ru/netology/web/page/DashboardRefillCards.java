package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;

public class DashboardRefillCards {
  private final SelenideElement transferAmountSum = $("[data-test-id=amount] input");
  private final SelenideElement transferFromWhere = $("[data-test-id=from] input");
  private final SelenideElement transferButton = $("[data-test-id=action-transfer]");
  private final SelenideElement errorCard = $("[data-test-id=error-notification] .notification__content");
  private final SelenideElement errorAmount = $("[data-test-id=error-notification] .notification__content");
  private final SelenideElement errorZero = $("[data-test-id=error-notification] .notification__content");

  public DashboardCards transfer(int transferAmount, DataHelper.CardInfo cardInfo) {
    transferAmountSum.setValue(String.valueOf((transferAmount)));
    transferFromWhere.setValue(String.valueOf(cardInfo));
    transferButton.click();
    return new DashboardCards();
  }

  public void cardIdError() {
    errorCard.shouldHave(exactText("Ошибка! Произошла ошибка"))
        .shouldBe(Condition.visible);
  }

  public void errorAmountLimit() {
    errorAmount.shouldHave(exactText("Ошибка! На Вашей карте недостаточно средств!"))
        .shouldBe(Condition.visible);
  }

  public void errorAmountZero() {
    errorZero.shouldHave(exactText("Ошибка! Сумма перевода должна быть больше 0!"))
        .shouldBe(Condition.visible);
  }

}
