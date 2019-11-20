package com.academy.amazon.page;

import com.academy.framework.page.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainHomePage extends BasePage {

    //LOCATORS
    @FindBy(css = "a[data-nav-role=\"signin\"]")
    private WebElement signInButton;




    //ACTIONS
    public MainHomePage(WebDriver driver) {
        super(driver);
    }
    public static MainHomePage startFromHome(WebDriver driver, String baseUrl) {
        driver.get(baseUrl);
        driver.get(baseUrl);
        return new MainHomePage(driver);
    }

    public LogInPage clickSingIn() {
        signInButton.click();
        return new LogInPage(driver);
    }

    //VERIFICATIONS
}
