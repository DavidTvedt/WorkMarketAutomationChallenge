package com.workmarketseleniumchallenge.pageObjectRepository;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	private String loginUrl = "https://dev.workmarket.com/register/campaign/10081C503B209A0C8E7F05FDCC1AA98D4C904DEEF5F73265CAE38C744E7EAD3E";
	private WebDriver driver;
	
	public static String firstName = "David";
	public static String lastName = "Tvedt";
	public static String validEmail = "qa+candidatetest@workmarket.com";
	public static String validPassword = "candidate123";
	public static String validPassword2 = "candidate123asd";
	public static String invalidEmail = "qa+candidatetest@workmarket";
	public static String invalidPassword = "cande";
	
	
	@FindBy(xpath = "//span[text()='Join as an individual']/ancestor::button")
	private WebElement joinAsIndividualBtn;
	
	@FindBy(linkText = "Sign Up for Work Market")
	private WebElement signUpHeader;
	
	@FindBy(id = "firstname")
	private WebElement firstNameTxtField;

	@FindBy(id = "lastname")
	private WebElement lastNameTxtField;
	
	@FindBy(id = "email")
	private WebElement emailTxtField;
	
	@FindBy(xpath = "//div[contains(text(),'Please enter a valid  email')]")
	private WebElement invalidEmailAlert;

	@FindBy(id = "password")
	private WebElement passwordTxtField;
	
	@FindBy(xpath = "//div[contains(text(),'Please enter a valid password')]")
	private WebElement invalidPasswordAlert;

	@FindBy(xpath = "//p[contains(text(),'Your password entered is not allowed because it is too simple')]")
	private WebElement invalidPasswordAlertBanner;
	
	
	@FindBy(xpath = "//input[@type='checkbox']")
	private WebElement agreementChkBox;
	
	@FindBy(xpath = "//span[text()='Register']/ancestor::button")
	private WebElement registerBtn;
	
	@FindBy(xpath = "//p[contains(text(),'is already being used')]")
	private WebElement alreadyUsedEmailBanner;
	
	@FindBy(id = "campaign_landing")
	private WebElement campaignLandingPage;
	
	
	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
		driver.get(loginUrl);
	}
	
	public void joinAsIndividual() {
		joinAsIndividualBtn.click();
	}
	
	public void fillFormAsIndividual(String firstName, String lastName, 
			String email, String password, boolean agree) {
		
		firstNameTxtField.click();
		firstNameTxtField.clear();
		firstNameTxtField.sendKeys(firstName);
		
		lastNameTxtField.click();
		lastNameTxtField.clear();
		lastNameTxtField.sendKeys(lastName);
		
		emailTxtField.click();
		emailTxtField.clear();
		emailTxtField.sendKeys(email);
		
		passwordTxtField.click();
		passwordTxtField.clear();
		passwordTxtField.sendKeys(password);
		
		if(agree) agreementChkBox.click();
	}
	
	public void register() {
		
		registerBtn.click();
	}
	
	public boolean registerBtnIsEnabled() {
		if(!registerBtn.getText().contains("disabled")) return true;
		else return false;
	}
	
	public boolean emailInvalidAlert() {
		try {
			if(invalidEmailAlert.isDisplayed()) return true;
		}
		catch (NoSuchElementException e) {
			return false;
		}
		return false;
	}
	
	public boolean passwordInvalidAlert() {
		
		try {
			if(invalidPasswordAlert.isDisplayed()) return true;
		}
		catch (NoSuchElementException e) {
			if(invalidPasswordAlertBanner.isDisplayed()) return true;
		}
		
		return false;
		
	}
	
	public boolean agreementChecked() {
		return agreementChkBox.getAttribute("value").contains("on");
	}
	
	public boolean emailAlreadyUsed() {
		return alreadyUsedEmailBanner.isDisplayed();
	}
	
	public boolean registeredSuccessfully() {
		if(campaignLandingPage.isDisplayed() && driver.getCurrentUrl().contains("thankyou")) return true;
		else return false;
	}
	
	
	
}
