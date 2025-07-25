package com.cts.hackathonproject.testrunners;

import com.cts.hackathonproject.listeners.MyListeners;
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
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
@Listeners(MyListeners.class)
public class TestScenario1 {

    public WebDriver driver;
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
            homePage = new HomePage(driver);
            coursesPageObj = new CoursesPage(driver);
            eachPageoObj = new IndividualCoursePage(driver);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test(priority = 0, dataProvider = "hdp", dataProviderClass = HackathonDataProvider.class)
    public void verifyIfCoursesAreDisplayed(String arr[]){
        homePage.giveSearchKeyword(arr[0]);
        homePage.clickSearchButton();
        Assert.assertTrue(coursesPageObj.getCountOfCourses()>0,"Course cards are not visible");
    }

    @Test(priority = 1, dataProvider = "hdp", dataProviderClass = HackathonDataProvider.class)
    public void verifyIfFiltersAreAppliedProperly(String arr[]){
        CommonUtils.sureWait(5);
        homePage.giveSearchKeyword(arr[0]);
        homePage.clickSearchButton();
        CommonUtils.sureWait(5);
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
        CommonUtils.sureWait(5);
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

    @Test(dataProvider = "hdp", dataProviderClass = HackathonDataProvider.class)
    public void verifyCourseLinksOpening(String arr[]){
        CommonUtils.sureWait(5);
        homePage.giveSearchKeyword(arr[0]);
        homePage.clickSearchButton();
        CommonUtils.sureWait(4);

        coursesPageObj.clickOnCourseCard(1);

        String actual = homePage.getTitleOfCurrentPage(driver);
        Set<String> allwin = driver.getWindowHandles();
        List<String> all = new ArrayList<>(allwin);

        driver.switchTo().window(all.get(1));
        CommonUtils.sureWait(5);
        String expected = arr[6];
        Assert.assertTrue(!(actual.equalsIgnoreCase(expected)), "Course link not opened correctly");
    }




    @Test(dataProvider = "hdp", dataProviderClass = HackathonDataProvider.class)
    public void checkTotalLearningHours(String arr[]){
        CommonUtils.sureWait(5);
        homePage.giveSearchKeyword(arr[0]);
        homePage.clickSearchButton();
        CommonUtils.sureWait(5);
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
            CommonUtils.sureWait(3);
        }

        eachPageoObj = new IndividualCoursePage(driver);

        try{
            for (int i=1;i<=courseCardsCount;i++){
                Set<String> allwin = driver.getWindowHandles();
                List<String> all = new ArrayList<>(allwin);

                driver.switchTo().window(all.get(i));
                CommonUtils.sureWait(3);
                String name = eachPageoObj.getNameOfCourse();
                System.out.println("Course : "+ name);
                JavaScriptUtil.JScrollByPixelValue(driver,250);
                String tim = eachPageoObj.getDuration();
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
        CommonUtils.sureWait(5);
        homePage.giveSearchKeyword(arr[0]);
        homePage.clickSearchButton();
        CommonUtils.sureWait(5);
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
            CommonUtils.sureWait(3);
        }

        try{
            for (int i=1;i<=courseCardsCount;i++){
                Set<String> allwin = driver.getWindowHandles();
                List<String> all = new ArrayList<>(allwin);

                driver.switchTo().window(all.get(i));
                CommonUtils.sureWait(3);
                String name = eachPageoObj.getNameOfCourse();
                System.out.println("Course : "+ name);
                JavaScriptUtil.JScrollByPixelValue(driver,250);
                String rating = eachPageoObj.getRatings();
                System.out.println("Ratings of course "+ i+ ": "+ rating);
                driver.switchTo().window(all.get(0));
            }
            Assert.assertTrue(true);
        }
        catch(Exception e){
            Assert.fail("It failed");
        }

    }

    @Test(dataProvider = "hdp", dataProviderClass = HackathonDataProvider.class)
    public void verifyIfCourseCardAndLinkAreSame(String arr[]){
        CommonUtils.sureWait(5);
        homePage.giveSearchKeyword(arr[0]);
        homePage.clickSearchButton();
        CommonUtils.sureWait(5);
        int noOfFilters = Integer.parseInt(arr[1]); int k=0;
        for(int i=0;i<noOfFilters;i++){
            coursesPageObj.findFilter(arr[2+k]);
            coursesPageObj.clickOnFilter(arr[2+k], arr[3+k]);
            CommonUtils.sureWait(5);
            k=k+2;
        }
        CommonUtils.sureWait(4);
        String cardName = coursesPageObj.getCourseCardName(0);
        coursesPageObj.clickOnCourseCard(1);

        Set<String> allwin = driver.getWindowHandles();
        List<String> all = new ArrayList<>(allwin);
        driver.switchTo().window(all.get(1));
        CommonUtils.sureWait(3);
        String pageName = eachPageoObj.getNameOfCourse();
        System.out.println(pageName);
        System.out.println(cardName);
        if(pageName.contains(cardName)||cardName.contains(pageName)){
            Assert.assertTrue(true);
        }
        else{
            Assert.fail("Not at all same. Nope. Not a chance");
        }
       // Assert.assertTrue(cardName.equalsIgnoreCase(pageName),"Not same");

    }

    @AfterMethod
    public void postTest() {
        driver.close();
        driver.quit();
    }


}
