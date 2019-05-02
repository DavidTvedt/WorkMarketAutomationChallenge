package com.workmarketseleniumchallenge.pageObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HomePage {

	private WebDriver driver;
	
	@FindBy(xpath = "//*[@id=\"wm-main-nav\"]/div/div[2]/div[2]/div/div/div[4]/button")
	private WebElement pofileBtn;
		
	@FindBy(xpath = "//a[contains(@role,'menuitem') and contains(.//div, 'Sign Out')]")
	private WebElement signOutBtn;
	
	@FindBy(id = "home__Find Talent-button")
	private WebElement findTalentBtn;
	
	@FindBy(xpath = "//div[@role='presentation']")
	private WebElement profileDropdown;
	
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public LoginPage logOut() {
		pofileBtn.click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(profileDropdown));
		signOutBtn.click();
		
		return new LoginPage(driver);

	}
	
	public FindTalentPage openFindTalent() {
		findTalentBtn.click();
		
		return new FindTalentPage(driver);
	}
}
