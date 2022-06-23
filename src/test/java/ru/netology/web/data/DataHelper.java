package ru.netology.web.data;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

import java.util.Random;

public class DataHelper {
  private DataHelper() {
  }

  @Data
  @AllArgsConstructor
  public static class CardInfo {
    private String number;
    private int balance;
    private String nominal;
  }

  public static CardInfo getFirstCardInfo() {
    return new CardInfo("5559 0000 0000 0001", 10000, "RUB");
  }

  public static CardInfo getSecondCardInfo() {
    return new CardInfo("5559 0000 0000 0002", 10000, "RUB");
  }

  public static String getRandomCardInfo() {
    Faker faker = new Faker();
    return faker.business()
        .creditCardNumber();
  }

  public static int getRandomCardAmount(int cardBalance) {
    Random random = new Random();
    return random.nextInt(cardBalance);
  }

  public static int getInvalidCardAmount(int cardBalance) {
    Random random = new Random();
    return cardBalance + random.nextInt();
  }

  public static void transferCardMoney(CardInfo from, CardInfo to, int amount) {
    int fromBalance = from.getBalance() - amount;
    int toBalance = to.getBalance() + amount;

    from.setBalance(fromBalance);
    to.setBalance(toBalance);
  }

  @Value
  public static class AuthInfo {
    private String login;
    private String password;
  }

  public static AuthInfo getAuthInfo() {
    return new AuthInfo("vasya", "qwerty123");
  }

  public static AuthInfo getOtherAuthInfo(AuthInfo original) {
    return new AuthInfo("petya", "123qwerty");
  }

  @Value
  public static class VerificationCode {
    private String code;
  }

  public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
    return new VerificationCode("12345");
  }
}
