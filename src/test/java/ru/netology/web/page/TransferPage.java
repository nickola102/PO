package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;


public class TransferPage {
    private SelenideElement action = $("[data-test-id=action-transfer]");
    private SelenideElement amount = $("[data-test-id='amount'] input");
    private SelenideElement from = $("[data-test-id='from'] input");
    private SelenideElement cancelButton = $("[data-test-id=action-cancel]");

    public TransferPage() {
        amount.shouldBe(visible);
        from.shouldBe(visible);
    }

    public DashboardPage transferMoney(String amount, String numberCard) {
        this.amount.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        this.amount.setValue(amount);
        from.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        from.setValue(numberCard);
        action.click();
        return new DashboardPage();
    }

    public  DashboardPage failedTransfer(String amount, String numberCard) {
        this.amount.setValue(amount);
        from.setValue(numberCard);
        action.click();
        $(withText("Сумма перевода превышает баланс карты")).shouldBe(visible);
        return new DashboardPage();
    }
}
