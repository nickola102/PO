package ru.netology.web.test;

import com.codeborne.selenide.Configuration;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
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

    @BeforeEach
    void setup() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
    }

    @Test
    void shouldTransferMinAmount1() {
        var dashboardPage = new DashboardPage();
        dashboardPage.firstCard();
        val initialBalanceCard1 = dashboardPage.getFirstCardBalance();
        val initialBalanceCard2 = dashboardPage.getSecondCardBalance();
        int money = 1;
        var transferPage = new TransferPage();
        transferPage.transferMoney(money, card2);
        int actualCard1 = dashboardPage.getFirstCardBalance();
        int actualCard2 = dashboardPage.getSecondCardBalance();
        int expectedCard1 = initialBalanceCard1 + money;
        int expectedCard2 = initialBalanceCard2 - money;
        assertEquals(expectedCard1, actualCard1);
        assertEquals(expectedCard2, actualCard2);
    }

//    @Test
//    void shouldTransferMinAmount() {
//        var dashboardPage = new DashboardPage();
//        dashboardPage.firstCard();
//        String money = "1";
//        var transferPage = new TransferPage();
//        transferPage.transferMoney(money, card2);
//        int actualCard1 = dashboardPage.getCardBalance(0);
//        int actualCard2 = dashboardPage.getCardBalance(1);
//        int expectedCard1 = 10001;
//        int expectedCard2 = 9999;
//        assertEquals(expectedCard1, actualCard1);
//        assertEquals(expectedCard2, actualCard2);
//        dashboardPage.secondCard();
//        transferPage.transferMoney(money, card1);
//    }
//
//    @Test
//    void shouldTransferCorrectAmount() {
//        var dashboardPage = new DashboardPage();
//        dashboardPage.firstCard();
//        String money = "5000";
//        var transferPage = new TransferPage();
//        transferPage.transferMoney(money, card2);
//        int actualCard1 = dashboardPage.getCardBalance(0);
//        int actualCard2 = dashboardPage.getCardBalance(1);
//        int expectedCard1 = 15000;
//        int expectedCard2 = 5000;
//        assertEquals(expectedCard1, actualCard1);
//        assertEquals(expectedCard2, actualCard2);
//        dashboardPage.secondCard();
//        transferPage.transferMoney(money, card1);
//    }
//
//    @Test
//    void shouldTransferNearMaxLimit() {
//        var dashboardPage = new DashboardPage();
//        dashboardPage.firstCard();
//        String money = "9999";
//        var transferPage = new TransferPage();
//        transferPage.transferMoney(money, card2);
//        int actualCard1 = dashboardPage.getCardBalance(0);
//        int actualCard2 = dashboardPage.getCardBalance(1);
//        int expectedCard1 = 19999;
//        int expectedCard2 = 1;
//        assertEquals(expectedCard1, actualCard1);
//        assertEquals(expectedCard2, actualCard2);
//        dashboardPage.secondCard();
//        transferPage.transferMoney(money, card1);
//    }
//
//    @Test
//    void shouldTransferMax() {
//        var dashboardPage = new DashboardPage();
//        dashboardPage.firstCard();
//        String money = "10000";
//        var transferPage = new TransferPage();
//        transferPage.transferMoney(money, card2);
//        int actualCard1 = dashboardPage.getCardBalance(0);
//        int actualCard2 = dashboardPage.getCardBalance(1);
//        int expectedCard1 = 20000;
//        int expectedCard2 = 0;
//        assertEquals(expectedCard1, actualCard1);
//        assertEquals(expectedCard2, actualCard2);
//        dashboardPage.secondCard();
//        transferPage.transferMoney(money, card1);
//    }

//    @Test
//    void shouldTransferOverLimit() {
//        var dashboardPage = new DashboardPage();
//        dashboardPage.firstCard();
//        String money = "10001";
//        var transferPage = new TransferPage();
//        transferPage.failedTransfer(money, card2);
//    }
}

