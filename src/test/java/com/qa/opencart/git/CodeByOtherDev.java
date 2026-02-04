package com.qa.opencart.git;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CodeByOtherDev {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://www.tutorialspoint.com/selenium/practice/broken-links.php");
		String url = driver.findElement(By.xpath("//a[text()='Click Here for Broken Link']")).getAttribute("href");
		System.err.println(url);
		Thread.sleep(5000);
		driver.quit();

	}

}
