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

    @Test(dataProvider = "hdp", dataProviderClass = HackathonDataProvider.class)
    public void verifyIfCoursesAreDisplayed(String arr[]){
        homePage = new HomePage(driver);
        homePage.giveSearchKeyword(arr[0]);
        homePage.clickSearchButton();
    }

    @Test(dataProvider = "hdp", dataProviderClass = HackathonDataProvider.class)
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
    }

    @Test(dataProvider = "hdp", dataProviderClass = HackathonDataProvider.class)
    public void verifyIfCourseCardsArePresent(String arr[]){
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

        for (int i=1;i<=courseCardsCount;i++){
            Set<String> allwin = driver.getWindowHandles();
            List<String> all = new ArrayList<>(allwin);

            driver.switchTo().window(all.get(i));
            CommonUtils.sureWait(3);
            WebElement course = driver.findElement(By.xpath("//h1[@class='cds-119 css-1rmg2ag cds-121']"));
            String name = course.getText();
            JavaScriptUtil.JScrollByPixelValue(driver,250);
            WebElement time = driver.findElement(By.xpath("(//div[@class=\"css-6mrk5o\"])[4]")); // (//div[@class="css-fw9ih3"])[7]/div[1]
            String tim = time.getText();
            System.out.println("Learning Hours 0f Course "+i+" is "+ tim);
            driver.switchTo().window(all.get(0));
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

        for (int i=1;i<=courseCardsCount;i++){
            Set<String> allwin = driver.getWindowHandles();
            List<String> all = new ArrayList<>(allwin);

            driver.switchTo().window(all.get(i));
            CommonUtils.sureWait(3);
            WebElement course = driver.findElement(By.xpath("//h1[@class='cds-119 css-1rmg2ag cds-121']"));
            String name = course.getText();
            JavaScriptUtil.JScrollByPixelValue(driver,250);
            WebElement rate = driver.findElement(By.xpath("(//div[@class=\"cds-119 cds-Typography-base css-h1jogs cds-121\"])[2]"));
            String rating = rate.getText();
            System.out.println("Ratings of course "+ i+ ": "+ rating);
            driver.switchTo().window(all.get(0));
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
