package com.academy.amazon;

import com.academy.amazon.page.MainHomePage;
import com.academy.framework.test.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.util.*;

import static com.academy.amazon.page.MainHomePage.startFromHome;

public class DemoTests extends BaseTest {
    private static final Logger LOG =  LogManager.getLogger(DemoTests.class);
    protected Properties propAm;
    private static final String DEFAULT_PATH = "src/main/resources/amazon.properties";

    @BeforeTest
    public void setUp() throws Exception {
        String propertyAmazon = System.getProperty("configForAmazon");
        if (propertyAmazon==null)
            propertyAmazon =DEFAULT_PATH;
        propAm = new Properties();
        propAm.load(new FileReader(propertyAmazon));
        baseUrl = propAm.getProperty("base.url");
//        driver.get(baseUrl);
    }

    @Test(dataProvider = "testDataProvider1")
    public void unsuccessfulInitialization(String value,String mes) {
        startFromHome(driver,baseUrl)
                .clickSingIn()
                .enterIncorrectLogin(value)
                .checkErrorMessage(mes);
    }

    @Test(dataProvider = "MenuTestProvider")
    public void mainMenuTest(String pathExpected) {
        new MainHomePage(driver)
                .checkMainMenu(startFromHome(driver, baseUrl)
                        .getChildrenFromMainMenu(),pathExpected);
    }

    @Test(dataProvider = "AddToBusket")
    public void addToBusket(String a) {

    }

    @Test
    public void checkBottomMenu(){
        startFromHome(driver, baseUrl)
                .checkBottomHeaders();


    }




    @DataProvider(name="testDataProvider1")
    public Object[][] testDataProvider1() {
        String login = propAm.getProperty("incorrectLogin");
        String error = propAm.getProperty("errorMes");
        return new Object[][] {
                {login,error}
        };
    }
    @DataProvider(name="MenuTestProvider")
    public Object[][] MenuTestProvider() {
        String pathExpected = propAm.getProperty("pathForMenuTest");
        return new Object[][] {
                {pathExpected}
        };
    }
    @DataProvider(name="AddToBusket")
    public Object[][] AddToBusket() {
        String a = propAm.getProperty("");
        return new Object[][] {
                {a}
        };
    }
}
