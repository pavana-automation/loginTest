package test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import Pages.loginPage;

public class login {
	
	private static WebDriver driver = null;
	loginPage objLoginPage = new loginPage();
	
	@BeforeTest
	public void browser() {
	
	System.setProperty("webdriver.chrome.driver","C:\\Users\\jayanti.p.JAMOCHAHQ\\Downloads\\chromedriver-win64\\chromedriver.exe");
	driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.get("http://10.10.10.250:6688/prohance/"); 
	}
	 
	 
	@Test
	 public void test_Login_Successful() throws InterruptedException
	 {
		objLoginPage.clickLogin("pavana", "1", driver);
	       
	 }

}
