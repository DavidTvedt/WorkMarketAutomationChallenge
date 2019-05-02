package com.workmarketseleniumchallenge.pageObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	private String loginUrl = "https://dev.workmarket.com/login";
	private WebDriver driver;
	
	public static String username = "qa+candidatetest@workmarket.com";
	public static String password = "candidate123";
	
	
	@FindBy(id = "login-email")
	private WebElement emailAddressTxtField;
	
	@FindBy(id = "login-password")
	private WebElement passwordTxtField;

	@FindBy(id = "login_page_button")
	private WebElement loginBtn;
	
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
		driver.get(loginUrl);
	}
	
	public HomePage login(String username, String password) {
		emailAddressTxtField.sendKeys(username);
		passwordTxtField.sendKeys(password);
		loginBtn.click();
		
		return new HomePage(driver);
	}
}
