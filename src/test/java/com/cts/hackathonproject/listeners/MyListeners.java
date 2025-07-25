package com.cts.hackathonproject.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.cts.hackathonproject.utils.seleniumutils.ScreenShotUtil;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.util.HashMap;
import java.util.Map;

public class MyListeners implements ITestListener {

    private static ExtentReports extent;


    private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();


    private static final Map<String, WebDriver> driverMap = new HashMap<>();

    static {


        String reportPath = "C:/CTS_Training/Practicals/HackathonProject/Hackathon/test-output/ExtentReport.html";


        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);


        spark.config().setDocumentTitle("Automation Report");


        spark.config().setReportName("Coursera Reports Suite");


        spark.config().setTheme(Theme.DARK);

        extent = new ExtentReports();


        extent.attachReporter(spark);


        extent.setSystemInfo("Tester", "Varsha");


        extent.setSystemInfo("Environment", "QA");


        extent.setSystemInfo("OS", System.getProperty("os.name"));


        extent.setSystemInfo("Browser", "Chrome");


    }

    @Override


    public void onTestStart(ITestResult result) {


        ExtentTest test = extent.createTest(


                result.getTestClass().getRealClass().getSimpleName() + " :: " + result.getMethod().getMethodName());


        test.log(Status.INFO, "Test Started: " + result.getMethod().getMethodName());


        testThread.set(test);


    }

    @Override


    public void onTestSuccess(ITestResult result) {


        testThread.get().log(Status.PASS, "Test Passed");


    }

    @Override


    public void onTestFailure(ITestResult result) {


        Object testClassInstance = result.getInstance();


        WebDriver driver = getDriverFromTestClass(testClassInstance);


        String screenshotPath = ScreenShotUtil.takeScreenShot(driver, result.getName());


        testThread.get().log(Status.FAIL, "Test Failed: " + result.getThrowable());


        testThread.get().addScreenCaptureFromPath(screenshotPath);


        System.out.println("Screenshot saved at: " + screenshotPath);


    }

    @Override


    public void onTestSkipped(ITestResult result) {


        testThread.get().log(Status.SKIP, "Test Skipped");


    }

    @Override


    public void onFinish(ITestContext context) {


        extent.flush();


    }

    // Utility method to extract driver using reflection


    private WebDriver getDriverFromTestClass(Object testInstance) {


        try {


            return (WebDriver) testInstance.getClass().getField("driver").get(testInstance);


        } catch (Exception e) {


            e.printStackTrace();


            return null;


        }


    }


}

