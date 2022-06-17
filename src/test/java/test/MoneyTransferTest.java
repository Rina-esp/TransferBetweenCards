package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardCards;
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
    var cardInfo = DataHelper.getCardInfo();
    var userCard = new DashboardCards();
    int firstBalance = userCard.getFirstCardBalance();
    int secondBalance = userCard.getSecondCardBalance();
    int difference = 257;
    var refillCard = userCard.refillFirst();
    refillCard.refill(Integer.toString(difference), cardInfo, 1);
    assertEquals(firstBalance + difference, userCard.getFirstCardBalance());
    assertEquals(secondBalance - difference, userCard.getSecondCardBalance());
  }

  @Test
  void shouldTransferMoneyBetweenRefillCard2() {
    var cardInfo = DataHelper.getCardInfo();
    var userCard = new DashboardCards();
    int firstBalance = userCard.getFirstCardBalance();
    int secondBalance = userCard.getSecondCardBalance();
    int difference = 55;
    var refillCard = userCard.refillSecond();
    refillCard.refill(Integer.toString(difference), cardInfo, 2);
    assertEquals(firstBalance - difference, userCard.getFirstCardBalance());
    assertEquals(secondBalance + difference, userCard.getSecondCardBalance());
  }


  // Negative test
  @Test
  void shouldTransferMoneyBetweenRefillCard1Exceeding() {
    var cardInfo = DataHelper.getCardInfo();
    var userCard = new DashboardCards();
    int firstBalance = userCard.getFirstCardBalance();
    int secondBalance = userCard.getSecondCardBalance();
    int difference = 500000;
    var refillCard = userCard.refillFirst();
    refillCard.refill(Integer.toString(difference), cardInfo, 1);
    assertEquals(firstBalance + difference, userCard.getFirstCardBalance());
    assertEquals(secondBalance - difference, userCard.getSecondCardBalance());
  }

  @Test
  void shouldTransferMoneyBetweenRefillCard2Exceeding() {
    var cardInfo = DataHelper.getCardInfo();
    var userCard = new DashboardCards();
    int firstBalance = userCard.getFirstCardBalance();
    int secondBalance = userCard.getSecondCardBalance();
    int difference = 55000;
    var refillCard = userCard.refillSecond();
    refillCard.refill(Integer.toString(difference), cardInfo, 2);
    assertEquals(firstBalance - difference, userCard.getFirstCardBalance());
    assertEquals(secondBalance + difference, userCard.getSecondCardBalance());
  }

  @Test
  void shouldTransferMoneyRefillCard1onCard1() {
    var cardInfo = DataHelper.getCardInfo();
    var userCard = new DashboardCards();
    int firstBalance = userCard.getFirstCardBalance();
    int difference = 50;
    var refillCard = userCard.refillFirst();
    refillCard.refill(Integer.toString(difference), cardInfo, 1);
    assertEquals(firstBalance + difference, userCard.getFirstCardBalance());
  }

  @Test
  void shouldTransferMoneyBetweenRefillCard1Zero() {
    var cardInfo = DataHelper.getCardInfo();
    var userCard = new DashboardCards();
    int firstBalance = userCard.getFirstCardBalance();
    int secondBalance = userCard.getSecondCardBalance();
    int difference = 0;
    var refillCard = userCard.refillFirst();
    refillCard.refill(Integer.toString(difference), cardInfo, 1);
    assertEquals(firstBalance + difference, userCard.getFirstCardBalance());
    assertEquals(secondBalance - difference, userCard.getSecondCardBalance());
  }
}
