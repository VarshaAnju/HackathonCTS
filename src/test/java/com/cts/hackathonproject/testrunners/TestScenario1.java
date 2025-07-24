package com.cts.hackathonproject.testrunners;

import com.cts.hackathonproject.pageobjects.CoursesPage;
import com.cts.hackathonproject.pageobjects.HomePage;
import com.cts.hackathonproject.pageobjects.IndividualCoursePage;
import com.cts.hackathonproject.utils.browserutils.BrowserFactory;
import com.cts.hackathonproject.utils.fwutils.CommonUtils;
import com.cts.hackathonproject.utils.fwutils.HackathonDataProvider;
import com.cts.hackathonproject.utils.fwutils.PropertiesFileReader;
import com.cts.hackathonproject.utils.seleniumutils.JavaScriptUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TestScenario1 {

    private WebDriver driver;
    HomePage homePage;
    CoursesPage coursesPageObj;
    IndividualCoursePage eachPageoObj;


    @BeforeMethod
    public void preSetUp() {
        String bname; String wr;
        String url; String hubip;
        BrowserFactory bobj = new BrowserFactory();
        try {
            bname = PropertiesFileReader.getPropertyValue("config", "browsername");
            url = PropertiesFileReader.getPropertyValue("config", "openurl");
            driver = bobj.getBrowser(bname, url);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test(priority = 0, dataProvider = "hdp", dataProviderClass = HackathonDataProvider.class)
    public void verifyIfCoursesAreDisplayed(String arr[]){
        homePage = new HomePage(driver);
        coursesPageObj = new CoursesPage(driver);
        homePage.giveSearchKeyword(arr[0]);
        homePage.clickSearchButton();
        Assert.assertTrue(coursesPageObj.getCountOfCourses()>0,"Course cards are not visible");
    }

    @Test(priority = 1, dataProvider = "hdp", dataProviderClass = HackathonDataProvider.class)
    public void verifyIfFiltersAreAppliedProperly(String arr[]){
        homePage = new HomePage(driver);
        coursesPageObj = new CoursesPage(driver);
        homePage.giveSearchKeyword(arr[0]);
        homePage.clickSearchButton();
        int noOfFilters = Integer.parseInt(arr[1]); int k=0;
        for(int i=0;i<noOfFilters;i++){
            coursesPageObj.findFilter(arr[2+k]);
            coursesPageObj.clickOnFilter(arr[2+k], arr[3+k]);
            CommonUtils.sureWait(5);
            k=k+2;
        }
        CommonUtils.sureWait(4);
        Assert.assertTrue(coursesPageObj.countOfFiltersApplied()>0,"Filters are not applied");

    }

    @Test(dataProvider = "hdp", dataProviderClass = HackathonDataProvider.class)
    public void verifyIfCourseCardsArePresentAfterApplyingFilters(String arr[]){
        homePage = new HomePage(driver);
        coursesPageObj = new CoursesPage(driver);
        homePage.giveSearchKeyword(arr[0]);
        homePage.clickSearchButton();
        int noOfFilters = Integer.parseInt(arr[1]); int k=0;
        for(int i=0;i<noOfFilters;i++){
            coursesPageObj.findFilter(arr[2+k]);
            coursesPageObj.clickOnFilter(arr[2+k], arr[3+k]);
            CommonUtils.sureWait(5);
            k=k+2;
        }
        CommonUtils.sureWait(4);
        Assert.assertTrue(coursesPageObj.getCountOfCourses()>0,"Course cards are not visible after applying filters");

    }

    @Test
    public void verifyCourseLinksOpening(String arr[]){
        homePage = new HomePage(driver);
        coursesPageObj = new CoursesPage(driver);
        homePage.giveSearchKeyword(arr[0]);
        homePage.clickSearchButton();
        int noOfFilters = Integer.parseInt(arr[1]); int k=0;
        for(int i=0;i<noOfFilters;i++){
            coursesPageObj.findFilter(arr[2+k]);
            coursesPageObj.clickOnFilter(arr[2+k], arr[3+k]);
            CommonUtils.sureWait(5);
            k=k+2;
        }
        CommonUtils.sureWait(4);
        int courseCardsCount = Integer.parseInt(arr[6]);
        for(int i=1;i<=courseCardsCount;i++){
            coursesPageObj.clickOnCourseCard(i);
        }
    }


    @Test(dataProvider = "hdp", dataProviderClass = HackathonDataProvider.class)
    public void checkTotalLearningHours(String arr[]){
        homePage = new HomePage(driver);
        coursesPageObj = new CoursesPage(driver);
        homePage.giveSearchKeyword(arr[0]);
        homePage.clickSearchButton();
        int noOfFilters = Integer.parseInt(arr[1]); int k=0;
        for(int i=0;i<noOfFilters;i++){
            coursesPageObj.findFilter(arr[2+k]);
            coursesPageObj.clickOnFilter(arr[2+k], arr[3+k]);
            CommonUtils.sureWait(5);
            k=k+2;
        }
        CommonUtils.sureWait(4);
        int courseCardsCount = Integer.parseInt(arr[6]);
        for(int i=1;i<=courseCardsCount;i++){
            coursesPageObj.clickOnCourseCard(i);
        }

        try{
            for (int i=1;i<=courseCardsCount;i++){
                Set<String> allwin = driver.getWindowHandles();
                List<String> all = new ArrayList<>(allwin);

                driver.switchTo().window(all.get(i));
                CommonUtils.sureWait(3);
                String name = coursesPageObj.getNameOfCourse();
                System.out.println("Course : "+ name);
                JavaScriptUtil.JScrollByPixelValue(driver,250);
                String tim = coursesPageObj.getDuration();
                System.out.println("Learning Hours 0f Course "+i+" is "+ tim);
                driver.switchTo().window(all.get(0));
                Assert.assertTrue(true);
            }
        }
        catch(Exception e){
            Assert.fail("It failed");
        }
    }

    @Test(dataProvider = "hdp", dataProviderClass = HackathonDataProvider.class)
    public void checkRatings(String arr[]){
        homePage = new HomePage(driver);
        coursesPageObj = new CoursesPage(driver);
        homePage.giveSearchKeyword(arr[0]);
        homePage.clickSearchButton();
        int noOfFilters = Integer.parseInt(arr[1]); int k=0;
        for(int i=0;i<noOfFilters;i++){
            coursesPageObj.findFilter(arr[2+k]);
            coursesPageObj.clickOnFilter(arr[2+k], arr[3+k]);
            CommonUtils.sureWait(5);
            k=k+2;
        }
        CommonUtils.sureWait(4);
        int courseCardsCount = Integer.parseInt(arr[6]);
        for(int i=1;i<=courseCardsCount;i++){
            coursesPageObj.clickOnCourseCard(i);
        }

        try{
            for (int i=1;i<=courseCardsCount;i++){
                Set<String> allwin = driver.getWindowHandles();
                List<String> all = new ArrayList<>(allwin);

                driver.switchTo().window(all.get(i));
                CommonUtils.sureWait(3);
                String name = coursesPageObj.getNameOfCourse();
                System.out.println("Course : "+ name);
                JavaScriptUtil.JScrollByPixelValue(driver,250);
                String rating = coursesPageObj.getRatings();
                System.out.println("Ratings of course "+ i+ ": "+ rating);
                driver.switchTo().window(all.get(0));
            }
            Assert.assertTrue(true);
        }
        catch(Exception e){
            Assert.fail("It failed");
        }

    }

    @Test
    public void TC007(){

    }

    @AfterMethod
    public void postTest() {
        driver.close();
        driver.quit();
    }


}
