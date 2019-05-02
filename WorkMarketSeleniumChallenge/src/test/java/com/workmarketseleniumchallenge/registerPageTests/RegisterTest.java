package com.workmarketseleniumchallenge.registerPageTests;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.workmarketseleniumchallenge.pageObjectRepository.RegisterPage;

public class RegisterTest {

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
	public void registerAsIndividualWithInvalidEmail() {
		//Arrange
		RegisterPage registerPage = new RegisterPage(driver);
		
		//Act
		registerPage.joinAsIndividual();
		registerPage.fillFormAsIndividual(RegisterPage.firstName, RegisterPage.lastName,
				RegisterPage.invalidEmail, RegisterPage.validPassword, true);
		
		//Assert
		assertEquals(registerPage.emailInvalidAlert(), true, "Invalid password alert missing");
		
	}
	
	@Test
	public void registerAsIndividualWithInvalidPassword() {
		//Arrange
		RegisterPage registerPage = new RegisterPage(driver);
		
		//Act
		registerPage.joinAsIndividual();
		registerPage.fillFormAsIndividual(RegisterPage.firstName, RegisterPage.lastName,
				getRandomString()+"@gmail.com", RegisterPage.invalidPassword, true);
		
		registerPage.register();
		//Assert
		assertEquals(registerPage.passwordInvalidAlert(), true, "Invalid password alert missing");
	}
	
	@Test
	public void registerAsIndividualWithoutAgreeingToLicense() {
		//Arrange
		RegisterPage registerPage = new RegisterPage(driver);
		
		//Act
		registerPage.joinAsIndividual();
		registerPage.fillFormAsIndividual(RegisterPage.firstName, RegisterPage.lastName,
				getRandomString()+"@gmail.com", RegisterPage.validPassword, false);
		
		//Assert
		assertEquals(registerPage.registerBtnIsEnabled(), true, "Agreement not checked, register should be disabled");
			
	}
	
	@Test
	public void registerAsIndividualWithMissingFields() {
		//Arrange
		RegisterPage registerPage = new RegisterPage(driver);
		
		//Act
		registerPage.joinAsIndividual();
		registerPage.fillFormAsIndividual(RegisterPage.firstName, "",
				getRandomString()+"@gmail.com", RegisterPage.validPassword, false);
		
		
		//Assert
		assertEquals(registerPage.registerBtnIsEnabled(), true, "Fields missing, register should be disabled");
	}
	
	@Test
	public void registerAsIndividualWithUsedEmail() {
		//Arrange
		RegisterPage registerPage = new RegisterPage(driver);
		String email = getRandomString()+"@gmail.com";
		
		//Act
		registerPage.joinAsIndividual();
		registerPage.fillFormAsIndividual(RegisterPage.firstName, RegisterPage.lastName,
				email, RegisterPage.validPassword, true);
		
		registerPage.register();
		
		registerPage = new RegisterPage(driver);
		
		registerPage.joinAsIndividual();
		registerPage.fillFormAsIndividual(RegisterPage.firstName, RegisterPage.lastName,
				email, RegisterPage.validPassword2, true);
		
		registerPage.register();
		//Assert
		assertEquals(registerPage.emailAlreadyUsed(), true, "email already used alert missing");
	}
	
	@Test
	public void registerAsIndividualSuccessfully() {
		//Arrange
		RegisterPage registerPage = new RegisterPage(driver);
		
		//Act
		registerPage.joinAsIndividual();
		registerPage.fillFormAsIndividual(RegisterPage.firstName, RegisterPage.lastName,
				getRandomString()+"@gmail.com", RegisterPage.validPassword, true);
		
		registerPage.register();
		//Assert
		assertEquals(registerPage.registeredSuccessfully(), true, "cannot register with correct fields entered");
	}
	

	@AfterTest
	public void tearDown() {
		driver.close();
	}
	
	
	protected String getRandomString() {
        String allchar = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder ranChar = new StringBuilder();
        Random rnd = new Random();
        while (ranChar.length() < 18) {
            int index = (int) (rnd.nextFloat() * allchar.length());
            ranChar.append(allchar.charAt(index));
        }
        String ranStr = ranChar.toString();
        return ranStr;

    }

}
