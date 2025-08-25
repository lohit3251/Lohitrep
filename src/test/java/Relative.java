
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.Dimension;
public class Relative {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-infobars");
		options.addArguments("--disable-notifications");
		WebDriver driver = new ChromeDriver(options);
		
		driver.manage().window().maximize();
		driver.get("https://www.spicejet.com/");
		
	
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,500);");
		Thread.sleep(2000);
		
		
		WebElement logo=driver.findElement(By.xpath("//img[@alt='spin']"));
		
		File src1=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File tar1=new File("./Screenshot12.png");
		FileUtils.copyFile(src1, tar1);
		
		File src=logo.getScreenshotAs(OutputType.FILE);
		File target=new File("./Screenshots//screenshot.png");
		
		FileUtils.copyFile(src, target);
		Dimension logoUX = logo.getSize();
		
		
		int elementHeight = logoUX.getHeight();
		int elementWidth = logoUX.getWidth();
		String backgroundColor = logo.getCssValue("background-color");
        String textColor = logo.getCssValue("color");
        String fontSize = logo.getCssValue("font-size");
        String fontWeight = logo.getCssValue("font-weight");
        
        int elementX = logo.getLocation().getX();
        int elementY = logo.getLocation().getY();
        
        System.out.println(elementHeight);
        System.out.println(elementWidth);
        System.out.println(backgroundColor);
        System.out.println(textColor);
        System.out.println(fontSize);
        System.out.println(fontWeight);
        System.out.println(elementX);
        System.out.println(elementY);
		
		
		
		
	
		
		

	}

}
