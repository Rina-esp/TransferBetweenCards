package ru.netology.web.data;

import lombok.Value;

public class DataHelper {
  private DataHelper() {
  }

  @Value
  public static class CardInfo {
    String cardInfo;
  }

  public static CardInfo getCard1() {
    return new CardInfo("5559000000000001");
  }

  public static CardInfo getCard2() {
    return new CardInfo("5559000000000002");
  }

  public static CardInfo getInvalidCardNumber() {
    return new CardInfo("5559000000000003");
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
