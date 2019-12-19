package com.academy.amazon.page;

import com.academy.framework.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StuffesPage extends BasePage {

    //LOCATORS
    @FindBy(css = "")
    private WebElement f;

        public StuffesPage(WebDriver driver) {
            super(driver);
        }

        //ACTIONS
    public OneGoodsPage selectCurrentElementInGrid(int number){
        WebElement element = driver.findElement(By.cssSelector("div[data-index=\"" + number + "\"]"));
        waitingExpectedElement(element,10);
        element.click();
        return new OneGoodsPage(driver);
    }

}


