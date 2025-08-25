package UOB;



	
	
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import java.util.List;

	public class SearchUsernameInTable {
	    public static void main(String[] args) throws InterruptedException {
	        // Set up WebDriver and open the browser
	        System.setProperty("webdriver.chrome.driver", "path_to_chromedriver");
	        WebDriver driver = new ChromeDriver();

	        // Open the webpage containing the table
	        driver.get("https://example.com/your-table-page");

	        // Specify the username to search for
	        String usernameToSearch = "desired_username";
	        boolean usernameFound = false;

	        // Loop through pages until the username is found or no more pages are left
	        while (true) {
	            // Find all rows in the table
	            List<WebElement> rows = driver.findElements(By.xpath("//table[@id='your-table-id']//tbody/tr"));

	            // Iterate over each row to find the username
	            for (WebElement row : rows) {
	                // Get the username column (assuming it's the second column)
	                WebElement usernameColumn = row.findElement(By.xpath(".//td[2]"));
	                String username = usernameColumn.getText();

	                // Check if this is the username we are searching for
	                if (username.equals(usernameToSearch)) {
	                    // Username found, now click on the "Edit" button (assuming it's in the last column)
	                    WebElement editButton = row.findElement(By.xpath(".//td[last()]//button[contains(text(), 'Edit')]"));
	                    editButton.click();
	                    usernameFound = true;
	                    break; // Exit the loop once the username is found
	                }
	            }

	            // If username is found, break out of the pagination loop
	            if (usernameFound) {
	                System.out.println("Username found and Edit button clicked.");
	                break;
	            }

	            // Check if the "Next" button exists and is enabled (to navigate to the next page)
	            try {
	                WebElement nextButton = driver.findElement(By.xpath("//a[contains(text(),'Next') and not(contains(@class, 'disabled'))]"));
	                nextButton.click();  // Click "Next" to go to the next page
	                Thread.sleep(2000);  // Wait for the next page to load
	            } catch (Exception e) {
	                // If "Next" button is not found or is disabled, stop pagination
	                System.out.println("No more pages to search. Username not found.");
	                break;
	            }
	        }

	        // Close the browser
	        driver.quit();
	    }
	}

