package FunctionalLibraries;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.Imdb;
import pageObjects.Wikipedia;
import resources.Base;

public class EndtoEnd extends Base{

	public WebDriver driver;
	//String SearchMovie="Pushpa: The Rise";
	@BeforeClass
	public void InitializeBrowser()
	{
		
		driver=browserInitiation(); 
		String imdbURL=prop.getProperty("ImdbURL");
		driver.get(imdbURL);
		driver.manage().window().maximize();
	}
	
	@Test(dataProvider="getMovie")
	public void Imdb_Wiki_validation(String SearchMovie) throws Exception
	{
		imdbValidation_homepage(SearchMovie);
		HashMap<String,String> imdb_hashmap=imdbMovieValidation();
		Wiki_Homepage(SearchMovie);
		HashMap<String,String> wiki_hashmap=Wiki_MovieValidation();
		String imdb_Release_Details=imdb_hashmap.get("Release Details");
		String wiki_Release_Details=wiki_hashmap.get("Release Details");
		SoftAssert soft= new SoftAssert();
		soft.assertEquals(imdb_Release_Details, wiki_Release_Details);
		soft.assertAll();
		if(imdb_Release_Details.equalsIgnoreCase(wiki_Release_Details))
			System.out.println(SearchMovie+" movie release date matches with imdb");
		else
			System.out.println(SearchMovie+" movie release date does not matches with imdb");
		
		String imdb_Country=imdb_hashmap.get("Country Origin");
		String wiki_Country=wiki_hashmap.get("Country Origin");
		Assert.assertEquals(imdb_Country, wiki_Country);
		
		if(imdb_Country.equalsIgnoreCase(wiki_Country))
			System.out.println(SearchMovie+" movie country origin matches with imdb");
		else
			System.out.println(SearchMovie+" movie release date does not matches with imdb");
		
		
	}
	
	public void imdbValidation_homepage(String SearchMovie) throws Exception
	{
		Imdb imdb =new Imdb(driver);
		boolean imdb_label=imdb.imdblabel().isDisplayed();
		Assert.assertTrue(imdb_label);
		imdb.imdbsearch().sendKeys(SearchMovie);
		Thread.sleep(2000);
		List<WebElement> list=imdb.Autosuggestions();
		
		for (WebElement webElement : list) {
			String moviename=webElement.getText();
			
			if(moviename.startsWith(SearchMovie))
			{
				imdb.Moviename_suggestions(moviename).click();
				break;
			}
		}
	}
	
	public HashMap<String, String> imdbMovieValidation() {
		
		Imdb imdb = new Imdb(driver);
		imdb.ExplicitWait(30);
		HashMap<String, String> imdb_hashmap=new HashMap<>();
		String ReleaseDetails = imdb.Imdb_ReleaseDetails().getText();
		String CountryOrigin=imdb.Imdb_Origin().getText();
		
		if((ReleaseDetails!=null && CountryOrigin!=null))
		{
			imdb_hashmap.put("Release Details", ReleaseDetails);
			imdb_hashmap.put("Country Origin", CountryOrigin);
		}
		
		return imdb_hashmap;
	}
	
	public void Wiki_Homepage(String SearchMovie) {
		String Wiki_URL=prop.getProperty("WikiURL");
		driver.get(Wiki_URL);
		Wikipedia wiki = new Wikipedia(driver);
		boolean WikiLabel=wiki.WikiLabel().isDisplayed();
		Assert.assertTrue(WikiLabel);
		wiki.Searchbox().sendKeys(SearchMovie);
		wiki.Searchbutton().click();
	}
	
	public HashMap<String, String> Wiki_MovieValidation() 
	{
		Wikipedia wiki = new Wikipedia(driver);
		wiki.Explicitlywait(30);
		HashMap<String, String> wiki_hashmap=new HashMap<>();
		
		String ReleaseDetails=wiki.ReleaseDetails().getText();
		String CountryOrigin=wiki.Origin().getText();
		
		if((ReleaseDetails!=null && CountryOrigin!=null))
		{
			wiki_hashmap.put("Release Details", ReleaseDetails);
			wiki_hashmap.put("Country Origin", CountryOrigin);
		}
		
		return wiki_hashmap;
		
	}
	
	
	
	@AfterClass
	public void Browser_close()
	{
		driver.close();
	}
	
	@DataProvider
	public Object[][] getMovie()
	{
		Object[][] data=new Object[1][1];
		data[0][0]="Pushpa: The Rise";
		
				
		return data;
	}
}
