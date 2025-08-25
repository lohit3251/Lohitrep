package UOB;

	
	import java.io.IOException;
	import java.time.Duration;
	import java.util.HashMap;
	import java.util.Map;

	import org.openqa.selenium.By;
	import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.chrome.ChromeOptions;
	import org.openqa.selenium.support.ui.Select;
	import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

	public class BaseLoginLogout {



		WebDriver driver;
		//ConfigReader configReader;
		//ChromeOptions options = new ChromeOptions();

		@BeforeClass
		public void browseLaunch() throws InterruptedException {

			// options.addArguments("headless");
			ChromeOptions options = new ChromeOptions();
			Map<String, Object> prefs = new HashMap<>();
			prefs.put("profile.default_content_setting_values.mixed_script", 1);// Allow mixed content (HTTP/HTTPS)
			// Apply these preferences to ChromeOptions
			options.setExperimentalOption("prefs", prefs);
			driver = new ChromeDriver(options);
			
			
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

			String userid = "test666";
			String password = "Password1";
			//String url = "http://192.168.2.190:7001/CentralAdmin/";
			String url=		"http://192.168.2.15:7001/CentralAdmin/";

			driver.get(url);
			
			// to check whether logo is present
			WebElement logo = driver.findElement(By.cssSelector("img[alt='UOB Logo']"));
			boolean bologo = logo.isDisplayed();
			System.out.println("UOB Logo is visible:  " + bologo);
			System.out.println("Logo is visible");

			// enter username, passsword and click on login button
			driver.findElement(By.id("username")).sendKeys(userid);
			driver.findElement(By.id("password")).sendKeys(password);

			System.out.println("Ente user name:" + userid);
			System.out.println("Enter password:" + password);

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
			Thread.sleep(4000);

			// to check whether url is matched
			String actualUrl = "http://192.168.2.15:7001/CentralAdmin/layout";
			String expectedUrl = driver.getCurrentUrl();
			Assert.assertEquals(expectedUrl, actualUrl);

			// to validate home page
			WebElement element = driver.findElement(By.xpath("//h1[contains(text(),'Welcome')]"));
			if (element.getText().contains("Welcome"))
				System.out.println("Welcome");
			else
				System.out.println("Match Not found");

			Thread.sleep(3000);
			

			
		}

		@AfterClass
		// Logout
		public void logout() throws InterruptedException, IOException {

			driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/ul[1]/li[2]/a[1]/i[1]")).click();

			driver.close();
		}

	}

