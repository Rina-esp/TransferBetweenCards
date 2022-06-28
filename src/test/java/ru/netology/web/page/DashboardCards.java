package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class DashboardCards {
  private SelenideElement heading = $("[data-test-id=dashboard]");
  private final SelenideElement card1 = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']");
  private final SelenideElement card2 = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']");
  private final SelenideElement card1Button = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0'] > .button");
  private final SelenideElement card2Button = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d'] > .button");


  public DashboardCards() {
    heading.shouldBe(visible);
  }

  public int getBalanceCard1() {
    return Integer.parseInt(card1.getText()
        .substring(29)
        .replace(" р.\nПополнить", ""));
  }

  public int getBalanceCard2() {
    return Integer.parseInt(card2.getText()
        .substring(29)
        .replace(" р.\nПополнить", ""));
  }

  public void pushButtonTransferCard1() {
    card1Button.click();
  }

  public void pushButtonTransferCard2() {
    card2Button.click();
  }
}
