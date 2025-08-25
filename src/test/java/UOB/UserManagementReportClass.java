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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserManagementReportClass {

	public static void User_Management_Report(String input, WebDriver driver,String downloadfilename,String args1,String args2,String args3,String args4,String args5,String args6,String args7,String args8,String args9) throws InterruptedException, IOException
	{
		//this.driver=driver;
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String currentDate = sdf.format(new Date());
		String downloadDir = "C:\\Users\\lohitkumar.gatta\\Downloads";

			
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		//driver.findElement(By.xpath("//a[normalize-space()='Audit Reports']")).click();
		//driver.findElement(By.xpath("//a[normalize-space()='Audit Reports']")).click();
		WebElement reportTypeDropdown = driver.findElement(By.id("cboReports"));
		
		
		Select selectReportType = new Select(reportTypeDropdown);
		
		selectReportType.selectByVisibleText(input);

		// Enter Date From and Date To
		WebElement dateFromField = driver.findElement(By.xpath("//input[@name='DateFrom']"));
		dateFromField.clear();
		dateFromField.sendKeys("10-09-2024");
						
		Thread.sleep(3000);
		WebElement dateToField = driver.findElement(By.xpath("//input[@name='DateTo']"));
		dateToField.click();
		dateToField.clear();
		
		dateToField.sendKeys("15-09-2024");
		

		

		// Click the "Get Report" button
		WebElement getReportButton = driver.findElement(By.id("GetReport"));
		getReportButton.click();

		// Wait for the report to be generated
		Thread.sleep(2000);
		
		
		
		WebElement table = driver.findElement(By.xpath("//table[@id='Report']"));
	    List<WebElement> headers = table.findElements(By.xpath(".//thead/tr/th"));
	    
       String[] expectedHeaders = {
    		   args1,args2,args3,args4,args5,args6,args7,args8,args9};
       System.out.println("Headers validation for "+input);
        // Check if each expected header is present
        for (String expectedHeader : expectedHeaders) 
        {
            boolean found = false;
            for (WebElement header : headers)
            {
                if (header.getText().equalsIgnoreCase(expectedHeader)) {
                    found = true;
                    break;
                }
            }         
            
            if (found) 
            {
            	
            	if (expectedHeader != null && !expectedHeader.isEmpty()) 
            	 {
            		 
            		 System.out.println("Header '" + expectedHeader + "' is present.");           		    		 
            		 
                   } 	    	
              
             else {
                System.out.println("Header '" + expectedHeader + "' is NOT present.");
            }
        }
        }
		// Extract data from the generated report table
		List<WebElement> rows = driver.findElements(By.xpath("//table[@id='Report']/tbody/tr"));

		// Print User ID and Login Date Time for each row
		for (WebElement row : rows) {
//              String userId = row.findElement(By.xpath("td[1]")).getText();
//             String loginDateTime = row.findElement(By.xpath("td[2]")).getText();
//             String logoutDateTime = row.findElement(By.xpath("td[3]")).getText();

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
	

		//csv button
		WebElement exportCsvButton = driver.findElement(By.xpath("//span[normalize-space()='CSV']"));
		exportCsvButton.click();
		Thread.sleep(3000);

		String fileName = downloadfilename + currentDate + ".csv";
		File downloadedFile = new File(Paths.get(downloadDir, fileName).toString());

		if (downloadedFile.exists()) 
		{
			System.out.println("File csv available.");
			long fileSize = Files.size(Paths.get(downloadedFile.getPath()));
			if (fileSize > 0) 
			{
				System.out.println("File csv downloaded successfully and size is greater than 0 bytes.");
				
			}
				
				else{
					System.out.println("File downloaded, but size is 0 bytes.");
				}}
		else
				{
				System.out.println("File csv not found.");
			
		}

		
		Thread.sleep(2000);
	

	
		WebElement exportxlsxButton = driver.findElement(By.xpath("//span[normalize-space()='Excel']"));
		exportxlsxButton.click();
		Thread.sleep(2000);
		String fileName1 = downloadfilename + currentDate + ".xlsx";
		File downloadedFile1 = new File(Paths.get(downloadDir, fileName1).toString());

		if (downloadedFile1.exists()) 
		{
			System.out.println("File xlsx available.");
			long fileSize1 = Files.size(Paths.get(downloadedFile1.getPath()));
			if (fileSize1 > 0) 
			{
				System.out.println("File xlsx downloaded successfully and size is greater than 0 bytes.");
				
			}
				
				else{
					System.out.println("File downloaded, but size is 0 bytes.");
				}}
		else
					
				{
				System.out.println("File xlsx not found.");
			
		}

		
		
	

	
		WebElement exporpdfButton = driver.findElement(By.xpath("//span[normalize-space()='PDF']"));
		exporpdfButton.click();
		Thread.sleep(2000);

		String fileName2 = downloadfilename + currentDate + ".pdf";
		File downloadedFile2 = new File(Paths.get(downloadDir, fileName2).toString());

		if (downloadedFile2.exists()) 
		{
			System.out.println("File pdf available.");
			long fileSize2 = Files.size(Paths.get(downloadedFile2.getPath()));
			if (fileSize2 > 0) 
			{
				System.out.println("File pdf downloaded successfully and size is greater than 0 bytes.");
				
			}
				
				else{
					System.out.println("File downloaded, but size is 0 bytes.");
				}}
		else
					
				{
				System.out.println("File pdf not found.");
			
		}
		
	


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
	


		WebElement allpdfbutton = driver.findElement(By.xpath("//span[normalize-space()='Export all to PDF']"));
		allpdfbutton.click();
		Thread.sleep(2000);

		String fileName3= downloadfilename+ currentDate + ".pdf";
		File downloadedFile3 = new File(Paths.get(downloadDir, fileName3).toString());

		if (downloadedFile3.exists()) 
		{
			System.out.println("File allpdf available.");
			long fileSize3 = Files.size(Paths.get(downloadedFile3.getPath()));
			if (fileSize3 > 0) 
			{
				System.out.println("File allpdf downloaded successfully and size is greater than 0 bytes.");
				
			}
				
				else{
					System.out.println("File downloaded, but size is 0 bytes.");
				}}
		else
					
				{
				System.out.println("File allpdf not found.");
			
		}
		
	



		WebElement allxlsx = driver.findElement(By.xpath("//span[normalize-space()='Export all to Excel']"));
		allxlsx.click();
		Thread.sleep(2000);

		String fileName4 = downloadfilename+ currentDate + ".xlsx";
		File downloadedFile4= new File(Paths.get(downloadDir, fileName4).toString());

		if (downloadedFile4.exists()) 
		{
			System.out.println("File allexcel available.");
			long fileSize4 = Files.size(Paths.get(downloadedFile4.getPath()));
			if (fileSize4 > 0) 
			{
				System.out.println("File allexcel downloaded successfully and size is greater than 0 bytes.");
				
			}
				
				else{
					System.out.println("File downloaded, but size is 0 bytes.");
				}}
		else
					
				{
				System.out.println("File allexcel not found.");
			
		}
	
	


		WebElement searchbox = driver.findElement(By.xpath("//input[@type='search']"));
		searchbox.sendKeys("test110");

		Thread.sleep(1000);
		/*WebElement searchtext=driver.findElement(By.xpath("//body[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[1]"));
		
		String searchkey=searchtext.getText();
		if (searchkey.equals("test110"))
		{
			System.out.println("search result is correct");
		}else
		{
			System.out.println("search result is not correct");
			
		}*/

		List<WebElement> rows1= driver.findElements(By.xpath("//table[@id='Report']/tbody/tr"));
		System.out.println("Search result based on test110");

		// Print User ID and Login Date Time for each row
		for (WebElement row : rows1)
		{
			List<WebElement> cells = row.findElements(By.tagName("td"));
			for (WebElement cell : cells)
			{
                String cellText = cell.getText();
                System.out.println(cellText);


			
			Thread.sleep(3000);
		}}
        }
        
	}
