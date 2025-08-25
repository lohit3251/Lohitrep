import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class dropdowns {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		
		  WebDriver driver = new ChromeDriver();
	        
	        // Open the target web page
	        
	        String url = "http://192.168.2.190:7001/CentralAdmin/";
			String username = "test666";
			String password = "Password1";
			driver.get(url);

			driver.findElement(By.id("username")).sendKeys(username);
			driver.findElement(By.id("password")).sendKeys(password);
//			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//			wait.until(ExpectedConditions.elementToBeClickable(By.id("loginSubmit"))).click();

			JavascriptExecutor js = (JavascriptExecutor) driver;

			js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

			WebElement Drole = driver.findElement(By.id("role"));

			Select select = new Select(Drole);
			// System.out.println(select.getFirstSelectedOption().getText());
			List<WebElement> optionss = select.getOptions();

			for (WebElement option : optionss) {
				if (option.getText().equals("Admin")) {
					option.click();
					break;
				}

			}
			driver.findElement(By.id("loginSubmit")).click();

			String ActualText = driver.findElement(By.xpath("//h1[contains(text(),'Welcome')]")).getText();

			String ExpectedText = "Welcome " + username;

			try {

				Assert.assertEquals(ActualText, ExpectedText, "login not successfull");

				System.out.println("Assertion Passed:login was successful.");
			} catch (AssertionError e) {
				System.out.println("Assertion Failed: " + e.getMessage());

			}
	        
			driver.findElement(By.xpath("//a[normalize-space()='Audit Reports']")).click();
	        // Locate the dropdown element
			Thread.sleep(3000);
	        WebElement reportTypeDropdown = driver.findElement(By.id("cboReports"));
	        
	        
	        // Create a Select object
	        Select dropdown = new Select(reportTypeDropdown);
	        
	        // Get all options from the reportTypeDropdown
	        List<WebElement> allOptions = dropdown.getOptions();
	        
	        // Loop through all options and select each one
	        for (WebElement option : allOptions) {
	            // Select the option by visible text
	            dropdown.selectByVisibleText(option.getText());
	            
	            // Pause for the page to load (you can replace with an explicit wait if needed)
	            try {
	                Thread.sleep(2000);  // Adjust this based on the page load time
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            
	            // After selecting each option, retrieve the relevant data
	            // For example, retrieve text from a result table
//	            WebElement resultTable = driver.findElement(By.id("result_table_id"));
//	            System.out.println("Results for option: " + option.getText());
//	            System.out.println(resultTable.getText());
	        }
	}

}
