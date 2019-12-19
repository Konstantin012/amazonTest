package com.academy.amazon.page;

import com.academy.framework.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import javax.swing.text.html.parser.Parser;
import java.util.Arrays;
import java.util.List;

public class BasketPage extends BasePage{

    //LOCATORS
    @FindBy(xpath = "//div[@data-name=\"Active Items\"]")
    private WebElement elementsInBasket;

    @FindBy(id = "nav-cart-count")
    private WebElement topBasketIcon;

        public BasketPage(WebDriver driver) {
            super(driver);

        }

//        VERIFICATION
        public BasketPage checkGoodsInBasket(boolean basketEmpty){
            List<WebElement> elements = driver.findElements(By.xpath("//div[@data-name=\"Active Items\"]"));
            if(basketEmpty==true){
            Assert.assertTrue(elements.size()==0);
            }
            if(basketEmpty==false){
                Assert.assertTrue(elements.size()!=0);
            }
            return new BasketPage(driver);
        }

        public void checkTopBusketIconText(int expectedNumb){
            Assert.assertEquals(Integer.parseInt(topBasketIcon.getText()),expectedNumb);
        }
}
