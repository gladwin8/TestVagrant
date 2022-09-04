package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Imdb {

	
	public WebDriver driver;
	
	By imdblabel=By.xpath("//a[@id='home_img_holder']");
	//static String imdbsearch="//div[@class='react-autosuggest__container']";
	By imdbsearch=By.xpath("//input[@id='suggestion-search']");
	
	
	By autosuggestionlist=By.xpath("//div[@class='sc-d2740ffb-1 duOqOv']/div[1]");
	//By ExpectedMovie=By.xpath("//div[text()='"+SearchMovie+"']");
	//String ExpectedMovie="//div[text()='Pushpa: The Rise - Part 1']";
	By Imdb_ReleaseDetails=By.xpath("//li[@data-testid='title-details-releasedate']/div");
	By Imdb_Origin=By.xpath("//li[@data-testid='title-details-origin']/div");
	
	public Imdb(WebDriver driver) {
		this.driver=driver;
	}
	
	public WebElement imdblabel()
	{
		return driver.findElement(imdblabel);
	}
	
	public WebElement imdbsearch()
	{
		return driver.findElement(imdbsearch);
	}
	
	public List<WebElement> Autosuggestions()
	{
		return driver.findElements(autosuggestionlist);
	}

	public WebElement Moviename_suggestions(String moviename) 
	{
		return driver.findElement(By.xpath("//div[text()='"+moviename+"']"));
	}
	
	public void ExplicitWait(int Seconds)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Seconds));
		wait.until(ExpectedConditions.visibilityOfElementLocated(Imdb_ReleaseDetails));
	}
	
	public WebElement Imdb_ReleaseDetails()
	{
		return driver.findElement(Imdb_ReleaseDetails);
	}
	
	public WebElement Imdb_Origin()
	{
		return driver.findElement(Imdb_Origin);
	}
}
