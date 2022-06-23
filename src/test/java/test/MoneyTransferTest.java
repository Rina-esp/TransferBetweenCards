package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardCards;
import ru.netology.web.page.DashboardRefillCards;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {
  public DashboardCards dashboardCards;

  @BeforeEach
  public void setUp() {
    open("http://localhost:9999");
    var loginPage = new LoginPage();
    var authInfo = DataHelper.getAuthInfo();
    var verificationPage = loginPage.validLogin(authInfo);
    DataHelper.VerificationCode verificationCode = DataHelper.getVerificationCodeFor(authInfo);
    verificationPage.validVerify(verificationCode);
  }

  // Positive test
  @Test
  void shouldTransferMoneyBetweenRefillCard1() {
    var firstCardInfo = DataHelper.getFirstCardInfo();
    firstCardInfo.setBalance(dashboardCards.getCardBalance(firstCardInfo.getNumber()));
    var secondCardInfo = DataHelper.getSecondCardInfo();
    secondCardInfo.setBalance(dashboardCards.getCardBalance(secondCardInfo.getNumber()));
    var amount = DataHelper.getRandomCardAmount(secondCardInfo.getBalance());
    var DashboardRefillCards = dashboardCards.selectCard(firstCardInfo.getNumber());
    var updaterDashboardCards = DashboardRefillCards.fillInfo(secondCardInfo.getNumber(), amount);
    DataHelper.transferCardMoney(secondCardInfo, firstCardInfo, amount);
    assertEquals(updaterDashboardCards.getCardBalance(firstCardInfo.getNumber()), firstCardInfo.getBalance());
    assertEquals(updaterDashboardCards.getCardBalance(secondCardInfo.getNumber()), secondCardInfo.getBalance());
  }

  @Test
  void shouldTransferMoneyBetweenRefillCard2() {
    var firstCardInfo = DataHelper.getFirstCardInfo();
    firstCardInfo.setBalance(dashboardCards.getCardBalance(firstCardInfo.getNumber()));
    var secondCardInfo = DataHelper.getSecondCardInfo();
    secondCardInfo.setBalance(dashboardCards.getCardBalance(secondCardInfo.getNumber()));
    var amount = DataHelper.getRandomCardAmount(secondCardInfo.getBalance());
    var DashboardRefillCards = dashboardCards.selectCard(secondCardInfo.getNumber());
    var updaterDashboardCards = DashboardRefillCards.fillInfo(firstCardInfo.getNumber(), amount);
    DataHelper.transferCardMoney(firstCardInfo, secondCardInfo, amount);
    assertEquals(updaterDashboardCards.getCardBalance(firstCardInfo.getNumber()), firstCardInfo.getBalance());
    assertEquals(updaterDashboardCards.getCardBalance(secondCardInfo.getNumber()), secondCardInfo.getBalance());
  }

  @Test
  void shouldTransferMoneyBetweenEmptyCards() {
    var firstCardInfo = DataHelper.getFirstCardInfo();
    firstCardInfo.setBalance(dashboardCards.getCardBalance(firstCardInfo.getNumber()));
    var DashboardRefillCards = dashboardCards.selectCard(firstCardInfo.getNumber());
    DashboardRefillCards.fillInfo(null, null);
    DashboardRefillCards.checkError();
  }

  // issues bug

  @Test
  void shouldTransferMoneyRefillCard1onCard1() {
    var firstCardInfo = DataHelper.getFirstCardInfo();
    firstCardInfo.setBalance(dashboardCards.getCardBalance(firstCardInfo.getNumber()));
    var amount = DataHelper.getRandomCardAmount(firstCardInfo.getBalance());
    DashboardRefillCards.fillInfo(firstCardInfo.getNumber(), amount);
    DashboardRefillCards.checkError();
  }

  @Test
  void shouldTransferMoneyBetweenRefillCard2Exceeding() {
    var firstCardInfo = DataHelper.getFirstCardInfo();
    firstCardInfo.setBalance(dashboardCards.getCardBalance(firstCardInfo.getNumber()));
    var secondCardInfo = DataHelper.getSecondCardInfo();
    secondCardInfo.setBalance(dashboardCards.getCardBalance(secondCardInfo.getNumber()));
    var amount = DataHelper.getInvalidCardAmount(firstCardInfo.getBalance());
    var DashboardRefillCards = dashboardCards.selectCard(firstCardInfo.getNumber());
    DashboardRefillCards.fillInfo(secondCardInfo.getNumber(), amount);
    DashboardRefillCards.checkError();
  }

  @Test
  void shouldTransferMoneyBetweenRefillCard1Zero() {
    var firstCardInfo = DataHelper.getFirstCardInfo();
    firstCardInfo.setBalance(dashboardCards.getCardBalance(firstCardInfo.getNumber()));
    var secondCardInfo = DataHelper.getSecondCardInfo();
    secondCardInfo.setBalance(dashboardCards.getCardBalance(secondCardInfo.getNumber()));
    var amount = 0;
    DashboardRefillCards.fillInfo(firstCardInfo.getNumber(), amount);
    DashboardRefillCards.checkError();
  }


}
