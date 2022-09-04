package resources;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.hc.core5.util.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base1 {

	WebDriver driver;
	public Properties prop;
	static String imdblabel="//a[@id='home_img_holder']";
	//static String imdbsearch="//div[@class='react-autosuggest__container']";
	String imdbsearch="//input[@id='suggestion-search']";
	String SearchMovie="Pushpa: The Rise";
	
	String autosuggestionlist="//div[@class='sc-d2740ffb-1 duOqOv']/div[1]";
	String ExpectedMovie="//div[text()='"+SearchMovie+"']";
	//String ExpectedMovie="//div[text()='Pushpa: The Rise - Part 1']";
	String Imdb_ReleaseDetails="//li[@data-testid='title-details-releasedate']/div";
	String Imdb_Origin="//li[@data-testid='title-details-origin']/div";
	String Wiki_Searchbox="//input[@class='vector-search-box-input']";
	//need to update
	String Wiki_ReleaseDetails="//div[text()='Release date']/parent::th/following-sibling::td";
	String Wiki_Origin="//th[text()='Country']/following-sibling::td";
	//table[@class='infobox vevent']/tbody/tr/th/div[text()='Release date']
	//div[text()='Release date']/parent::th/following-sibling::td
	public static void main(String[] args) throws Exception {
		
		Base1 base=new Base1();
		base.browserInitiation();
		base.imdbValidation_homepage();
		HashMap<String,String> imdb_hs=base.imdbMovieValidation();
		base.Wiki_Homepage();
		HashMap<String,String> wiki_hs=base.Wiki_MovieValidation();
		System.out.println("Print");
	}
	
	public HashMap<String, String> Wiki_MovieValidation() {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Wiki_Searchbox)));
		HashMap<String, String> wiki_hashmap=new HashMap<>();
		
		String ReleaseDetails=driver.findElement(By.xpath(Wiki_ReleaseDetails)).getText();
		String CountryOrigin=driver.findElement(By.xpath(Wiki_Origin)).getText();
		
		if((ReleaseDetails!=null && CountryOrigin!=null))
		{
			wiki_hashmap.put("Release Details", ReleaseDetails);
			wiki_hashmap.put("Country Origin", CountryOrigin);
		}
		
		return wiki_hashmap;
		
	}

	public void Wiki_Homepage() {
		String Wiki_URL=prop.getProperty("WikiURL");
		driver.get(Wiki_URL);
		boolean WikiLabel=driver.findElement(By.id("p-logo")).isDisplayed();
		Assert.assertTrue(WikiLabel);
		
		driver.findElement(By.xpath(Wiki_Searchbox)).sendKeys(SearchMovie);
		driver.findElement(By.id("searchButton")).click();
	}

	public HashMap<String, String> imdbMovieValidation() {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Imdb_ReleaseDetails)));
		HashMap<String, String> imdb_hashmap=new HashMap<>();
		
		String ReleaseDetails_Unfiltered=driver.findElement(By.xpath(Imdb_ReleaseDetails)).getText();
		String[] ReleaseDetails_array = ReleaseDetails_Unfiltered.split(" (United States)");
		String ReleaseDetails = ReleaseDetails_array[0];
		String CountryOrigin=driver.findElement(By.xpath(Imdb_Origin)).getText();
		
		if((ReleaseDetails!=null && CountryOrigin!=null))
		{
			imdb_hashmap.put("Release Details", ReleaseDetails);
			imdb_hashmap.put("Country Origin", CountryOrigin);
		}
		
		return imdb_hashmap;
	}

	public void imdbValidation_homepage() throws Exception
	{
		boolean imdb_label=driver.findElement(By.xpath(imdblabel)).isDisplayed();
		Assert.assertTrue(imdb_label);
		driver.findElement(By.xpath(imdbsearch)).sendKeys(SearchMovie);
		Thread.sleep(2000);
		List<WebElement> list=driver.findElements(By.xpath(autosuggestionlist));
		
		for (WebElement webElement : list) {
			String moviename=webElement.getText();
			
			if(moviename.startsWith(SearchMovie))
			{
				driver.findElement(By.xpath("//div[text()='"+moviename+"']")).click();
				break;
			}
		}
	}
	
	public WebDriver browserInitiation()
	{
		prop=new Properties();
		String currentdirectory=System.getProperty("user.dir");
		System.out.println(currentdirectory);
		try {
			FileInputStream file=new FileInputStream(currentdirectory+"\\src\\main\\java\\resources\\global.properties");
			prop.load(file);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String browser=prop.getProperty("Browser");
		String imdbURL=prop.getProperty("ImdbURL");
		
		int implicitwait=Integer.parseInt(prop.getProperty("Implicitwait"));
		if(browser.equalsIgnoreCase("Implicitwait"));
		{
			ChromeOptions chromeOptions = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver(chromeOptions);
			driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(implicitwait));
			driver.get(imdbURL);
			driver.manage().window().maximize();
			
		}
		return driver;
	}
}
