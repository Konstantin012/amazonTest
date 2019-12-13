package com.academy.amazon.page;

import com.academy.framework.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;

public class MainHomePage extends BasePage {

    //LOCATORS
    @FindBy(css = "a[data-nav-role=\"signin\"]")
    private WebElement signInButton;
    @FindBy(css = "i[class=\"hm-icon nav-sprite\"]")
    private WebElement menuButton;
    @FindBy(css = "i[class=\"hm-icon nav-sprite\"]")
    private WebElement shopByCategoryMenu;
    @FindBy(id = "hmenu-canvas")
    private WebElement hmenu;
    @FindBy(css = "ul [data-menu-id]")
    private WebElement allCategoriesInMenu;
    @FindBy(xpath = "//div[contains(text(),\"Full Store Directory\")]")
    private WebElement fullStoreDirectory;
    @FindBy(xpath = "//a[text()=\"Conditions of Use\"]")
    private WebElement conditionsOfUse;
    @FindBy(xpath = "//div[text()=\"HELP & SETTINGS\"]")
    private WebElement helpAndSettings;
    @FindBy(xpath = "//div[text()=\"SHOP BY CATEGORY\"]")
    private WebElement shopByCategory;
    @FindBy(id = "nav-cart-count")
    private WebElement buyBusket;




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

    public BasketPPage goToBuyBusket(){
        buyBusket.click();
        return new BasketPPage(driver);
    }

    //GET INFORMATION
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

    public void checkNumberOfBuyBusket(String expected){
        String actual = buyBusket.getText();
        Assert.assertEquals(actual,expected);
    }

    public void checkBottomHeaders() {
        shopByCategoryMenu.click();

        //рабочий метод https://www.360logica.com/blog/multiple-ways-to-scroll-a-page-using-selenium-webdriver/
        ((JavascriptExecutor)driver).executeScript(
                "document.querySelector('#hmenu-content > ul.hmenu.hmenu-visible').scrollTo(0,document.body.scrollHeight)");

        EventFiringWebDriver event = new EventFiringWebDriver(driver);
        //рабочий метод
//        event.executeScript(
//                "document.querySelector('#hmenu-content > ul.hmenu.hmenu-visible').scrollTop=500");
        //рабочий метод
        event.executeScript(
                "document.querySelector('#hmenu-content > ul.hmenu.hmenu-visible').scrollTo(0,document.body.scrollHeight)");


        helpAndSettings.click();

    }

}
