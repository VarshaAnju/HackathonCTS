/*
package com.cts.hackathonproject.utils.browserutils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.time.Duration;

public class BrowserFactory {
    private static WebDriver driver;

    public WebDriver getBrowser(String bname, String url){
        switch(bname.intern().toLowerCase()){
            case "chrome":
                driver = new ChromeDriver(); break;
            case "edge":
                driver = new EdgeDriver(); break;
            default:
                ChromeOptions co = new ChromeOptions();
                co.addArguments("--headless");
                driver = new ChromeDriver(co);
                break;
        }
        driver.manage().window().maximize();
        driver.get(url);
        return driver;
    }

    public  WebDriver getBrowser(String bname) {
        switch(bname.intern().toLowerCase()){
            case "chrome":
                driver = new ChromeDriver(); break;
            case "edge":
                driver = new EdgeDriver(); break;
            default:
                ChromeOptions co = new ChromeOptions();
                co.addArguments("--headless");
                driver = new ChromeDriver(co);
                break;
        }
        driver.manage().window().maximize();
        return driver;
    }

    public static WebDriver setUrl(WebDriver driver ,String url){
        driver.get(url);
        return driver;
    }

    public static WebDriver getRemoteWebDriver(String browsername, String url){

        DesiredCapabilities dc = new DesiredCapabilities();
        try {
            switch (browsername.toLowerCase().intern()) {
                case "chrome":
                    dc.setBrowserName("chrome");
                    driver = new RemoteWebDriver(new URL("http://10.241.160.30:4444/wd/hub"), dc);
                    break;
                case "edge":
                    dc.setBrowserName("chrome");
                    driver = new RemoteWebDriver(new URL("http://10.241.160.30:4444/wd/hub"), dc);
                    break;
            }
        }
        catch(Exception e){

        }
        driver.manage().window().maximize();
        driver.get(url);
        return driver;
    }

    private static WebDriver runLocal(String bname){
        switch (bname.toLowerCase().intern()){
            case "chrome":
                driver = new ChromeDriver();break;
            case "edge":
                driver = new EdgeDriver(); break;
            default: break;
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
        return driver;
    }

    private static WebDriver runRemote(String bname, String ip) throws Exception{
        DesiredCapabilities dc = new DesiredCapabilities();
        try {
            switch (bname.toLowerCase().intern()) {
                case "chrome":
                    dc.setBrowserName("chrome");
                    driver = new RemoteWebDriver(new URL(ip+"/wd/hub"), dc);
                    break;
                case "edge":
                    dc.setBrowserName("edge");
                    driver = new RemoteWebDriver(new URL(ip+"/wd/hub"), dc);
                    break;
            }
        }
        catch(Exception e){

        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
        return driver;
    }

    public static WebDriver getDriver(String bname , String wr, String hubip){
        try {
            if (wr.toLowerCase().intern().equals("cloud")) {
                driver = runRemote(bname, hubip);
            } else {
                driver = runLocal(bname);
            }
        }
        catch(Exception e){

        }
        return driver;
    }

}
*/

package com.cts.hackathonproject.utils.browserutils;



import com.cts.hackathonproject.utils.seleniumutils.Waits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class BrowserFactory {
    private static WebDriver driver;

    public static void OpenUrl(String url) {
        driver.get(url);
    }

    public static WebDriver getBrowser(String bn, String wr, String hubip) throws Exception {
        if (wr.toLowerCase().intern().equals("cloud")) {
            driver = runRemote(bn, hubip);
        } else {
            driver = runLocal(bn);
        }
        return driver;
    }

    private static WebDriver runLocal(String bname) {
        ChromeOptions chromeOptions = new ChromeOptions();
        EdgeOptions edgeOptions=new EdgeOptions();
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
        chromeOptions.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
        edgeOptions.addArguments("--disable-notifications");
        edgeOptions.addArguments("--disable-blink-features=AutomationControlled");
        edgeOptions.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
        switch (bname.intern().toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver(chromeOptions);
                break;
            case "edge":
                driver = new EdgeDriver(edgeOptions);
                break;
            default:
                chromeOptions.addArguments("--headless=new");
                driver = new ChromeDriver(chromeOptions);
                break;
        }
        driver.manage().window().maximize();
        Waits.implicitlyWait(driver,30);
        return driver;
    }

    private static WebDriver runRemote(String bn, String ip) throws Exception {
        ChromeOptions chromeOptions = new ChromeOptions();
        EdgeOptions edgeOptions=new EdgeOptions();
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
        chromeOptions.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
        edgeOptions.addArguments("--disable-notifications");
        edgeOptions.addArguments("--disable-blink-features=AutomationControlled");
        edgeOptions.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
        DesiredCapabilities dc = new DesiredCapabilities();
        switch (bn.intern().toLowerCase()) {
            case "chrome":
                dc.setBrowserName("chrome");
//                ChromeOptions co = new ChromeOptions();
//                co.merge(dc);
                driver = new RemoteWebDriver(new URL(ip + "/wd/hub"), dc);
                break;
            case "edge":
                dc.setBrowserName("edge");
                driver = new RemoteWebDriver(new URL(ip + "/wd/hub"), dc);
                break;
            default:
                chromeOptions.addArguments("--headless=new");
                driver = new ChromeDriver(chromeOptions);
                break;
        }
        driver.manage().window().maximize();
        Waits.implicitlyWait(driver,30);
        return driver;
    }

    public static WebDriver getBrowser(String bname, String url) {
        ChromeOptions chromeOptions = new ChromeOptions();
        EdgeOptions edgeOptions=new EdgeOptions();
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
        chromeOptions.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
        edgeOptions.addArguments("--disable-notifications");
        edgeOptions.addArguments("--disable-blink-features=AutomationControlled");
        edgeOptions.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
        switch (bname.intern().toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver(chromeOptions);
                break;
            case "edge":
                driver = new EdgeDriver(edgeOptions);
                break;
            default:
                chromeOptions.addArguments("--headless=new");
                driver = new ChromeDriver(chromeOptions);
                break;
        }
        driver.manage().window().maximize();
        Waits.implicitlyWait(driver,30);
        driver.get(url);
        return driver;
    }

    public static WebDriver getBrowser(String bname) {
        ChromeOptions chromeOptions = new ChromeOptions();
        EdgeOptions edgeOptions=new EdgeOptions();
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
        chromeOptions.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
        edgeOptions.addArguments("--disable-notifications");
        edgeOptions.addArguments("--disable-blink-features=AutomationControlled");
        edgeOptions.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
        switch (bname.intern().toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver(chromeOptions);
                break;
            case "edge":
                driver = new EdgeDriver(edgeOptions);
                break;
            default:
                chromeOptions.addArguments("--headless=new");
                driver = new ChromeDriver(chromeOptions);
                break;
        }
        driver.manage().window().maximize();
        Waits.implicitlyWait(driver,30);
        return driver;
    }
}

