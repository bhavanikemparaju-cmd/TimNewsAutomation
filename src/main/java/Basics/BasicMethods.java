package Basics;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.htmlunit.javascript.background.JavaScriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ByIdOrName;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BasicMethods {

	public static void main(String[] args) throws InterruptedException, IOException {

		WebDriverManager.chromedriver().setup();
		//WebDriver driver = new ChromeDriver();
	
		ChromeOptions option = new ChromeOptions();
		option.addArguments("window-size=1800,400");
		option.addArguments("headless");
		WebDriver driver = new ChromeDriver(option);
		//WebDriver driver = new HtmlUnitDriver();

		Actions actions = new Actions(driver);

		try {
			// Open the Website
			driver.get("https://timnews.com.br");
			// Thread.sleep(1000);
			// Maximize window
			driver.manage().window().maximize();
			// Thread.sleep(1000);
			// Wait for elements to load
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			// Thread.sleep(5000);
			// Locate the iframe containing the video play button
			WebElement iframe = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]")); 
			highlightElement(driver, iframe);
			// System.out.println("LOG: " + iframe.getTagName());
			// Thread.sleep(5000);
			// Click on the div to activate it

			actions.moveToElement(iframe).click().perform();
			// Thread.sleep(5000);
			// Locate the SVG Play Button inside the iframe (adjust selector if needed)

			WebElement svgPlayButton = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div/div[3]/button")); // Adjust if needed
			highlightElement(driver, iframe);
			// System.out.println("LOG: " + svgPlayButton.getTagName());
			// Thread.sleep(5000);
			// Click the SVG Play Button
			svgPlayButton.click();
			System.out.println("Clicked on the 'View a short ad' video play button.");
			// Thread.sleep(5000);
			// Switch back to main content (if needed)
			// driver.switchTo().defaultContent();

			Thread.sleep(6000);

			WebElement topView = driver.findElement(By.xpath("/html/body/div/div/div[1]/div[1]"));
			highlightElement(driver, topView);
			actions.moveToElement(topView).click().perform();
			String Title = driver.getTitle();
			if (Title.equalsIgnoreCase("TIM News")) {

				System.out.println("Correct Title is displayed");
			} else {
				System.out.println("Wrong Title is displayed");
			}

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// driver.findElement(By.xpath("//span[contains(@class,'m-auto')]")).click();
			// driver.findElement(By.xpath("//input[@type='email']")).sendKeys("bhavanik160795@gmail.com");
			driver.navigate().refresh();
			WebElement searchicon = driver.findElement(By.xpath("//input[@type='search']"));
			highlightElement(driver, searchicon);
			searchicon.click();
			searchicon.sendKeys("tim news");
			List<WebElement> listResult = driver.findElements(By.xpath("//div[contains(@class, 'mx-auto') and contains(@class, 'absolute') and contains(@class, 'top-9')] //div[contains(@class, 'ml-1') and contains(@class, 'text-medium') and contains(text(), 'tim')]"));
			//WebElement firstResult = driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div/header/div[3]/div[2]/div[2]/div/div[2]/div"));
			//highlightElement(driver, firstResult);
			for (int i =0; i<listResult.size(); i++)
			{
				if(listResult.get(i).getText().contains("tim")) {
					actions.moveToElement(listResult.get(i)).click().perform();	
				}
			}
			Thread.sleep(5000);
			//actions.moveToElement(firstResult).click().perform();
			driver.navigate().refresh();
			List<WebElement> linklist = driver.findElements(By.tagName("a"));
			System.out.println("The number of links present on this page is :" + linklist.size());
			
			for (int i = 0; i < linklist.size(); i++) {
				String linkText = linklist.get(i).getText();
				System.out.println("The Links present on this webpage is :" + linkText);
			}
			driver.navigate().refresh();
			List<WebElement> buttonList = driver.findElements(By.tagName("button"));
			System.out.println("The number of button present on this page is :" + buttonList.size());
			
			for (int i = 0; i < buttonList.size(); i++) {
				String buttonText = buttonList.get(i).getText();
				System.out.println("The button present on this webpage is :" + buttonText);
			}
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
			Thread.sleep(5000);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement scrollIntoView = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(), 'Sportbuzz')]")));
		

			//WebElement scrollIntoView = driver.findElement(By.xpath("//div[contains(text(), 'Sportbuzz')]"));
			scrollIntoView(scrollIntoView, driver);
			
			/*Set<String> handler = driver.getWindowHandles();
			Iterator<String> it = handler.iterator();
			String parentWindow = it.next();
			String childWindow = it.next();
			
			driver.switchTo().window(childWindow);
			driver.close();
			driver.switchTo().window(parentWindow);*/
            File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File("/Users/praveenkumargnanavel/eclipse-workspace/PRACTICEeeeeeee/target/Footer.png"));
			// WebElement closeButton=
			// driver.findElement(By.cssSelector("#searchQueryEventFeed >
			// section.desktop\\:grid.desktop\\:gap-2.desktop\\:grid-cols-10 >
			// div.mb-5.desktop\\:col-span-7 > div.shadow.px-2.py-6.desktop\\:hidden >
			// div.shadow-2xl.video-player-container.stickyads > button"));
			// actions.moveToElement(closeButton).click().perform();
			// JavascriptExecutor js = (JavascriptExecutor) driver;
			// WebElement element =
			// driver.findElement(By.xpath("/html/body/div/div/div/main/section/section[2]/div[1]/div[1]/div[2]/button"));
			// js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block:
			// 'center'});", element);
			// element.click();

       System.out.println("End of AutomationScript");
       




		}

		// driver.quit();

	}
	public static void highlightElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver; // Cast driver to JavaScriptExecutor
        
        // Store the original element style
        String originalStyle = element.getAttribute("style");
        
        // Change background color to yellow with red border (highlight effect)
        js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid black; background: red;');", element);
        
        // Pause for visual effect (optional)`
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Revert to original style
        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, originalStyle);
   
	}
	public static void scrollIntoView(WebElement element,WebDriver driver)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}
}
