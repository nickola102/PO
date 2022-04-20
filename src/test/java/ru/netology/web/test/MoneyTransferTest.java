package ru.netology.web.test;

import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPageV1;
import ru.netology.web.page.TransferPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTransferTest {
  private String card1 = DataHelper.getCardNumber1().getNumber();
  private String card2 = DataHelper.getCardNumber2().getNumber();

  @Test
  void shouldTransferMinAmount() {
    open("http://localhost:9999");
    var loginPage = new LoginPageV1();
    var authInfo = DataHelper.getAuthInfo();
    var verificationPage = loginPage.validLogin(authInfo);
    var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
    verificationPage.validVerify(verificationCode);
    String money = "1";
    new DashboardPage().firstCard();
    new TransferPage().transferMoney(money, card2);
    int actualCard1 = new DashboardPage().getCardBalance(0);
    int actualCard2 = new DashboardPage().getCardBalance(1);
    int expectedCard1 = 10001;
    int expectedCard2 = 9999;
    assertEquals(expectedCard1, actualCard1);
    assertEquals(expectedCard2, actualCard2);
    new DashboardPage().secondCard();
    new TransferPage().defaultMoney(money, card1);
  }

  @Test
  void shouldTransferCorrectAmount() {
    open("http://localhost:9999");
    var loginPage = new LoginPageV1();
    var authInfo = DataHelper.getAuthInfo();
    var verificationPage = loginPage.validLogin(authInfo);
    var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
    verificationPage.validVerify(verificationCode);
    String money = "5000";
    new DashboardPage().firstCard();
    new TransferPage().transferMoney(money, card2);
    int actualCard1 = new DashboardPage().getCardBalance(0);
    int actualCard2 = new DashboardPage().getCardBalance(1);
    int expectedCard1 = 15000;
    int expectedCard2 = 5000;
    assertEquals(expectedCard1, actualCard1);
    assertEquals(expectedCard2, actualCard2);
    new DashboardPage().secondCard();
    new TransferPage().defaultMoney(money, card1);
  }

  @Test
  void shouldTransferNearMaxLimit() {
    open("http://localhost:9999");
    var loginPage = new LoginPageV1();
    var authInfo = DataHelper.getAuthInfo();
    var verificationPage = loginPage.validLogin(authInfo);
    var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
    verificationPage.validVerify(verificationCode);
    String money = "9999";
    new DashboardPage().firstCard();
    new TransferPage().transferMoney(money, card2);
    int actualCard1 = new DashboardPage().getCardBalance(0);
    int actualCard2 = new DashboardPage().getCardBalance(1);
    int expectedCard1 = 19999;
    int expectedCard2 = 1;
    assertEquals(expectedCard1, actualCard1);
    assertEquals(expectedCard2, actualCard2);
    new DashboardPage().secondCard();
    new TransferPage().defaultMoney(money, card1);
  }

  @Test
  void shouldTransferMax() {
    open("http://localhost:9999");
    var loginPage = new LoginPageV1();
    var authInfo = DataHelper.getAuthInfo();
    var verificationPage = loginPage.validLogin(authInfo);
    var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
    verificationPage.validVerify(verificationCode);
    String money = "10000";
    new DashboardPage().firstCard();
    new TransferPage().transferMoney(money, card2);
    int actualCard1 = new DashboardPage().getCardBalance(0);
    int actualCard2 = new DashboardPage().getCardBalance(1);
    int expectedCard1 = 20000;
    int expectedCard2 = 0;
    assertEquals(expectedCard1, actualCard1);
    assertEquals(expectedCard2, actualCard2);
    new DashboardPage().secondCard();
    new TransferPage().defaultMoney(money, card1);
  }

  @Test
  void shouldTransferOverLimit() {
    open("http://localhost:9999");
    var loginPage = new LoginPageV1();
    var authInfo = DataHelper.getAuthInfo();
    var verificationPage = loginPage.validLogin(authInfo);
    var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
    verificationPage.validVerify(verificationCode);
    String money = "10001";
    new DashboardPage().firstCard();
    new TransferPage().transferMoney(money, card2);
    int actualCard1 = new DashboardPage().getCardBalance(0);
    int actualCard2 = new DashboardPage().getCardBalance(1);
    int expectedCard1 = 20001;
    int expectedCard2 = -1;
    assertEquals(expectedCard1, actualCard1);
    assertEquals(expectedCard2, actualCard2);
    new DashboardPage().secondCard();
    new TransferPage().defaultMoney(money, card1);
  }
}

