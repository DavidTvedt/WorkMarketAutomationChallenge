package com.workmerketseleniumchallenge.findTalentPageTests;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.workmarketseleniumchallenge.pageObjectRepository.FindTalentPage;
import com.workmarketseleniumchallenge.pageObjectRepository.HomePage;
import com.workmarketseleniumchallenge.pageObjectRepository.LoginPage;

public class SearchTest {

	WebDriver driver;
	

	@BeforeTest
	public void setup() {
		File chromeDriver = new File("Driver" + File.separator + "chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", chromeDriver.getAbsolutePath());
        driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}


	@Test
	public void returnedSearchResultsContainsSearchKeyword() {
		
		//Arrange
		LoginPage loginPage = new LoginPage(driver);
		HomePage homePage = loginPage.login(LoginPage.username, LoginPage.password);
		FindTalentPage findTalentPage = homePage.openFindTalent();
		
		//Act
		findTalentPage.keywordSearch("test");
		boolean keywordFoundInAll = findTalentPage.keywordFoundInAllResults("test");
		
		//Assert
		assertEquals(keywordFoundInAll, true, "Search result did not return with match");
		
		//Cleanup
		homePage.logOut();
	}
	
	@AfterTest
	public void tearDown() {
		driver.close();
	}

}
