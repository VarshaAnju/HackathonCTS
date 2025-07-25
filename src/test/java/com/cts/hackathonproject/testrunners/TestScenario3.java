package com.cts.hackathonproject.testrunners;

import com.cts.hackathonproject.listeners.MyListeners;
import com.cts.hackathonproject.pageobjects.FormSubmitPage;
import com.cts.hackathonproject.pageobjects.HomePage;
import com.cts.hackathonproject.utils.browserutils.BrowserFactory;
import com.cts.hackathonproject.utils.fwutils.CommonUtils;
import com.cts.hackathonproject.utils.fwutils.HackathonDataProvider;
import com.cts.hackathonproject.utils.fwutils.PropertiesFileReader;
import com.cts.hackathonproject.utils.seleniumutils.JavaScriptUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners(MyListeners.class)
public class TestScenario3 {

    HomePage homePageObj;
    FormSubmitPage formObj;
    private WebDriver driver;

    @BeforeMethod
    public void preSetUp() {
        String bname; String wr;
        String url; String hubip;
        try {
            bname = PropertiesFileReader.getPropertyValue("config", "browsername");
            url = PropertiesFileReader.getPropertyValue("config", "openurl");
            driver = BrowserFactory.getBrowser(bname, url);
            driver.manage().deleteAllCookies();
            driver.navigate().refresh();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test(priority = 0, dataProvider = "hdp", dataProviderClass = HackathonDataProvider.class)
    public void checkIfEnterpriseIsPresent(String arr[]){
        homePageObj = new HomePage(driver);
        boolean present = homePageObj.isCatalogElementPresent(arr[0]);
        Assert.assertTrue(present);
    }

    @Test(priority = 1, dataProvider = "hdp", dataProviderClass = HackathonDataProvider.class)
    public void verifyClickActionOnForEnterpriseOption(String arr[]){
        homePageObj = new HomePage(driver);
        homePageObj.clickOnDesiredCatalogElement(arr[0]);
        String actual = homePageObj.getURLOfCurrentPage(driver);
        String expected = arr[1];
        Assert.assertEquals(actual, expected, "Desired page is not opened");
    }

    @Test(priority=2, dataProvider = "hdp", dataProviderClass = HackathonDataProvider.class)
    public void clickOnForEnterpriseOption(String arr[]){
        homePageObj = new HomePage(driver);
        homePageObj.clickOnDesiredCatalogElement(arr[0]);
        String actual = homePageObj.getTitleOfCurrentPage(driver);
        String expected = arr[1];
        Assert.assertEquals(actual, expected, "Titles are not matching");
    }


    @Test(priority = 3, dataProviderClass = HackathonDataProvider.class, dataProvider = "hdp")
    public void verifyFormPresenceInEnterprisePage(String arr[]){
        homePageObj = new HomePage(driver);
        homePageObj.clickOnDesiredCatalogElement(arr[0]);
        formObj = new FormSubmitPage(driver);
        String actual = formObj.formProof();
        String expected = arr[1];
        Assert.assertEquals(actual, expected, "Form is not available");
    }

    @Test(priority = 4, dataProvider = "hdp",dataProviderClass = HackathonDataProvider.class)
    public void validateMandatoryFieldErrorOnForm(String arr[]){
        homePageObj = new HomePage(driver);
        homePageObj.clickOnDesiredCatalogElement(arr[0]);
        CommonUtils.sureWait(3);
        formObj = new FormSubmitPage(driver);
        formObj.setFirstElement(arr[1]);
        formObj.setLastName(arr[2]);
        formObj.setEmailElement(arr[3]);
        formObj.clickSubmitButton();
        String expected = arr[4];
        String actual = formObj.getErrorMessage(driver);
        Assert.assertEquals(expected, actual, "Error is not generated correctly");

    }

    @Test(priority = 5, dataProvider = "hdp", dataProviderClass = HackathonDataProvider.class)
    public void validateMandatoryFieldErrorOnFormBasedOnEmail(String arr[]){
        homePageObj = new HomePage(driver);
        homePageObj.clickOnDesiredCatalogElement(arr[0]);
        CommonUtils.sureWait(3);
        formObj = new FormSubmitPage(driver);
        formObj.setFirstElement(arr[1]);
        formObj.setLastName(arr[2]);
        formObj.setEmailElement(arr[3]);
        formObj.clickSubmitButton();
        formObj.getErrorMessage(driver);
    }


    @Test(priority = 6, dataProvider = "hdp", dataProviderClass = HackathonDataProvider.class)
    public void validateMandatoryFieldErrorOnFormBasedOnPhoneNumber(String arr[]){
        homePageObj = new HomePage(driver);
        homePageObj.clickOnDesiredCatalogElement(arr[0]);
        CommonUtils.sureWait(3);
        formObj = new FormSubmitPage(driver);
        formObj.setFirstElement(arr[1]);
        formObj.setLastName(arr[2]);
        formObj.setEmailElement(arr[3]);
        formObj.setPhoneElement(arr[4]);
        formObj.clickSubmitButton();
        formObj.getErrorMessage(driver);
    }


    @Test
    public void debug(){
        homePageObj = new HomePage(driver);
        homePageObj.clickOnDesiredCatalogElement("For Enterprise");
        CommonUtils.sureWait(3);
        FormSubmitPage obj = new FormSubmitPage(driver);
        obj.setFirstElement("V");
        obj.setLastName("v");
        obj.setEmailElement("VNH@gmail.com");
        obj.setPhoneElement("456");
        obj.clickSubmitButton();
        System.out.println(driver.findElement(By.xpath("//div[@role='alert']")).getText());
    }

    @AfterMethod
    public void closeBrowser(){
        driver.close();
    }
}
