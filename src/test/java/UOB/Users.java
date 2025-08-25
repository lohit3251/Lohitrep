package UOB;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import junit.framework.Assert;

public class Users extends Multiplelogin {

	@Test
	public void Createuser() throws InterruptedException, AWTException, IOException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String currentDate = sdf.format(new Date());
		String downloadDir = "C:\\Users\\lohitkumar.gatta\\Downloads";
		
		login(user1Username, user1Password);
		driver.findElement(By.xpath("//a[normalize-space()='Users']")).click();
		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	
		  String usernameToSearch = "kevin523";
	        boolean usernameFound = false;

	        // Loop through pages until the username is found or no more pages are left
	        while (true) {
	           
	            List<WebElement> rows = driver.findElements(By.xpath("//table[@id='UsersList']//tbody/tr"));
	            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	           
	            for (WebElement row : rows) {
	                
	                WebElement usernameColumn = row.findElement(By.xpath(".//td[2]"));
	                String username = usernameColumn.getText();
	              
	                if (username.equals(usernameToSearch)) {
	                    
	                    WebElement editButton = row.findElement(By.cssSelector(".fa.fa-edit.tbl_edt_btn.editUser"));
	                    editButton.click();
	                    Thread.sleep(4000);
	                    usernameFound = true;
	                    break; 
	                }
	            }
	           
	            if (usernameFound) {
	                System.out.println("Username found and Edit button clicked.");
	                break;
	            }
	            // Check if the "Next" button exists and is enabled (to navigate to the next page)
	            try {
	                WebElement nextButton = driver.findElement(By.xpath("//a[@id='UsersList_next']"));
	                nextButton.click();
	                Thread.sleep(1000);  
	            } catch (Exception e) {
	                // If "Next" button is not found or is disabled, stop pagination
	                System.out.println("No more pages to search. Username not found.");
	                break;
	            }
	        }

