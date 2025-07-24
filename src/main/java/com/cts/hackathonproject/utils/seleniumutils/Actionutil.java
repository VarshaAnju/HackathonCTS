package com.cts.hackathonproject.utils.seleniumutils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
public class Actionutil{
    public static void clickAction(WebDriver driver) {
        Actions act = new Actions(driver);
        act.click().perform();
    }
    public static void sendKeysAction(WebDriver driver, String str) {
        Actions act = new Actions(driver);
        act.sendKeys(str).perform();
    }

    public static void dragAndDropAction(WebDriver driver, WebElement dragSrc, WebElement dragDest) {
        Actions act = new Actions(driver);
        act.dragAndDrop(dragSrc, dragDest).perform();
    }

    public static void rightClickAction(WebDriver driver, WebElement ele) {
        Actions act = new Actions(driver);
        act.contextClick(ele).perform();
    }

    public static void moveToElementAction(WebDriver driver, WebElement ele) {
        Actions act = new Actions(driver);
        act.moveToElement(ele).perform();
    }

    public  static void scrollToElementAction(WebDriver driver, WebElement ele){
        Actions act = new Actions(driver);
        act.scrollToElement(ele).perform();
    }

    public static void clickEnter(WebDriver driver){
        Actions a = new Actions(driver);
        a.sendKeys(Keys.ENTER).perform();
    }
    public static void clickOnElement(WebDriver driver, WebElement element){
        Actions a = new Actions(driver);
        a.scrollToElement(element).perform();
        a.click(element).perform();
    }

}
