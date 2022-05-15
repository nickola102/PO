package ru.netology.web.test;

import com.codeborne.selenide.Configuration;
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

//    @Test
//    void shouldTransferZero() {
//        var dashboardPage = new DashboardPage();
//        int initialBalanceCard1 = dashboardPage.getCardBalance(0);
//        int initialBalanceCard2 = dashboardPage.getCardBalance(1);
//        dashboardPage.firstCard();
//        int money = 0;
//        var transferPage = new TransferPage();
//        transferPage.transferMoney(money, card2);
//        int actualCard1 = dashboardPage.getCardBalance(0);
//        int actualCard2 = dashboardPage.getCardBalance(1);
//        int expectedCard1 = initialBalanceCard1 + money;
//        int expectedCard2 = initialBalanceCard2 - money;
//        assertEquals(expectedCard1, actualCard1);
//        assertEquals(expectedCard2, actualCard2);
//    }
//
//    @Test
//    void shouldTransferMinAmount() {
//        var dashboardPage = new DashboardPage();
//        int initialBalanceCard1 = dashboardPage.getCardBalance(0);
//        int initialBalanceCard2 = dashboardPage.getCardBalance(1);
//        dashboardPage.firstCard();
//        int money = 1;
//        var transferPage = new TransferPage();
//        transferPage.transferMoney(money, card2);
//        int actualCard1 = dashboardPage.getCardBalance(0);
//        int actualCard2 = dashboardPage.getCardBalance(1);
//        int expectedCard1 = initialBalanceCard1 + money;
//        int expectedCard2 = initialBalanceCard2 - money;
//        assertEquals(expectedCard1, actualCard1);
//        assertEquals(expectedCard2, actualCard2);
//    }
//
//
//    @Test
//    void shouldTransferCorrectAmount() {
//        var dashboardPage = new DashboardPage();
//        int initialBalanceCard1 = dashboardPage.getCardBalance(0);
//        int initialBalanceCard2 = dashboardPage.getCardBalance(1);
//        dashboardPage.firstCard();
//        int money = 5000;
//        var transferPage = new TransferPage();
//        transferPage.transferMoney(money, card2);
//        int actualCard1 = dashboardPage.getCardBalance(0);
//        int actualCard2 = dashboardPage.getCardBalance(1);
//        int expectedCard1 = initialBalanceCard1 + money;
//        int expectedCard2 = initialBalanceCard2 - money;
//        assertEquals(expectedCard1, actualCard1);
//        assertEquals(expectedCard2, actualCard2);
//    }
//
//    @Test
//    void shouldTransferNearMaxLimit() {
//        var dashboardPage = new DashboardPage();
//        int initialBalanceCard1 = dashboardPage.getCardBalance(0);
//        int initialBalanceCard2 = dashboardPage.getCardBalance(1);
//        dashboardPage.firstCard();
//        int money = initialBalanceCard1 - 1;
//        var transferPage = new TransferPage();
//        transferPage.transferMoney(money, card2);
//        int actualCard1 = dashboardPage.getCardBalance(0);
//        int actualCard2 = dashboardPage.getCardBalance(1);
//        int expectedCard1 = initialBalanceCard1 + money;
//        int expectedCard2 = initialBalanceCard2 - money;
//        assertEquals(expectedCard1, actualCard1);
//        assertEquals(expectedCard2, actualCard2);
//    }
//
//    @Test
//    void shouldTransferMax() {
//        var dashboardPage = new DashboardPage();
//        int initialBalanceCard1 = dashboardPage.getCardBalance(0);
//        int initialBalanceCard2 = dashboardPage.getCardBalance(1);
//        dashboardPage.firstCard();
//        int money = initialBalanceCard2;
//        var transferPage = new TransferPage();
//        transferPage.transferMoney(money, card2);
//        int actualCard1 = dashboardPage.getCardBalance(0);
//        int actualCard2 = dashboardPage.getCardBalance(1);
//        int expectedCard1 = initialBalanceCard1 + money;
//        int expectedCard2 = initialBalanceCard2 - money;
//        assertEquals(expectedCard1, actualCard1);
//        assertEquals(expectedCard2, actualCard2);
//    }

    @Test
    void shouldTransferOverLimit() {
        var dashboardPage = new DashboardPage();
        int initialBalanceCard1 = dashboardPage.getCardBalance(0);
        int initialBalanceCard2 = dashboardPage.getCardBalance(1);
        dashboardPage.firstCard();
        int money = initialBalanceCard2 + 1;
        var transferPage = new TransferPage();
        transferPage.transferMoney(money, card2);
        int actualCard1 = dashboardPage.getCardBalance(0);
        int actualCard2 = dashboardPage.getCardBalance(1);
        int expectedCard1 = initialBalanceCard1 + money;
        int expectedCard2 = initialBalanceCard2 - money;
        assertEquals(expectedCard1, actualCard1);
        assertEquals(expectedCard2, actualCard2);
//        transferPage.failedTransfer(money, card2);
    }
}

