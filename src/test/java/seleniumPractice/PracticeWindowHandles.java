package seleniumPractice;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PracticeWindowHandles {
	WebDriver driver;
	
	@BeforeTest
	public void setUp()
	{
		WebDriverManager.chromedriver().setup();

	    driver = new ChromeDriver();
	    driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	

	@Test
	public void getWindowHandles() throws InterruptedException, IOException {
		

	
		driver.get("https://seleniumpractise.blogspot.com/2017/07/multiple-window-examples.html");
		String parent = driver.getWindowHandle();

		WebElement newLink = driver.findElement(By.xpath("//a[@href='//www.google.com']"));
		JavascriptExecutor js= (JavascriptExecutor)driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		wait.until(d -> js.executeScript("return document.readyState").equals("complete"));
		
		
		newLink.click();
		Set<String> allWindows = driver.getWindowHandles();
		for (String windows : allWindows) {
			// System.out.println(windows);
			if (!parent.equalsIgnoreCase(windows)) {
				System.out.println(windows);
				
			}
		}
		driver.switchTo().window(parent);
		
		
		//Taking ScreenShots With TimeStamp Code
		
		TakesScreenshot ts= (TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		FileUtils.copyFile(source, new File("/Users/satishchodisetti/eclipse-workspace/SelniumJava/target/Screeshots/"+timeStamp+".jpg"));
		
		
		System.out.println(driver.getTitle());
		Reporter.log("Test is Successful");
	}
	
//	@Test
//	public void facebookLogin() throws IOException
//	{
//		
//		
//		TakesScreenshot ts= (TakesScreenshot)driver;
//		File source=ts.getScreenshotAs(OutputType.FILE);
//		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
//		FileUtils.copyFile(source, new File("/Users/satishchodisetti/eclipse-workspace/SelniumJava/target/Screeshots/"+timeStamp+"/.jpg"));
//		
//	}

}
