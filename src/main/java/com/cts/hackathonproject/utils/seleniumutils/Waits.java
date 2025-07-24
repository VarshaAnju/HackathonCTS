package com.cts.hackathonproject.utils.seleniumutils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waits {
    public static void implicitlyWait(WebDriver driver, int time){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
    }

    public static void waitForElement(By by, WebDriver driver, int time){
        WebDriverWait wdw = new WebDriverWait(driver,Duration.ofSeconds(time));
        wdw.until(ExpectedConditions.presenceOfElementLocated(by));

    }
}
