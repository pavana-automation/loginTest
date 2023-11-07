package org.prohance.test.demoTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import utilities.ReadXLSdata;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class App 
{
	
	
	//@Test(dataProviderClass=ReadXLSdata.class,dataProvider="bvtdata")
	@Test
    //public void login(String username,String password) throws InterruptedException
	public void login() throws InterruptedException
    {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\jayanti.p.JAMOCHAHQ\\Downloads\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(10, null);
		
/*		open URL	*/
		
		driver.get("http://10.10.10.250:6688/prohance/");
		//driver.get("http://10.10.10.250:4848/prohance/");
		

		driver.findElement(By.id("tlogin")).sendKeys("pavana");
		driver.findElement(By.id("tpwdsaved")).sendKeys("1");
		driver.findElement(By.id("loginSubmitFrm")).click();
		Thread.sleep(2000);
		if(driver.findElement(By.xpath("//input[@class='btn-login']")).isDisplayed());
		{
			driver.findElement(By.xpath("//input[@class='btn-login']")).click();
		}
		Thread.sleep(5000);
		//Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		//wait.until(ExpectedConditions.titleIs("quickLinkMenu"));
		
/*		GoTo QuickLink	*/
		driver.findElement(By.id("quickLinkMenu")).click();
		Thread.sleep(2000);
		
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
		reportBy.selectByValue("userRole");
		
		Select viewBy = new Select(driver.findElement(By.id("CustomDurationViewBy")));
		viewBy.selectByValue("M");
		
		Select userBy = new Select(driver.findElement(By.id("userType")));
		userBy.selectByVisibleText("All Users");
		
		driver.findElement(By.id("displayOverallData")).click();
		
		Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class=\"table table-bordered\"]//input[@type=\"checkbox\"]	")));
		
/*		a)Get the total number of rows 
 * 		b)selecting few details such as (LoggedHrs,AvgHrs,Days,Users)
 */
		
		//List<ArrayList> count =  (List<ArrayList>) driver.findElement(By.xpath("//table[@class=\"table table-bordered\"]//input[@type=\"checkbox\"]")).getSize();
		List<WebElement> a = driver.findElements(By.xpath("//table[@class=\"table table-bordered\"]//input[@type=\"checkbox\"]"));
		for(int i =1; i<a.size();i++)
		{
			if(a.get(i).isSelected())
			{
				a.get(i).click();
			
			}
		}
		
		driver.findElement(By.id("LHrs")).click();
		driver.findElement(By.id("AVG_LHrs")).click();
		driver.findElement(By.id("usersChk")).click();
		driver.findElement(By.id("daysChk")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#current > img")).click();
		Thread.sleep(2000);
		boolean isSelected = driver.findElement(By.xpath("//*[@id=\"all_select\"]")).isSelected();
		if(isSelected == false)
		{
			driver.findElement(By.xpath("//*[@id=\"all_select\"]")).click();
			
		}
		driver.findElement(By.id("workDayRatioValue")).clear();
		driver.findElement(By.id("workDayRatioValue")).sendKeys("0.5");
		driver.findElement(By.xpath("//*[@id=\"daysDialogDiv\"]/div/div/div[1]/div/div/div[2]/button")).click();
		driver.findElement(By.xpath("//*[contains(text(),'UPDATE FIELDS')]")).click();
		driver.findElement(By.xpath("//td[text()='Custom']")).click();
		
		Thread.sleep(5000);
		Select yearBy = new Select(driver.findElement(By.id("selectedMonthYear")));
		yearBy.selectByValue("2023");
		Select monthBy = new Select(driver.findElement(By.id("selectedMonth")));
		monthBy.selectByVisibleText("June");
		
		driver.findElement(By.xpath("//*[contains(text(),'FETCH')]")).click();
		
		Thread.sleep(5000);
		
		int rowcount = driver.findElements(By.xpath("//*[@id='efficiencyList']/tbody/tr")).size();

		System.out.print("row count is"+rowcount);
		
/*		a)Expected result = TotalwrkHrs/TotalDays
 * 		b)ExpectedResult = ActualResult 
 */

		
		for(int i=1;i<=rowcount;i++) {
			try {
				String totHrs = driver.findElement(By.xpath("//*[@id='efficiencyList']/tbody/tr["+i+"]/td[3]")).getText();
				String  days = driver.findElement(By.xpath("//*[@id='efficiencyList']/tbody/tr["+i+"]/td[6]")).getText();
				String averageHrs = driver.findElement(By.xpath("//*[@id='efficiencyList']/tbody/tr["+i+"]/td[4]")).getText();
				if(totHrs.length()!=0 && days.length()!=0 && averageHrs.length()!=0)
				{
					float totalHrs = Float.parseFloat(totHrs.substring(0, totHrs.length()-1));
					//System.out.println("the actual value is:" +totalHrs);
					float totalDys = Float.parseFloat(days);
					//System.out.println("the actual value is:" +totalDys);
					float expectedValue = totalHrs/totalDys;
					String formattedExpectedValue = String.format("%.2f", expectedValue);
					float formattedExpectedValue1 = Float.parseFloat(formattedExpectedValue);
					//System.out.println("the expected value is:" +expectedValue);
					System.out.println("the expected value is:" +formattedExpectedValue1);
					float actualValue = Float.parseFloat(averageHrs.substring(0, averageHrs.length()-1));
					System.out.println("the actual value is:" +actualValue);
					boolean condition1 = (formattedExpectedValue1==actualValue);
					boolean condition2 = ((formattedExpectedValue1+0.5)==actualValue);
					boolean condition3 = ((formattedExpectedValue1-0.5)==actualValue);
					//Assert.assertEquals((expectedValue, actualValue) || (expectedValue1,actualValue) || (expectedValue2,actualValue));
					//System.out.println(+expectedValue+" same "+actualValue);
					Assert.assertTrue((condition1 || condition2 || condition3));

				}
				
			}
			catch(NumberFormatException nfe)
			{
				System.out.println("Number format Exception:" +nfe.getMessage());
			}
		
			
		}
		
		

	
    }
	
	/*
	 * private Object actualValue(double d, float actualValue) { // TODO
	 * Auto-generated method stub return null; }
	 */
	
	
	public static void main( String[] args )
    {
    	
		System.out.print("hello");
    }
}
