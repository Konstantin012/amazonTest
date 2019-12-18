package com.academy.framework.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.*;
import java.time.Duration;
import java.util.*;

public class BasePage {
    protected WebDriver driver;


    protected BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected void inputTextField(WebElement textField, String value) {
        textField.click();
        textField.clear();
        textField.sendKeys(value);
    }

    public String getTitle() {
        return driver.getTitle();
    }

    //WAITING
    protected void waitingExpectedElement(WebElement locator, int time) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(time))
                    .until(ExpectedConditions.visibilityOf(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void waitingExpectedElement(By locator, int time) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(time))
                    .until(ExpectedConditions.visibilityOf(driver.findElement(locator)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<WebElement> getWebElements(String locator) {
        return driver.findElements(By.cssSelector(locator));
    }

    public void writeToTxt(List<WebElement> stringToFile, String attribute, String path) {
        try (FileWriter writer = new FileWriter(path, false)) {
            for (WebElement s : stringToFile) {
                String text = s.getText();
                writer.write(text + "\n");
            }
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<String> readFromTxt(String path) {
        List<String> newList = new ArrayList<String>();
        File file = new File(path);
        Scanner scan = null;
        try{
            scan = new Scanner(file);
            while (scan.hasNextLine()){
                newList.add(scan.nextLine());
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            scan.close();
        }
        return newList;
    }

    public void checkTwoLists(List<String> actual, List<String> expected){
        Collections.sort(actual);
        Collections.sort(expected);
        Assert.assertEquals(actual,expected);
    }

    protected void scrollToElement(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    protected void scroll(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,2000)");
    }
}




