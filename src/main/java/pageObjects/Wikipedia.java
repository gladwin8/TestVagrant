package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Wikipedia {

	public WebDriver driver;
	
	By Wiki_Searchbox=By.xpath("//input[@class='vector-search-box-input']");
	//need to update
	By Wiki_ReleaseDetails=By.xpath("//div[text()='Release date']/parent::th/following-sibling::td");
	By Wiki_Origin=By.xpath("//th[text()='Country']/following-sibling::td");
	//table[@class='infobox vevent']/tbody/tr/th/div[text()='Release date']
	//div[text()='Release date']/parent::th/following-sibling::td
	By Wiki_label=By.id("p-logo");
	By SearchButton=By.id("searchButton");
	
	
	public Wikipedia(WebDriver driver) {
		this.driver=driver;
	}
	
	public WebElement WikiLabel()
	{
		return driver.findElement(Wiki_label);	
	}
	
	public WebElement Searchbox()
	{
		return driver.findElement(Wiki_Searchbox);
	}
	
	public WebElement Searchbutton()
	{
		return driver.findElement(SearchButton);
	}
	
	public void Explicitlywait(int seconds)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.visibilityOfElementLocated(Wiki_Searchbox));
	}
	
	public WebElement ReleaseDetails()
	{
		return driver.findElement(Wiki_ReleaseDetails);
	}
	
	public WebElement Origin()
	{
		return driver.findElement(Wiki_Origin);
	}
}