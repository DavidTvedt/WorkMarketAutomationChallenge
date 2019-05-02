package com.workmarketseleniumchallenge.pageObjectRepository;

import java.util.List;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FindTalentPage {
	
	private WebDriver driver;
	
	@FindBy(id = "clear_facets")
	private WebElement clearSearchBtn;
	
	@FindBy(id = "input-text")
	private WebElement searchTxtField;

	@FindBy(className = "profile-card")
	private List<WebElement> resultListItem;

	
	
	public FindTalentPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void keywordSearch(String keyword) {
		clearSearchBtn.click();
		searchTxtField.sendKeys(keyword, Keys.ENTER);
	}
	
	
	public boolean keywordFoundInAllResults(String keyword) {
		boolean found = false;
		
		if(resultListItem.size()>0) {
			for(WebElement resultItem: resultListItem) {
				if(!resultItem.getText().contains(keyword)) break;
			}
			found = true;
		}
		return found;
	}
	
}
