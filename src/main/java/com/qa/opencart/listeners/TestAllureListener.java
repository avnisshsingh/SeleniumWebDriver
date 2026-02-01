package com.qa.opencart.listeners;

import io.qameta.allure.Attachment;
import io.qameta.allure.testng.AllureTestNg;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.qa.opencart.factory.DriverFactory;

public class TestAllureListener extends AllureTestNg implements ITestListener {

    private static String getTestMethodName(ITestResult result) {
        return result.getMethod().getConstructorOrMethod().getName();
    }

    // ========== ATTACHMENTS ==========

    @Attachment(value = "Page Screenshot", type = "image/png")
    public byte[] saveScreenshotPNG(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    @Attachment(value = "{0}", type = "text/html")
    public static String attachHtml(String html) {
        return html;
    }

    // ========== TESTNG LIFECYCLE ==========

    @Override
    public void onStart(ITestContext context) {
        super.onStart(context); // 🔥 REQUIRED FOR ALLURE
        System.out.println("Allure Listener - onStart : " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        super.onFinish(context); // 🔥 REQUIRED FOR ALLURE
        System.out.println("Allure Listener - onFinish : " + context.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        super.onTestStart(result); // 🔥 REQUIRED FOR ALLURE
        System.out.println("Test Started : " + getTestMethodName(result));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        super.onTestSuccess(result); // 🔥 REQUIRED FOR ALLURE
        System.out.println("Test Passed : " + getTestMethodName(result));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        super.onTestFailure(result); // 🔥 REQUIRED FOR ALLURE
        System.out.println("Test Failed : " + getTestMethodName(result));

        if (DriverFactory.getDriver() instanceof WebDriver) {
            saveScreenshotPNG(DriverFactory.getDriver());
        }

        saveTextLog(getTestMethodName(result) + " failed and screenshot captured!");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        super.onTestSkipped(result); // 🔥 REQUIRED FOR ALLURE
        System.out.println("Test Skipped : " + getTestMethodName(result));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        super.onTestFailedButWithinSuccessPercentage(result); // 🔥 REQUIRED FOR ALLURE
        System.out.println("Test failed within success percentage : " + getTestMethodName(result));
    }
}