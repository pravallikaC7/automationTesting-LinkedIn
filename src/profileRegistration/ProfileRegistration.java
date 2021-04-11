package profileRegistration;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfileRegistration {
	
	static WebDriver driver;
	
	public void invokeBrowser() {
				System.setProperty("webdriver.chrome.driver", "D:\\Selenium IDE\\chromedriver_win32_v89\\chromedriver.exe");
				driver = new ChromeDriver();
				driver.manage().deleteAllCookies();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
				driver.get("https://www.linkedin.com/");
				try {
					registration();
				} catch (InterruptedException e) {
				}
		}
	 
	public void registration() throws InterruptedException {
		WebElement joinNow = new WebDriverWait(driver, 20)
				.until(ExpectedConditions.elementToBeClickable(By.linkText("Join now")));
		joinNow.click();
		Thread.sleep(10000);
		WebElement submit = new WebDriverWait(driver, 40)
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='join-form-submit']")));
		WebElement mailId = new WebDriverWait(driver, 40)
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='email-or-phone']")));
		mailId.click();
		mailId.sendKeys("selenium173@gmail.com");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("saibaba9");
		submit.click();
		driver.findElement(By.name("first-name")).sendKeys("SP");
		driver.findElement(By.name("last-name")).sendKeys("Ch");
		driver.findElement(By.xpath("//button[@id='join-form-submit']")).click();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		Thread.sleep(10000);
		driver.switchTo().frame(0);
		driver.findElement(By.xpath("//input[@name='phoneNumber']")).sendKeys("8639595020");
		driver.findElement(By.xpath("//button[@id='register-phone-submit-button']")).click();
		}
	

	public static void main(String[] args) {
		ProfileRegistration inv = new ProfileRegistration();
		inv.invokeBrowser();
	}

}
