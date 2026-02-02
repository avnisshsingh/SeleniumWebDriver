package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

public class AddCartPage {
	@SuppressWarnings("unused")
	private WebDriver driver;
	@SuppressWarnings("unused")
	private ElementUtil eleUtil;

	@SuppressWarnings("unused")
	private By logoutLink = By.xpath("//a[@class='list-group-item' and text()='Logout']");
	@SuppressWarnings("unused")
	private By search = By.name("search");
	@SuppressWarnings("unused")
	private By searchIcon = By.cssSelector("div#search button");
	@SuppressWarnings("unused")
	private By accSecHeaders = By.cssSelector("div#content h2");

	public AddCartPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
}
