package com.cts.hackathonproject.utils.seleniumutils;

import com.cts.hackathonproject.utils.fwutils.CommonUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;

public class ScreenShotUtil {
    public static void takesScreenShots(WebElement element) throws IOException {
        TakesScreenshot tss = (TakesScreenshot) element;
        File src = tss.getScreenshotAs(OutputType.FILE);
        File dest = new File("Screenshots/" + CommonUtils.getCurrentDate() + ".png");
        FileUtils.copyFile(src, dest);

    }

    public static void takesScreenShots(WebDriver driver) throws IOException {
        TakesScreenshot tss = (TakesScreenshot) driver;
        File src = tss.getScreenshotAs(OutputType.FILE);
        File dest = new File("Screenshots/" + CommonUtils.getCurrentDate() + ".png");
        FileUtils.copyFile(src, dest);
    }

    public static void takesScreenShots(SearchContext context) throws IOException {
        TakesScreenshot tss = (TakesScreenshot) context;
        File src = tss.getScreenshotAs(OutputType.FILE);
        File dest = new File("Screenshots/" + CommonUtils.getCurrentDate() + ".png");
        FileUtils.copyFile(src, dest);
    }
}
