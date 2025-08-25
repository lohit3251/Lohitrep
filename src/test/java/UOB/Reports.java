package UOB;

import java.io.File;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Reports extends BaseLoginLogout {
		@Test
	public void Userloginlogoutreport() throws InterruptedException, IOException
	{
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String currentDate = sdf.format(new Date());
		String downloadDir = "C:\\Users\\lohitkumar.gatta\\Downloads";

	

		// Access reports
		driver.findElement(By.xpath("//a[normalize-space()='Audit Reports']")).click();
		WebElement reportTypeDropdown = driver.findElement(By.id("cboReports"));
		
		
		Select selectReportType = new Select(reportTypeDropdown);
		
		
		selectReportType.selectByVisibleText("User Login Logout Report");

		// Enter Date From and Date To
		WebElement dateFromField = driver.findElement(By.xpath("//input[@name='DateFrom']"));

		dateFromField.click();
		driver.findElement(By.xpath("//td[@class='weekend available'][normalize-space()='15']")).click();
		Thread.sleep(1000);
		WebElement dateToField = driver.findElement(By.xpath("//input[@name='DateTo']"));

		dateToField.click();

		driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[1]/div[2]/table[1]/tbody[1]/tr[4]/td[2]")).click();

		// Click the "Get Report" button
		WebElement getReportButton = driver.findElement(By.id("GetReport"));
		getReportButton.click();

		// Wait for the report to be generated
		Thread.sleep(3000);
		
		
		
		WebElement table = driver.findElement(By.xpath("//table[@id='Report']"));
	    List<WebElement> headers = table.findElements(By.xpath(".//thead/tr/th"));
        String[] expectedHeaders = {
            "User ID",
            "Login Date Time",
            "Logout Date Time",        
        };

        // Check if each expected header is present
        for (String expectedHeader : expectedHeaders) {
            boolean found = false;
            for (WebElement header : headers) {
                if (header.getText().equalsIgnoreCase(expectedHeader)) {
                    found = true;
                    break;
                }
            }
            if (found) {
                System.out.println("Header '" + expectedHeader + "' is present.");
            } else {
                System.out.println("Header '" + expectedHeader + "' is NOT present.");
            }
        }
		// Extract data from the generated report table
		List<WebElement> rows = driver.findElements(By.xpath("//table[@id='Report']/tbody/tr"));

		// Print User ID and Login Date Time for each row
		for (WebElement row : rows) {
//              String userId = row.findElement(By.xpath("td[1]")).getText();
//              String loginDateTime = row.findElement(By.xpath("td[2]")).getText();
//              String logoutDateTime = row.findElement(By.xpath("td[3]")).getText();

			String data = row.getText();
			System.out.println(data);
		}

	

		WebElement copyButton = driver.findElement(By.xpath("//span[normalize-space()='Copy']"));
		copyButton.click();
		WebElement popup = driver.findElement(By.xpath("//h2[contains(text(),'Copy to clipboard')]"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.visibilityOf(popup));

		String actual = popup.getText();

		String expected = "Copy to clipboard";
		if (actual.equals(expected)) {
			System.out.println("text copied");

		} else {
			System.out.println("text not copied");
		}

		Thread.sleep(3000);
	

	
		WebElement exportCsvButton = driver.findElement(By.xpath("//span[normalize-space()='CSV']"));
		exportCsvButton.click();

		String fileName = "User_Login_Logout_Report_" + currentDate + ".csv";
		File downloadedFile = new File(Paths.get(downloadDir, fileName).toString());

		if (downloadedFile.exists()) {
			long fileSize = Files.size(Paths.get(downloadedFile.getPath()));
			if (fileSize > 0) {
				System.out.println("File downloaded successfully and size is greater than 0 bytes.");
				System.out.println("File csv available.");
			}

			else {
				System.out.println("File downloaded, but size is 0 bytes.");
			}

		}
		Thread.sleep(1000);
	

	
		WebElement exportxlsxButton = driver.findElement(By.xpath("//span[normalize-space()='Excel']"));
		exportxlsxButton.click();
		String fileName1 = "User_Login_Logout_Report_" + currentDate + ".xlsx";
		File downloadedFile1 = new File(Paths.get(downloadDir, fileName1).toString());

		if (downloadedFile1.exists()) {
			long fileSize = Files.size(Paths.get(downloadedFile1.getPath()));
			if (fileSize > 0) {
				System.out.println("File downloaded successfully and size is greater than 0 bytes.");
				System.out.println("File xlsx available.");
			}

			else {
				System.out.println("File downloaded, but size is 0 bytes.");
			}

		}
		Thread.sleep(1000);
	

	
		WebElement exporpdfButton = driver.findElement(By.xpath("//span[normalize-space()='PDF']"));
		exporpdfButton.click();

		String fileName2 = "User_Login_Logout_Report_" + currentDate + ".pdf";
		File downloadedFile2 = new File(Paths.get(downloadDir, fileName2).toString());

		if (downloadedFile2.exists()) {
			long fileSize = Files.size(Paths.get(downloadedFile2.getPath()));
			if (fileSize > 0) {
				System.out.println("File downloaded successfully and size is greater than 0 bytes.");
				System.out.println("File pdf available.");
			}

			else {
				System.out.println("File downloaded, but size is 0 bytes.");
			}

		}
		Thread.sleep(1000);
	


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

		driver.switchTo().window(parent);
	


		WebElement allpdfbutton = driver.findElement(By.xpath("//button[@class='dt-button loginReportPdfButton']"));
		allpdfbutton.click();

		String fileName3= "User_Login_Logout_Report_" + currentDate + ".pdf";
		File downloadedFile3 = new File(Paths.get(downloadDir, fileName3).toString());

		if (downloadedFile3.exists()) {
			long fileSize = Files.size(Paths.get(downloadedFile3.getPath()));
			if (fileSize > 0) {
				System.out.println("File downloaded successfully and size is greater than 0 bytes.");
				System.out.println("File allpdf available.");
			}

			else {
				System.out.println("File downloaded, but size is 0 bytes.");
			}

		}
		Thread.sleep(1000);
	



		WebElement allxlsx = driver.findElement(By.xpath("//span[normalize-space()='Export all to Excel']"));
		allxlsx.click();

		String fileName4 = "User_Login_Logout_Report_" + currentDate + ".xlsx";
		File downloadedFile4= new File(Paths.get(downloadDir, fileName4).toString());

		if (downloadedFile4.exists()) {
			long fileSize = Files.size(Paths.get(downloadedFile4.getPath()));
			if (fileSize > 0) {
				System.out.println("File downloaded successfully and size is greater than 0 bytes.");
				System.out.println("File allxlsx available.");
			}

			else {
				System.out.println("File downloaded, but size is 0 bytes.");
			}

		}
		Thread.sleep(1000);
	


		WebElement searchbox = driver.findElement(By.xpath("//input[@type='search']"));
		searchbox.sendKeys("test110");

		Thread.sleep(1000);
		WebElement searchtext=driver.findElement(By.xpath("//body[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[1]"));
		
		String searchkey=searchtext.getText();
		if (searchkey.equals("test110"))
		{
			System.out.println("search result is correct");
		}else
		{
			System.out.println("search result is not correct");
			
		}

		List<WebElement> rows1= driver.findElements(By.xpath("//tr[@role='row']"));
		System.out.println("Search result based on test110");

		// Print User ID and Login Date Time for each row
		for (WebElement row : rows1) {
//              String userId = row.findElement(By.xpath("td[1]")).getText();
//              String loginDateTime = row.findElement(By.xpath("td[2]")).getText();
//              String logoutDateTime = row.findElement(By.xpath("td[3]")).getText();

			String data = row.getText();
			System.out.println(data);
		}
	}

		
}


