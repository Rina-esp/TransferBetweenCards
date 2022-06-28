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
    int transferAmount = 1000;
    var dashboardCards = new DashboardCards();
    var card1BalanceStart = dashboardCards.getBalanceCard1();
    var card2BalanceStart = dashboardCards.getBalanceCard2();
    dashboardCards.pushButtonTransferCard1();
    var dashboardRefillCards = new DashboardRefillCards();
    dashboardRefillCards.transfer(transferAmount, DataHelper.getCard2());
    assertEquals(card1BalanceStart + transferAmount, dashboardCards.getBalanceCard1());
    assertEquals(card2BalanceStart - transferAmount, dashboardCards.getBalanceCard2());

  }

  @Test
  void shouldTransferMoneyBetweenRefillCard2() {
    int transferAmount = 1000;
    var dashboardCards = new DashboardCards();
    var card1BalanceStart = dashboardCards.getBalanceCard1();
    var card2BalanceStart = dashboardCards.getBalanceCard2();
    dashboardCards.pushButtonTransferCard2();
    var dashboardRefillCards = new DashboardRefillCards();
    dashboardRefillCards.transfer(transferAmount, DataHelper.getCard1());
    assertEquals(card1BalanceStart - transferAmount, dashboardCards.getBalanceCard1());
    assertEquals(card2BalanceStart + transferAmount, dashboardCards.getBalanceCard2());
  }

  @Test
  void shouldTransferMoneyBetweenRefillInvalid() {
    int transferAmount = 1000;
    var dashboardCards = new DashboardCards();
    dashboardCards.pushButtonTransferCard1();
    var dashboardRefillCards = new DashboardRefillCards();
    dashboardRefillCards.transfer(transferAmount, DataHelper.getInvalidCardNumber());
    dashboardRefillCards.cardIdError();
  }

  // issues bug

  @Test
  void shouldTransferMoneyRefillCard1onCard1() {
    var dashboardCards = new DashboardCards();
    int transferAmount = dashboardCards.getBalanceCard1();
    dashboardCards.pushButtonTransferCard1();
    var dashboardRefillCards = new DashboardRefillCards();
    dashboardRefillCards.transfer(transferAmount, DataHelper.getCard1());
    dashboardRefillCards.cardIdError();

  }


  @Test
  void shouldTransferMoneyBetweenRefillCard2Exceeding() {
    var dashboardCards = new DashboardCards();
    int transferAmount = dashboardCards.getBalanceCard1() + 1;
    dashboardCards.pushButtonTransferCard2();
    var dashboardRefillCards = new DashboardRefillCards();
    dashboardRefillCards.transfer(transferAmount, DataHelper.getCard1());
    dashboardRefillCards.errorAmountLimit();
  }

  @Test
  void shouldTransferMoneyBetweenRefillCard1Zero() {
    int transferAmount = 0;
    var dashboardCards = new DashboardCards();
    var card1BalanceStart = dashboardCards.getBalanceCard1();
    var card2BalanceStart = dashboardCards.getBalanceCard2();
    dashboardCards.pushButtonTransferCard1();
    var dashboardRefillCards = new DashboardRefillCards();
    dashboardRefillCards.transfer(transferAmount, DataHelper.getCard2());
//    assertEquals(card1BalanceStart + transferAmount, dashboardCards.getBalanceCard1());
//    assertEquals(card2BalanceStart - transferAmount, dashboardCards.getBalanceCard2());
    dashboardRefillCards.errorAmountZero();
  }

}