package com.academy.amazon.page;

import com.academy.framework.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OneGoodsPage extends BasePage {

    //LOCATORS
    @FindBy(css = "div[class=\"a-row a-spacing-micro\"]+span")
    private WebElement selectSize;
    @FindBy(id = "add-to-cart-button")
    private WebElement addToCart;
    @FindBy(id = "hlb-view-cart-announce")
    private WebElement cartButton;
    @FindBy(xpath = "//*[contains(text(), \"Shopping Cart\")]")
    private WebElement shoppingCart;

    String currentSize = "native_dropdown_selected_size_name_";

        public OneGoodsPage(WebDriver driver) {
            super(driver);
        }

        public OneGoodsPage selectSize(int curSize){
            String s = getStringLocatorFromWebElement(selectSize);
            if (tryFindElement(By.cssSelector(s))){
                selectSize.click();
                driver.findElement(By.id(currentSize+curSize)).click();
                waiteByTime(1000);
                return new OneGoodsPage(driver);
            }
            else {
                return new OneGoodsPage(driver);
            }
        }

    public BasketPage addToBasketClick(){
        addToCart.click();
        waiteByTime(2000);
        cartButton.click();
        waitingExpectedElement(shoppingCart, 10);
        return new BasketPage(driver);
    }



}


