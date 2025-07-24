package com.cts.hackathonproject.testrunners;

import com.cts.hackathonproject.pageobjects.*;
import com.cts.hackathonproject.utils.browserutils.BrowserFactory;
import com.cts.hackathonproject.utils.fwutils.HackathonDataProvider;
import com.cts.hackathonproject.utils.fwutils.PropertiesFileReader;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestScenario2 {
    private WebDriver driver;
    HomePage homePage;
    CoursesPage coursesPageObj;
    IndividualCoursePage eachPageoObj;
    ExplorePage explorePageObj;
    LanguagesPage langPageObj;
    ExplorePage2 expObj2;
    LanguagePage2 langObj2;



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

    @Test(dataProvider = "hdp", dataProviderClass = HackathonDataProvider.class)
    public void verifyIfExploreCategoriesPageIsPresent(String arr[]){
        homePage = new HomePage(driver);
        homePage.clickExploreButton();
        homePage.selectExploreOption(arr[0]);
        String actual = driver.getTitle();
        if(actual.equalsIgnoreCase(arr[1]) || actual.equalsIgnoreCase(arr[2])){
            Assert.assertTrue(true,"Opened");
        }
        else{
            Assert.fail("Explore Page is not Opened");
        }
    }

    @Test(dataProvider = "hdp", dataProviderClass = HackathonDataProvider.class)
    public void verifyIfAllOptionsAreDisplayed(String arr[]){
        homePage = new HomePage(driver);
        homePage.clickExploreButton();
        homePage.selectExploreOption(arr[0]);
        int expected = homePage.getCategoriesCount();
        int actual = Integer.parseInt(arr[1]);
        Assert.assertEquals(actual, expected, "Count of categories did not match");
    }

    @Test(dataProvider = "hdp", dataProviderClass = HackathonDataProvider.class)
    public void clickOnRequiredCategory(String arr[]){
        homePage = new HomePage(driver);
        homePage.clickExploreButton();
        homePage.selectExploreOption(arr[0]);
        explorePageObj = new ExplorePage(driver);
        explorePageObj.selectCategoryOption(arr[1]);
        String expected = "Language Learning Online Courses | Coursera";
        String actual = driver.getTitle();
        Assert.assertEquals(expected,actual,"The page did not open");
    }


    @Test(dataProvider = "hdp", dataProviderClass = HackathonDataProvider.class)
    public void extractListOfLanguagesOffered(String arr[]){
        homePage = new HomePage(driver);
        homePage.clickExploreButton();

        explorePageObj = new ExplorePage(driver);
        expObj2 = new ExplorePage2(driver);

        langPageObj = new LanguagesPage(driver);
        langObj2 = new LanguagePage2(driver);

        homePage.selectExploreOption(arr[0]);

        if(explorePageObj.isElementPresent()){
            explorePageObj.selectCategoryOption(arr[1]);
            langPageObj.clickOnShowMore(arr[2]);
            langPageObj.getCount(arr[2]);
            langPageObj.getOptions(arr[2]);
        }
        else{
            expObj2.clickOnCategory(arr[1]);
            langObj2.getList();
            langObj2.getLanguagesCount();
        }
    }


    @Test(dataProvider = "hdp", dataProviderClass =  HackathonDataProvider.class)
    public void extractLevelsPerLanguage(String arr[]){
        homePage = new HomePage(driver);
        homePage.clickExploreButton();

        explorePageObj = new ExplorePage(driver);
        expObj2 = new ExplorePage2(driver);

        langPageObj = new LanguagesPage(driver);
        langObj2 = new LanguagePage2(driver);

        homePage.selectExploreOption(arr[0]);

        if(explorePageObj.isElementPresent()){
            explorePageObj.selectCategoryOption(arr[1]);
            langPageObj.clickOnShowMore(arr[2]);
            langPageObj.getOptions(arr[2]);
            int actual = langPageObj.getCount(arr[2]);
            int expected = Integer.parseInt(arr[3]);
            Assert.assertEquals(actual, expected, "Levels extracted are not what to be expected");
        }
        else{
            expObj2.clickOnCategory(arr[1]);
            langObj2.getLevels();
            Assert.assertTrue(true);
        }
    }


    @AfterMethod
    public void closeBrowser(){
        driver.close();
    }

}
