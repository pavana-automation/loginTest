package org.prohance.test.demoTest;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;


public class EnableDisableChk {
	
	@Test
	 public void login() throws InterruptedException
	    {
			System.setProperty("webdriver.chrome.driver","C:\\Users\\jayanti.p.JAMOCHAHQ\\Downloads\\chromedriver-win64\\chromedriver.exe");
			WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			//driver.manage().timeouts().implicitlyWait(10, null);
			
	/*		open URL	*/
			
			//driver.get("http://10.10.10.250:6688/prohance/");
			driver.get("http://10.10.10.250:4848/prohance/");
			
	/*		Login	*/
			driver.findElement(By.id("tlogin")).sendKeys("pavana_admin");
			driver.findElement(By.id("tpwdsaved")).sendKeys("1");
			driver.findElement(By.id("loginSubmitFrm")).click();
			Thread.sleep(2000);
			boolean isdisplayed = driver.findElement(By.xpath("//input[@class='btn-login']")).isDisplayed();
			if(isdisplayed) {
				driver.findElement(By.xpath("//input[@class='btn-login']")).click();
			}
			Thread.sleep(5000);
			//Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));
			//wait.until(ExpectedConditions.titleIs("quickLinkMenu"));
			
	/*		GoTo QuickLink	*/
			driver.findElement(By.id("quickLinkMenu")).click();
			//Thread.sleep(2000);
			
	/*		1) GoTo COD Page(Window switch)	
	*/
			driver.findElement(By.xpath("//li[@id='quickLink_tr_6']")).click();
			Set<String> handles=driver.getWindowHandles();
			Iterator it=handles.iterator();
			String parentwindow=(String) it.next();
			System.out.print("parent id"+parentwindow);
			String childwindow=(String) it.next();
			System.out.print("child id"+childwindow);
			driver.switchTo().window(childwindow);
			Thread.sleep(2000);
			//Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));
			//wait.equals(childwindow);
			
			
	/*		GoTo CustomizeReportPage	
	 * 		Fill the given data(CustomerReport,CustomerDuration,UserType)
	 * 
	 * */
			
			
			driver.findElement(By.id("show-content-menu-main")).click();
			
			Select reportBy = new Select(driver.findElement((By.id(("customReportViewByFilter")))));
			reportBy.selectByValue("UserGroupId");
			
			Select viewBy = new Select(driver.findElement(By.id("CustomDurationViewBy")));
			viewBy.selectByValue("M");
			
			Assert.assertTrue(driver.findElement(By.id("usersChk")).isEnabled() && driver.findElement(By.id("daysChk")).isEnabled());
			
			
			Select viewBy1 = new Select(driver.findElement(By.id("CustomDurationViewBy")));
			viewBy.selectByValue("D");
			
			Assert.assertTrue(driver.findElement(By.id("usersChk")).isEnabled() || driver.findElement(By.id("daysChk")).isEnabled());
			
			Select reportBy1 = new Select(driver.findElement((By.id(("customReportViewByFilter")))));
			reportBy.selectByValue("TeamMemberId");
			
			Assert.assertFalse(driver.findElement(By.id("usersChk")).isEnabled() && driver.findElement(By.id("daysChk")).isEnabled());
			
			Select viewBy2 = new Select(driver.findElement(By.id("CustomDurationViewBy")));
			viewBy.selectByValue("M");
			
			Assert.assertTrue(driver.findElement(By.id("usersChk")).isEnabled() || driver.findElement(By.id("daysChk")).isEnabled());
			
			
	    }

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

	}

}
