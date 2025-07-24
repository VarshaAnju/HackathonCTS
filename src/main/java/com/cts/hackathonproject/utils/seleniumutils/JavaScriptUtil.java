package com.cts.hackathonproject.utils.seleniumutils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {
    public static void JSclick(WebElement ele, WebDriver driver) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();", ele);

    }

    public static void JSsendKeys(WebElement ele, WebDriver driver, String str) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].value='" + str + "';", ele);
    }

    public static void JSscrollToElement(WebElement ele, WebDriver driver) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView();", ele);
    }

    public static void JScrollByPixelValue(WebDriver driver, int pixelValue){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,"+pixelValue+")");
    }

}
