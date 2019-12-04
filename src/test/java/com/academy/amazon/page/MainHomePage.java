package com.academy.amazon.page;

import com.academy.framework.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainHomePage extends BasePage {

    //LOCATORS
    @FindBy(css = "a[data-nav-role=\"signin\"]")
    private WebElement signInButton;
    @FindBy(css = "i[class=\"hm-icon nav-sprite\"]")
    private WebElement menuButton;
    @FindBy(css = "i[class=\"hm-icon nav-sprite\"]")
    private WebElement shopByCategoryMenu;
    @FindBy(css = "ul [data-menu-id]")
    private WebElement allCategoriesInMenu;
    @FindBy(xpath = "//div[contains(text(),\"Full Store Directory\")]")
    private WebElement fullStoreDirectory;



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

    public List<String> getChildrenFromMainMenu(){
        shopByCategoryMenu.click();
        waitingExpectedElement(fullStoreDirectory,5);
//        writeToTxt(getWebElements("ul [data-menu-id]"),"data-menu-id",path);
        List<WebElement> elem = getWebElements("ul [data-menu-id]");
        return elem.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    //VERIFICATIONS
    public void checkMainMenu(List<String> actual, String path){
        List<String> expected = readFromTxt(path);
        checkTwoLists(actual,expected);
    }
}
