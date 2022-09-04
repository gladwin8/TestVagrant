package resources;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.hc.core5.util.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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

public class Base {

	WebDriver driver;
	public Properties prop;
	
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
		
		
		int implicitwait=Integer.parseInt(prop.getProperty("Implicitwait"));
		if(browser.equalsIgnoreCase("Implicitwait"));
		{
			ChromeOptions chromeOptions = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver(chromeOptions);
			driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(implicitwait));
		
			
		}
		return driver;
	}
	
	public void getScreenshot(String testcaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		String dest=System.getProperty("user.dir")+"\\reports\\" +testcaseName+timestamp()+ ".png";
		FileUtils.copyFile(src, new File(dest));
	}

	private String timestamp() 
	{
		return new SimpleDateFormat("dd_mm_yyyy-hh-mm-ss").format(new Date());
	}
	
	
}
