package Steps;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import com.aventstack.extentreports.gherkin.model.Scenario;

import io.cucumber.java.AfterStep;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;import io.cucumber.java.After;

public class StepDefination extends BaseClass {

	public static WebDriver driver;
	public static String aim;
	@BeforeStep
	@Given("user is on login Page")
	public void user_is_on_login_page()  {
		
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Administrator\\Desktop\\chromedriver-win64\\chromedriver.exe");
		driver =new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	}

	@When("user Added allCredentials and click on login button")
	public void user_added_all_credentials_and_click_on_login_button() {
		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--main orangehrm-login-button']")).click();
	}

	@When("user is on homePage and capture the homepage title")
	public void user_is_on_home_page_and_capture_the_homepage_title() {
	 String title=driver.getTitle();
	 Assert.assertEquals(title, "OrangeHRM");
	}

	@When("user capture homepage url")
	public void user_capture_homepage_url() {
	   String Url=driver.getCurrentUrl();
	  boolean CU=Url.contains("orange");
	  Assert.assertEquals(CU,true);
	}

	@When("user click on pimlink")
	public void user_click_on_pimlink() {
	  driver.findElement(By.xpath("//span[text()='PIM']")).click();
	}

	@When("user click on add employee")
	public void user_click_on_add_employee() throws InterruptedException {
		driver.findElement(By.xpath("//a[text()='Add Employee']")).click();	   
		
		Thread.sleep(4000);
	}

	@When("user add {string},{string},{string} and click on save button")
	public void user_add_and_click_on_save_button(String fname, String mname, String lname) throws InterruptedException {
			driver.findElement(By.name("firstName")).sendKeys(fname);
			driver.findElement(By.name("middleName")).sendKeys(mname);
			driver.findElement(By.name("lastName")).sendKeys(lname);
			Thread.sleep(2000);
			driver.findElement(By.xpath("//button[text()=' Save ']")).click();

	  
	}

	@Then("user capture the employee Id")
	public void user_capture_the_employee_id() throws InterruptedException {
		Thread.sleep(2000);

	     aim=driver.findElement(By.xpath("//label[text()='Employee Id']/following::input[1]")).getAttribute("value");
	   
	    Thread.sleep(3000);
	}

	@Then("user clickon employee list and search the employee by using employee id")
	public void user_clickon_employee_list_and_search_the_employee_by_using_employee_id() throws InterruptedException {
	    driver.findElement(By.xpath("//a[text()='Employee List']")).click();
	    driver.findElement(By.xpath("//label[text()='Employee Id']/following::input[1]")).sendKeys(aim);
	    Thread.sleep(3000);
	}

	@Then("user select the employyee and clickon delete")
	public void user_select_the_employyee_and_clickon_delete() {
	WebElement wb= driver.findElement(By.xpath("//div[text()='Id']/following::input[1]"));
	   JavascriptExecutor js=(JavascriptExecutor) driver;
	   js.executeScript("arguments[0].click();",wb);
	   
	   driver.findElement(By.xpath("//button[text()=' Delete Selected ']")).click();
	}
	@Then("validate the conform delete window and click on yes delete")
	public void validate_the_conform_delete_window_and_click_on_yes_delete() throws InterruptedException {
	   String c=driver.findElement(By.xpath("//p[text()='The selected record will be permanently deleted. Are you sure you want to continue?']")).getText();
	   Assert.assertEquals(c.contains("delete"),true);
	   
	driver.findElement(By.xpath("//button[text()=' Yes, Delete ']")).click();
	Thread.sleep(3000);
	}
	
	@When("user click on profile image")
	public void user_click_on_profile_image() {
	   driver.findElement(By.xpath("//p[text()='Test63 Collings']")).click();
	}

	@Then("click on logout")
	public void click_on_logout() {
	   driver.findElement(By.xpath("//a[text()='Logout']")).click();
	}
	   @AfterStep
		public void tearDown(Scenario scenario) throws InterruptedException {
			Thread.sleep(4000);
			TakesScreenshot ts = (TakesScreenshot) driver;
			byte[] f1 = ts.getScreenshotAs(OutputType.BYTES);

//			System.out.println(scenario.getName());
//			System.out.println(scenario.getId());
//			scenario.attach(f, "image/png", scenario.getId());

	   }

	}


	
	

