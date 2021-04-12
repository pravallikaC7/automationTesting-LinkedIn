package profileCompletion;

import org.testng.annotations.*;
import org.testng.AssertJUnit;
import java.util.concurrent.TimeUnit;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// AddProfileSection_Skills class for adding a skill under Skills& endorsements of Me>Profile
public class AddProfileSection_Skills {
	static WebDriver driver;
	
	@BeforeSuite
	public void invokeBrowser()  {		
		System.setProperty("webdriver.chrome.driver", "D:\\Selenium IDE\\chromedriver_win32_v89\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://www.linkedin.com/");
		//login();
		//addSkill();
		//verifySkill();
	}
	
	@BeforeTest
	public void login() {
		//find web element 'EmailId' TextBox element and give input
		driver.findElement(By.id("session_key")).sendKeys("selenium173@gmail.com");    
		//find  web element 'Password' TextBox element and give input
		driver.findElement(By.cssSelector("#session_password")).sendKeys("saibaba9");	
		//find, Wait until the Web element submit button is enabled
		WebElement submit = new WebDriverWait(driver, 20)
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='sign-in-form__submit-button']")));
		//Click on submit button of Login form 
		try {
			submit.click();
		} catch (Exception e) {
		}
	}
	
	/*
	 * Firstly, to add a skill in Me >> Profile
	 */
	@Test (priority=1)
	public void addSkill() {
		//wait until the Home page is loaded completely
		try {
			Thread.sleep(5000);		
		} catch (Exception e) {
		}
		//Find web element 'Me'
		WebElement me = new WebDriverWait(driver, 40)
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@class='global-nav__me-photo ghost-person ember-view']")));
		//Click on web element  'ME'
		me.click();
		//Find web element 'View profile' that appears after clicking on 'Me' in Home page
		WebElement viewProfile = new WebDriverWait(driver, 40)
				.until(ExpectedConditions.elementToBeClickable(By.linkText("View Profile")));
		//click on  web element 'View profile'
		viewProfile.click();
		//Scroll down to find web element Skills which appears after clicking on web element 'Add profile section'
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,300);");
		//Find web element 'Add profile section' and click on it
		driver.findElement(By.xpath("//li[2]/section/button")).click();
		//find web element 'Skills' and click on it
		driver.findElement(By.xpath("//li[@id='skills-drawer']/button")).click();
		//Find web element link 'Skills' which appears after clicking on 'Skills' and click on it
		driver.findElement(By.linkText("Skills")).click();
		//Find web element text box 'Skill' in Add Skill popup
		WebElement enterSkill = driver.findElement(By.xpath("//div[@id='artdeco-modal-outlet']/div//div[@class='artdeco-modal__content ember-view']/div/div/div[1]//input[@role='combobox']"));
	    try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
	    /*
	     * Enter skill in Skill text box and click on submit button in Add Skill popup
	     */
		enterSkill.click();
		enterSkill.sendKeys(" Dev");    
		enterSkill.sendKeys(Keys.TAB);
	    enterSkill.sendKeys(Keys.ENTER);
		driver.findElement(By.xpath("//div[@class='artdeco-modal__actionbar ember-view']/button[1]")).click();
	}	
	
	/* 
	 * To verify whether the added skill is being displayed under Skills section of My profile
	 */
	@Test (priority=1)
	public void verifySkill() {
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
		String actualTitle = driver.findElement(By.xpath
				("//p[@class='pv-skill-category-entity__name tooltip-container']/span[1]")).getText();
		String expectedTitle = "Dev";
	    AssertJUnit.assertEquals(expectedTitle, actualTitle);
	    System.out.println("Verified if the added skill is being displayed or not");
	}
	
	/*
	 * To take a screenshot of Skills section of Me >> Profile
	 */
	@AfterTest
	public void addedSkill()
	{
		TakesScreenshot scrShot = ((TakesScreenshot)driver);
		File takenSS = scrShot.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(takenSS, new File("./Screenshots/addedSkill.png"));
		} catch (IOException e) {
		}
	}
/*	public static void main(String[] args) {
		AddProfileSection_Skills skills = new AddProfileSection_Skills();
		skills.invokeBrowser();
	}*/

}
