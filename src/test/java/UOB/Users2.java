package UOB;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Users2 extends Multiplelogin {

	Robot robot;
	String downloadDir = "C:\\Users\\lohitkumar.gatta\\Downloads";

	String randomString = RandomStringUtils.randomNumeric(3); // Generate 3 a-character random string
	String username = "kevin" + randomString;
	String fileName = "Users_List_26-09-2024";
	String keyword = "Users";
	File downloadedFile = new File(Paths.get(downloadDir, fileName).toString());

	@Test()
	public void pagination() throws InterruptedException, IOException {

		login(user1Username, user1Password);

		driver.findElement(By.xpath("//a[normalize-space()='Users']")).click();
		Thread.sleep(4000);

		boolean hasNextPage = true;

		while (hasNextPage) {
			// Process the current page
			List<WebElement> items = driver.findElements(By.cssSelector(".paginate_button ")); // Adjust selector
			for (WebElement item : items) {
				System.out.println(item.getText()); // Replace with your processing logic
			}

			// Check for the "Next" button
			try {
				WebElement nextButton = driver.findElement(By.cssSelector(".paginate_button next")); // Adjust selector
				if (nextButton.isDisplayed() && nextButton.isEnabled()) {
					nextButton.click(); // Click the "Next" button
				} else {
					hasNextPage = false; // No more pages
				}
			} catch (Exception e) {
				hasNextPage = false; // No "Next" button found
			}
		}
		
		
		logout();
		Thread.sleep(4000);
	}

	@Test(priority = 1)
	public void Createuser() throws InterruptedException, AWTException, IOException {

		login(user1Username, user1Password);

		driver.findElement(By.xpath("//a[normalize-space()='Users']")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("AddNewUser")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("txtLoginId")).sendKeys(username);
		driver.findElement(By.id("txtUserName")).sendKeys("lohit");
		driver.findElement(By.id("txtDept")).sendKeys("IT");

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("window.scrollBy(0,document.body.scrollHeight);");
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

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SaveUser"))).click();
		Thread.sleep(2000);

		WebElement popup = driver.findElement(By.className("jBox-content"));
		String message = popup.getText();
		System.out.println("success alert: " + message);
		Thread.sleep(2000);
		robot = new Robot();

		// Press and release the Escape key
		robot.keyPress(KeyEvent.VK_ESCAPE);
		robot.keyRelease(KeyEvent.VK_ESCAPE);

		logout();
		Thread.sleep(4000);

//Approver login

		login(user2Username, user2Password);
		Thread.sleep(2000);

		driver.findElement(By.xpath("//a[normalize-space()='Users']")).click();
		Thread.sleep(3000);

		driver.findElement(By.xpath("//input[@aria-controls='UsersList']")).sendKeys(username);
		Thread.sleep(3000);

		WebElement status = driver.findElement(By.xpath("//table[@id='UsersList']/tbody/tr/td[9]/span"));
		String ActualStatus = status.getText();
		String Expected = "Pending Approval";

		if (ActualStatus.equals(Expected)) {
			System.out.println("Approver Status Matched for user creation '" + ActualStatus + "'");
		} else {
			System.out.println("Approver Status for user creation Not Matched");
		}

		Thread.sleep(2000);
		driver.findElement(By.xpath("//i[@class='fa fa-edit tbl_edt_btn editUser']")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("ApproveUser")).click();
		Thread.sleep(3000);

		WebElement popup2 = driver.findElement(By.className("jBox-content"));
		String message2 = popup2.getText();
		System.out.println("success alert: " + message2);
		Thread.sleep(1000);

		robot.keyPress(KeyEvent.VK_ESCAPE);
		robot.keyRelease(KeyEvent.VK_ESCAPE);

//approver logout
		logout();
		Thread.sleep(4000);

//Admin user login

		login(user1Username, user1Password);
		Thread.sleep(3000);

		driver.findElement(By.xpath("//a[normalize-space()='Users']")).click();

		Thread.sleep(3000);

		driver.findElement(By.xpath("//input[@type='search']")).sendKeys(username);
		Thread.sleep(5000);
		WebElement status1 = driver.findElement(By.xpath("//table[@id='UsersList']/tbody/tr/td[9]"));
		String ActualStatus1 = status1.getText();
		String Expected1 = "Approved";

		if (ActualStatus1.equals(Expected1)) {
			System.out.println("Adminuser Status Matched for user creation '" + ActualStatus1 + "'");
		} else {
			System.out.println("Adminuser Status for user creation Not Matched");
		}
		logout();
	}

	@Test(priority = 2) // view button functionality
	public void view() throws InterruptedException, IOException {

		login(user1Username, user1Password);
		Thread.sleep(3000);

		driver.findElement(By.xpath("//a[normalize-space()='Users']")).click();

		Thread.sleep(4000);

		driver.findElement(By.xpath("//input[@type='search']")).sendKeys(username);

		Thread.sleep(3000);
		driver.findElement(By.xpath("//i[@class='fa fa-eye tbl_edt_btn viewUser']")).click();
		screenshot();
		System.out.println("Screenshot taken");

		driver.findElement(By.xpath("//button[@id='CloseUserDetails']")).click();
		Thread.sleep(3000);
		logout();

	}

	@Test(priority = 3) // Edit button functionality
	public void edit() throws InterruptedException, IOException {

		login(user1Username, user1Password);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[normalize-space()='Users']")).click();

		Thread.sleep(3000);

		driver.findElement(By.xpath("//input[@aria-controls='UsersList']")).sendKeys(username);
		Thread.sleep(2000);

		driver.findElement(By.xpath("//i[@class='fa fa-edit tbl_edt_btn editUser']")).click();
		Thread.sleep(4000);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 300);");

		driver.findElement(By.xpath(
				"//body[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[5]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr[3]/td[3]/i[1]"))
				.click();
		System.out.println("Edit: Removed group");
		WebElement body1 = driver.findElement(By.tagName("body"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", body1);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@id='SaveUser']")).click();
		Thread.sleep(3000);

		WebElement popup3 = driver.findElement(By.className("jBox-content"));
		String message3 = popup3.getText();
		System.out.println("success alert: " + message3);
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
		WebElement status2 = driver.findElement(By.xpath("//table[@id='UsersList']/tbody/tr/td[9]/span"));
		String ActualStatus2 = status2.getText();
		String Expected2 = "Pending Approval";

		if (ActualStatus2.equals(Expected2)) {
			System.out.println("Approver Status Matched for edit '" + ActualStatus2 + "'");
		} else {
			System.out.println("Approver Status for edit Not Matched");
		}

		driver.findElement(By.xpath("//i[@class='fa fa-edit tbl_edt_btn editUser']")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("ApproveUser")).click();
		Thread.sleep(3000);
		WebElement popup4 = driver.findElement(By.className("jBox-content"));
		String message4 = popup4.getText();
		System.out.println("success alert: " + message4);
		Thread.sleep(1000);
		robot.keyPress(KeyEvent.VK_ESCAPE);
		robot.keyRelease(KeyEvent.VK_ESCAPE);
		// Approver logout
		logout();
		Thread.sleep(4000);
	}

	// Admin user login
	@Test(priority = 4)
	public void Delete() throws InterruptedException, IOException {
		login(user1Username, user1Password);
		Thread.sleep(3000);

		driver.findElement(By.xpath("//a[normalize-space()='Users']")).click();

		Thread.sleep(3000);

		driver.findElement(By.xpath("//input[@type='search']")).sendKeys(username);
		Thread.sleep(3000);
		WebElement status3 = driver.findElement(By.xpath("//table[@id='UsersList']/tbody/tr/td[9]"));
		String ActualStatus3 = status3.getText();
		String Expected3 = "Approved";

		if (ActualStatus3.equals(Expected3)) {
			System.out.println("Adminuser Status Matched for edit '" + ActualStatus3 + "'");
		} else {
			System.out.println("Adminuser Status for edit Not Matched");
		}

//delete functionality
		driver.findElement(By.xpath("//i[@class='fa fa-trash tbl_del_btn deleteUser']")).click();

		driver.findElement(By.xpath("//div[@class='jBox-Confirm-button jBox-Confirm-button-submit']")).click();

		Thread.sleep(1000);
		WebElement popup5 = driver.findElement(By.className("jBox-content"));
		String message5 = popup5.getText();
		Thread.sleep(1000);
		System.out.println("success alert: " + message5);
		Thread.sleep(3000);
		robot.keyPress(KeyEvent.VK_ESCAPE);
		robot.keyRelease(KeyEvent.VK_ESCAPE);
		Thread.sleep(1000);
		// Admin user logout
		logout();
		Thread.sleep(2000);

		// Approver login
		login(user2Username, user2Password);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[normalize-space()='Users']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@aria-controls='UsersList']")).sendKeys(username);
		Thread.sleep(3000);
		WebElement status4 = driver.findElement(By.xpath("//table[@id='UsersList']/tbody/tr/td[9]/span"));
		String ActualStatus4 = status4.getText();
		String Expected4 = "Pending Deletion";

		if (ActualStatus4.equals(Expected4)) {
			System.out.println("Approver Status Matched for delete '" + ActualStatus4 + "'");
		} else {
			System.out.println("Approver Status for delete Not Matched");
		}

		driver.findElement(By.xpath("//i[@class='fa fa-edit tbl_edt_btn editUser']")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("RejectUser")).click();
		Thread.sleep(1000);
		WebElement popup6 = driver.findElement(By.className("jBox-content"));
		String message6 = popup6.getText();
		System.out.println("success alert: " + message6);
		Thread.sleep(3000);

		robot.keyPress(KeyEvent.VK_ESCAPE);
		robot.keyRelease(KeyEvent.VK_ESCAPE);
		Thread.sleep(1000);
		// Approver logout
		logout();
		Thread.sleep(2000);

		// Admin user login
		login(user1Username, user1Password);
		Thread.sleep(3000);

		driver.findElement(By.xpath("//a[normalize-space()='Users']")).click();

		Thread.sleep(5000);

		driver.findElement(By.xpath("//input[@type='search']")).sendKeys(username);
		Thread.sleep(3000);
		WebElement status5 = driver.findElement(By.xpath("//table[@id='UsersList']/tbody/tr/td[9]"));
		String ActualStatus5 = status5.getText();
		String Expected5 = "Approved";

		if (ActualStatus5.equals(Expected5)) {
			System.out.println("Adminuser Status Matched for delete '" + ActualStatus5 + "'");
		} else {
			System.out.println("Adminuser Status for delete Not Matched");

		}
		Thread.sleep(3000);
		// copy button
		WebElement copyButton = driver.findElement(By.xpath("//span[normalize-space()='Copy']"));
		copyButton.click();
		WebElement popup1 = driver.findElement(By.xpath("//h2[contains(text(),'Copy to clipboard')]"));
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait1.until(ExpectedConditions.visibilityOf(popup1));

		String actual = popup1.getText();

		String expected = "Copy to clipboard";
		if (actual.equals(expected)) {
			System.out.println("text copied");

		} else {
			System.out.println("text not copied");
		}

		Thread.sleep(2000);
		// csv button

		WebElement exportCsvButton = driver.findElement(By.xpath("//span[normalize-space()='CSV']"));
		exportCsvButton.click();
		System.out.println("csv button clicked");
		Thread.sleep(2000);

		if (downloadedFile.exists() && downloadedFile.getName().contains(keyword)) {
			System.out.println("File csv available.");
		}

		else {
			System.out.println("File csv not found.");

		}

		// excel button
		WebElement exportxlsxButton = driver.findElement(By.xpath("//span[normalize-space()='Excel']"));
		exportxlsxButton.click();
		System.out.println("Excel button clicked");
		Thread.sleep(2000);

		// pdf button
		WebElement exporpdfButton = driver.findElement(By.xpath("//span[normalize-space()='PDF']"));
		exporpdfButton.click();
		System.out.println("PDF button clicked");
		Thread.sleep(2000);

		// print button
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

		// all pdf
		WebElement allpdfbutton = driver.findElement(By.xpath("//span[normalize-space()='Export all to PDF']"));
		allpdfbutton.click();
		System.out.println("Export all to PDF button clicked");
		Thread.sleep(2000);

		// all excel button
		WebElement allxlsx = driver.findElement(By.xpath("//span[normalize-space()='Export all to Excel']"));
		allxlsx.click();
		System.out.println("Export all to Excel button clicked");
		Thread.sleep(2000);

		WebElement allpdfDept = driver
				.findElement(By.xpath("//span[normalize-space()='Export all to PDF By Department']"));
		allpdfDept.click();
		System.out.println("Export all to PDF By Department button clicked");
		Thread.sleep(2000);
		logout();

	}

}
