package UOB;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class aiflogin {

	//public static void main(String[] args) throws InterruptedException {
		
		  @Test void login()
		  {
	
	ChromeOptions options = new ChromeOptions();
	WebDriver driver = new ChromeDriver(options);
	Map<String, Object> prefs = new HashMap<>();
	prefs.put("profile.default_content_setting_values.mixed_script", 1);// Allow mixed content (HTTP/HTTPS)
	// Apply these preferences to ChromeOptions options
	options.setExperimentalOption("prefs", prefs);
	
	
	
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

	String userid = "lohitqa";
	String password = "Lohit@123456";
	String domain="ihh_doctors";
	//String url = "http://192.168.2.190:7001/CentralAdmin/";
	String url=		"https://uatif.advintek.com.my:643/";

	driver.get(url);
	driver.findElement(By.id("exampleInputEmail1")).sendKeys(userid);
	driver.findElement(By.id("exampleInputPassword1")).sendKeys(password);
	driver.findElement(By.id("exampleInputDomain1")).sendKeys(domain);
	driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();
	

}
}