		/*js.executeScript("window.scrollBy(0,document.body.scrollHeight);");
		List<WebElement> items = driver.findElements(By.cssSelector(".paginate_button"));
		for (WebElement item : items) 
		{
			System.out.println(item.getText()); 
			Thread.sleep(1000);
		}
		
		 try {		 

	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	            String disabledClassName = "paginate_button next disabled"; // Replace with the actual class name that indicates the button is disabled
	            int i=2;
	            while (true) {
	                // Locate the "Next" button
	                WebElement nextButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".paginate_button.next"))); // Replace with your button's ID
	  
	                // Check if the button is enabled or has the disabled class
	                if (!nextButton.isEnabled() || nextButton.getAttribute("class").contains(disabledClassName)) 
	                {
	                    System.out.println("Next button is disabled or has the disabled class. Exiting loop.");
	                    break; // Exit the loop if the button is disabled
	                }

	                // Click the "Next" button
	                js.executeScript("window.scrollBy(0,document.body.scrollHeight);");
	                nextButton.click();
	                System.out.println("Clicked on the page No.: "+i);
	                i++;

	                // Optionally, add a wait to allow the page to load or changes to take effect
	                Thread.sleep(1000); // Adjust the wait time as needed
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } 
		
		/*boolean hasNextPage = true;
		while (hasNextPage) 
		{
			// Process the current page
			// Check for the "Next" button
			try {
				WebElement nextButton = driver.findElement(By.cssSelector(".paginate_button.next"));
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		        wait.until(ExpectedConditions.elementToBeClickable(nextButton));
			if (nextButton.isDisplayed() && nextButton.isEnabled())
				{
					js.executeScript("window.scrollBy(0,document.body.scrollHeight);");
					nextButton.click(); 
					Thread.sleep(1000);
				} else
				
				{
					hasNextPage = false; // No more pages
				}
			
		}catch (NoSuchElementException e) {
	        hasNextPage = false; // No "Next" button found
	    } catch (StaleElementReferenceException e) {
	        // If the next button goes stale, we can handle this case
	        // Continue the loop to try again
	    } catch (Exception e) {
	        // Handle any other exceptions as needed
	        e.printStackTrace();
	        hasNextPage = false; // Fail-safe to prevent infinite loop
	    }
		}
		
		logout();
		Thread.sleep(4000);*/
//		String username="kevin120";
//		
//
//		driver.findElement(By.xpath("//a[normalize-space()='Users']")).click();	
//		Thread.sleep(2000);
//		
//		
		/*driver.findElement(By.id("AddNewUser")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("txtLoginId")).sendKeys(username);
		driver.findElement(By.id("txtUserName")).sendKeys("lohit");
		driver.findElement(By.id("txtDept")).sendKeys("IT");

		JavascriptExecutor js1 = (JavascriptExecutor) driver;

		js1.executeScript("window.scrollBy(0,document.body.scrollHeight);");
		Thread.sleep(2000);
		List<WebElement> checkbox = driver.findElements(By.xpath("//input[@type='checkbox']"));

		for (int i = 0; i <= 4; i++) {
			checkbox.get(i).click();

			Thread.sleep(100);
		}

		WebElement Gdropdown = driver.findElement(By.id("cboGroups"));

		Select select = new Select(Gdropdown);
		select.selectByVisibleText("GRP_UOBT_NPP_ADV");
		driver.findElement(By.id("AddGroup")).click();
		select.selectByValue("278");
		driver.findElement(By.id("AddGroup")).click();
		select.selectByIndex(1);
		driver.findElement(By.id("AddGroup")).click();
		
	
	    WebElement body = driver.findElement(By.tagName("body"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", body);
		
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SaveUser"))).click();
		Thread.sleep(2000);
		
		WebElement popup =driver.findElement(By.className("jBox-content"));
		String message=popup.getText();
		System.out.println("success alert: "+message);
		Thread.sleep(2000);
		
		 Robot robot = new Robot();
		   // Press and release the Escape key
		  robot.keyPress(KeyEvent.VK_ESCAPE); 
		  robot.keyRelease(KeyEvent.VK_ESCAPE);
		  
		  logout();
		  Thread.sleep(4000);
		  
		  

//Approver login
		login(user2Username, user2Password);	
		Thread.sleep(2000);   
		
		driver.findElement(By.xpath("//a[normalize-space()='Users']")).click();
		Thread.sleep(2000);		
		
		driver.findElement(By.xpath("//input[@aria-controls='UsersList']")).sendKeys(username);
		Thread.sleep(3000);
		
		WebElement status=driver.findElement(By.xpath("//table[@id='UsersList']/tbody/tr/td[9]/span"));
		String ActualStatus=status.getText();
		String Expected="Pending Approval";
		
		
		if (ActualStatus.equals(Expected)) {
			System.out.println("Approver Status Matched for user creation '"+ActualStatus+"'");
		} else {
			System.out.println("Approver Status for user creation Not Matched");
		}
		
		
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//i[@class='fa fa-edit tbl_edt_btn editUser']")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("ApproveUser")).click();
		Thread.sleep(3000);
		
		WebElement popup2 =driver.findElement(By.className("jBox-content"));
		String message2=popup2.getText();
		System.out.println("success alert: "+message2);
		Thread.sleep(1000);
		
		
		robot.keyPress(KeyEvent.VK_ESCAPE); 
		robot.keyRelease(KeyEvent.VK_ESCAPE);
		
//approver logout
		logout();
		Thread.sleep(4000);
		
		
		
		
		
		
//Admin user login
		
		login(user1Username, user1Password);
		Thread.sleep(2000);		
	
		driver.findElement(By.xpath("//a[normalize-space()='Users']")).click();
		
		Thread.sleep(3000);
		
	
		driver.findElement(By.xpath("//input[@type='search']")).sendKeys(username);
		Thread.sleep(5000);
		WebElement status1=driver.findElement(By.xpath("//table[@id='UsersList']/tbody/tr/td[9]"));
		String ActualStatus1=status1.getText();
		String Expected1="Approved";
		
		if (ActualStatus1.equals(Expected1)) {
			System.out.println("Adminuser Status Matched for user creation '"+ActualStatus1+"'");
		} else {
			System.out.println("Adminuser Status for user creation Not Matched");
		}
		
		
		
		
		
		
//view button functionality
		driver.findElement(By.xpath("//i[@class='fa fa-eye tbl_edt_btn viewUser']")).click();
		screenshot();
		System.out.println("Screenshot taken");
		
		driver.findElement(By.xpath("//button[@id='CloseUserDetails']")).click();
		Thread.sleep(3000);
		
//Edit button functionality
		driver.findElement(By.xpath("//input[@aria-controls='UsersList']")).sendKeys(username);
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//i[@class='fa fa-edit tbl_edt_btn editUser']")).click();
		Thread.sleep(4000);
		js1.executeScript("window.scrollBy(0, 300);");
		
		driver.findElement(By.xpath("//body[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[5]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr[3]/td[3]/i[1]")).click();
		System.out.println("Edit: Removed group");
	    WebElement body1 = driver.findElement(By.tagName("body"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", body1);
        Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@id='SaveUser']")).click();
		Thread.sleep(3000);
		
		WebElement popup3 =driver.findElement(By.className("jBox-content"));
		String message3=popup3.getText();
		System.out.println("success alert: "+message3);
		Thread.sleep(1000);
		robot.keyPress(KeyEvent.VK_ESCAPE); 
		robot.keyRelease(KeyEvent.VK_ESCAPE);
		
//Admin user logout
		logout();
		Thread.sleep(4000);
		
//Approver login
		login(user2Username, user2Password);	
				Thread.sleep(2000);
				driver.findElement(By.xpath("//a[normalize-space()='Users']")).click();
				Thread.sleep(3000);				
				driver.findElement(By.xpath("//input[@aria-controls='UsersList']")).sendKeys(username);
				Thread.sleep(3000);
				WebElement status2=driver.findElement(By.xpath("//table[@id='UsersList']/tbody/tr/td[9]/span"));
				String ActualStatus2=status2.getText();
				String Expected2="Pending Approval";
				
				
				if (ActualStatus2.equals(Expected2)) {
					System.out.println("Approver Status Matched for edit '"+ActualStatus2+"'");
				} else {
					System.out.println("Approver Status for edit Not Matched");
				}	
				
				
				driver.findElement(By.xpath("//i[@class='fa fa-edit tbl_edt_btn editUser']")).click();
				Thread.sleep(2000);
				driver.findElement(By.id("ApproveUser")).click();
				Thread.sleep(3000);
				WebElement popup4 =driver.findElement(By.className("jBox-content"));
				String message4=popup4.getText();
				System.out.println("success alert: "+message4);
				Thread.sleep(1000);
				robot.keyPress(KeyEvent.VK_ESCAPE); 
				robot.keyRelease(KeyEvent.VK_ESCAPE);
		//Approver logout
				logout();
				Thread.sleep(4000);
				
				
		//Admin user login
				
				login(user1Username, user1Password);
				Thread.sleep(2000);		
			
				driver.findElement(By.xpath("//a[normalize-space()='Users']")).click();
				
				Thread.sleep(3000);
				
			
				driver.findElement(By.xpath("//input[@type='search']")).sendKeys(username);
				Thread.sleep(3000);
				WebElement status3=driver.findElement(By.xpath("//table[@id='UsersList']/tbody/tr/td[9]"));
				String ActualStatus3=status3.getText();
				String Expected3="Approved";
				
				if (ActualStatus3.equals(Expected3)) {
					System.out.println("Adminuser Status Matched for edit '"+ActualStatus3+"'");
				} else {
					System.out.println("Adminuser Status for edit Not Matched");
				}
				
//delete functionality
				driver.findElement(By.xpath("//i[@class='fa fa-trash tbl_del_btn deleteUser']")).click();
				
				
				
				driver.findElement(By.xpath("//div[@class='jBox-Confirm-button jBox-Confirm-button-submit']")).click();
				
				
				Thread.sleep(1000);
				WebElement popup5 =driver.findElement(By.className("jBox-content"));
				String message5=popup5.getText();
				System.out.println("success alert: "+message5);
				Thread.sleep(2000);
				robot.keyPress(KeyEvent.VK_ESCAPE); 
				robot.keyRelease(KeyEvent.VK_ESCAPE);
		//Admin user logout
				logout();
				Thread.sleep(4000);
				
				
				
		//Approver login
				login(user2Username, user2Password);	
				Thread.sleep(2000);
				driver.findElement(By.xpath("//a[normalize-space()='Users']")).click();
				Thread.sleep(3000);				
				driver.findElement(By.xpath("//input[@aria-controls='UsersList']")).sendKeys(username);
				Thread.sleep(3000);
				WebElement status4=driver.findElement(By.xpath("//table[@id='UsersList']/tbody/tr/td[9]/span"));
				String ActualStatus4=status4.getText();
				String Expected4="Pending Deletion";
				
				if (ActualStatus4.equals(Expected4)) {
					System.out.println("Approver Status Matched for delete '"+ActualStatus4+"'");
				} else {
					System.out.println("Approver Status for delete Not Matched");
				}
				
				
				
				driver.findElement(By.xpath("//i[@class='fa fa-edit tbl_edt_btn editUser']")).click();
				Thread.sleep(2000);
				driver.findElement(By.id("RejectUser")).click();
				Thread.sleep(1000);
				WebElement popup6 =driver.findElement(By.className("jBox-content"));
				String message6=popup6.getText();
				System.out.println("success alert: "+message6);
				Thread.sleep(1000);
				
				robot.keyPress(KeyEvent.VK_ESCAPE); 
				robot.keyRelease(KeyEvent.VK_ESCAPE);
		//Approver logout
			 logout();
				Thread.sleep(4000);
				
				
		//Admin user login
				login(user1Username, user1Password);
				Thread.sleep(2000);		
			
				driver.findElement(By.xpath("//a[normalize-space()='Users']")).click();
				
				Thread.sleep(3000);
				
			
				driver.findElement(By.xpath("//input[@type='search']")).sendKeys(username);
				Thread.sleep(2000);
				WebElement status5=driver.findElement(By.xpath("//table[@id='UsersList']/tbody/tr/td[9]"));
				String ActualStatus5=status5.getText();
				String Expected5="Approved";
				
				if (ActualStatus5.equals(Expected5)) {
					System.out.println("Adminuser Status Matched for delete '"+ActualStatus5+"'");
				} else {
					System.out.println("Adminuser Status for delete Not Matched");
					
				}		
				
				Thread.sleep(3000);		
				//copy button
				WebElement copyButton = driver.findElement(By.xpath("//span[normalize-space()='Copy']"));
				copyButton.click();
				WebElement popup1 = driver.findElement(By.xpath("//h2[contains(text(),'Copy to clipboard')]"));
				WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));

				wait1.until(ExpectedConditions.visibilityOf(popup));

				String actual = popup1.getText();

				String expected = "Copy to clipboard";
				if (actual.equals(expected)) {
					System.out.println("text copied");

				} else {
					System.out.println("text not copied");
				}
				
				//csv button
				
				WebElement exportCsvButton = driver.findElement(By.xpath("//span[normalize-space()='CSV']"));
				exportCsvButton.click();
				System.out.println("csv button clicked");
				Thread.sleep(3000);				
				
				//excel button
				WebElement exportxlsxButton = driver.findElement(By.xpath("//span[normalize-space()='Excel']"));
				exportxlsxButton.click();
				System.out.println("Excel button clicked");
				Thread.sleep(2000);				
				
				//pdf button
				WebElement exporpdfButton = driver.findElement(By.xpath("//span[normalize-space()='PDF']"));
				exporpdfButton.click();
				System.out.println("PDF button clicked");
				Thread.sleep(2000);
				
				//print button
				WebElement exportprintButton = driver.findElement(By.xpath("//span[normalize-space()='Print']"));
				exportprintButton.click();			    
				Set<String> allWindowHandles = driver.getWindowHandles();
				Iterator<String> it = allWindowHandles.iterator();
				String parent = it.next();
				String child = it.next();

				// Iterate over all window handles

				driver.switchTo().window(child);
				Thread.sleep(3000);
				driver.close();
				System.out.println("Print button clicked");
				driver.switchTo().window(parent);
				
				//all pdf
				WebElement allpdfbutton = driver.findElement(By.xpath("//span[normalize-space()='Export all to PDF']"));
				allpdfbutton.click();
				System.out.println("Export all to PDF button clicked");
				Thread.sleep(2000);

				//all excel button
				WebElement allxlsx = driver.findElement(By.xpath("//span[normalize-space()='Export all to Excel']"));
				allxlsx.click();
				System.out.println("Export all to Excel button clicked");
				Thread.sleep(2000);				
				
				WebElement allpdfDept = driver.findElement(By.xpath("//span[normalize-space()='Export all to PDF By Department']"));
				allpdfDept.click();
				System.out.println("Export all to PDF By Department button clicked");
				
				logout();*/		
				
				

	}
}

	

