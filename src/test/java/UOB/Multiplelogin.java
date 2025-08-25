package UOB;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class Multiplelogin {
	
	

	WebDriver driver;
	//ConfigReader configReader;
	ChromeOptions options = new ChromeOptions();
	
	
	// User credentials
    String user1Username = "test666";
    String user1Password = "Password1";
    
    String user2Username = "raman";
    String user2Password = "Password1";
	

	@BeforeMethod
	public void browseLaunch() throws InterruptedException {
		
		

		// options.addArguments("headless");
		ChromeOptions options = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("profile.default_content_setting_values.mixed_script", 1);// Allow mixed content (HTTP/HTTPS)
		// Apply these preferences to ChromeOptions
		options.setExperimentalOption("prefs", prefs);
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("http://192.168.2.15:7001/CentralAdmin/");
		
		//String url = "http://192.168.2.190:7001/CentralAdmin/";
		//String url=		"http://192.168.2.15:7001/CentralAdmin/";
		
		
	}


	
    
    
	public void login(String username, String password) throws InterruptedException {
        
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        
        Select domain = new Select(driver.findElement(By.id("domain")));
		domain.selectByVisibleText("DEV");

		// Scroll down
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 5000);");
		Thread.sleep(2000);

		Select Database = new Select(driver.findElement(By.id("database")));
		Database.selectByVisibleText("Retail");

		Select Role = new Select(driver.findElement(By.id("role")));
		Role.selectByVisibleText("Admin");

		driver.findElement(By.id("loginSubmit")).click();
		Thread.sleep(3000);
		
		
    }
	
	public void screenshot() throws IOException
	{
		Date currentdate = new Date();
		String dates = currentdate.toString().replace(" ", "-").replace(":", "-");
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotFile, new File("./screenshot/"+dates+"Screenshot.png"));
	}
	
	

	
	// Logout
     public void logout() throws InterruptedException, IOException {
		

		driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/ul[1]/li[2]/a[1]/i[1]")).click();

		
		
		
	}
	
     @AfterMethod
	public void teardown()
	{

		if (driver != null)
			driver.close();
	}
}


