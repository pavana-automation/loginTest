package test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.Assert;

import Pages.CUDPage;
import Pages.loginPage;


public class CalculateAverageWorkingHours {
	
	   // WebDriver driver;
		private static WebDriver driver = null;
	    loginPage objLoginPage = new loginPage();
	    CUDPage CUDPage = new CUDPage();
	    
		@BeforeTest
		public void browser() {
		
		System.setProperty("webdriver.chrome.driver","C:\\Users\\jayanti.p.JAMOCHAHQ\\Downloads\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://10.10.10.250:4848/prohance/"); 
		
		}
		
		@Test()
		 public void enableDiasbleChkInCud() throws InterruptedException
		 {
			 	objLoginPage.clickLogin("admink", "1", driver);
		        CUDPage.clickQuickLink(driver);
		        CUDPage.clickCUDLink(driver);
		        Thread.sleep(2000);
				CUDPage.clickCustomizeReportLink(driver);
				Thread.sleep(2000);
				CUDPage.setValueForCustReportViewFilter("userRole", driver);
				CUDPage.setValueForCustomerDurationView("M", driver);
				CUDPage.setValueForUserType("All Users", driver);
				CUDPage.selectdiplayOverALLChkBox(driver);
				
				Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class=\"table table-bordered\"]//input[@type=\"checkbox\"]	")));
				
				CUDPage.getTotalNumberOfRow(driver);
				CUDPage.selectLHrs(driver);
				CUDPage.selectAvgHrs(driver);
				CUDPage.selectUsersChk(driver);
				CUDPage.selectDaysChk(driver);
				//Thread.sleep(2000);
				//CUDPage.selectImageOption(driver);
				Thread.sleep(2000);
				CUDPage.clickUpdateFieldsBtn(driver);
				CUDPage.clickCustomBtn(driver);
				CUDPage.selectYear("2023", driver);
				CUDPage.selectMonth("August", driver);
				
				CUDPage.clickFetchBtn(driver);
				Thread.sleep(2000);
				CUDPage.calculateTotalHrsVsAvgHrsDays(driver);
				
		 }

}
