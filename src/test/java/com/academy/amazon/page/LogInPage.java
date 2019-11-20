package com.academy.amazon.page;

import com.academy.framework.page.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class LogInPage extends BasePage {
    public LogInPage(WebDriver driver) {
        super(driver);
    }

    //LOCATORS
    @FindBy(id = "ap_email")
    private WebElement signInField;
    @FindBy(id = "continue")
    private WebElement continueButton;
    @FindBy(id = "auth-error-message-box")
    private WebElement errorWindow;


    //ACTIONS

    public LogInPage enterIncorrectLogin(String login) {
        waitingExpectedElement(signInField,10);
        inputTextField(signInField, login);
        continueButton.click();
        return this;
    }



    //VERIFICATIONS
    public LogInPage checkErrorMessage(String expected){
        waitingExpectedElement(errorWindow, 10);
        String text[] = errorWindow.getText().split("\n");
        Assert.assertEquals(text[0],expected);
        return this;
    }
}
