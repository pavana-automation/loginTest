package Pages;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import test.login;


public class CUDPage {
	
	By quickLink = By.id("quickLinkMenu");
	
	By CUDLink = By.xpath("//div[contains(text(),'Comprehensive Work Time Analytics')]");
	
	By CustomizeReportLink = By.id("show-content-menu-main");
	
	//@FindBy(id = "customReportViewByFilter")
	By customReportViewByFilter = By.id("customReportViewByFilter");
	//private WebElement customReportViewByFilter ;

	
	By CustomerDurationView = By.id("CustomDurationViewBy");
	
	By UsersChk = By.id("usersChk");
	
	By DaysChk = By.id("daysChk");
	
	By UserType = By.id("userType");
	
	By diplayOverALLChkBox = By.id("displayOverallData");
	
	By table = By.xpath("//table[@class=\"table table-bordered\"]//input[@type=\"checkbox\"]");
	
	By lHrs = By.id("LHrs");
	
	By avgHrs = By.id("AVG_LHrs");
	
	By imageOptionSelection = By.cssSelector("#current > img");
	
	By UpdateFieldsBtn = By.xpath("//*[contains(text(),'UPDATE FIELDS')]");
	
	By CustomBtn = By.xpath("//td[text()='Custom']");
	
	By selectYear =By.id("selectedMonthYear");
	
	By selectMonth = By.id("selectedMonth");
	
	By fetchBtn = By.xpath("//*[contains(text(),'FETCH')]");
	
	By tableCount = By.xpath("//*[@id='efficiencyList']/tbody/tr");
	
	
	
	public void clickQuickLink(WebDriver driver) {
		driver.findElement(quickLink).click();
	}
	
	public void clickCUDLink(WebDriver driver) throws InterruptedException {
		driver.findElement(CUDLink).click();
        //Frame switch
        Set<String> handles=driver.getWindowHandles();
		Iterator it=handles.iterator();
		String parentwindow=(String) it.next();
		String childwindow=(String) it.next();
		driver.switchTo().window(childwindow);
		Thread.sleep(2000);
	}
	
	public List<WebElement> getTotalNumberOfRow(WebDriver driver) {
		List<WebElement> totalRowCount = driver.findElements(table);
		for(int i =1; i<totalRowCount.size();i++)
		{
			if(totalRowCount.get(i).isSelected())
			{
				totalRowCount.get(i).click();
			
			}
		}
		return totalRowCount;
	}
	
	public void clickCustomizeReportLink(WebDriver driver) {
		driver.findElement(CustomizeReportLink).click();
	}
	
	public void selectdiplayOverALLChkBox(WebDriver driver) {
		driver.findElement(diplayOverALLChkBox).click();
	}
	
	public void setValueForCustReportViewFilter(String valueforCustReportViewFilter, WebDriver driver)
	{
		Select value= new Select(driver.findElement(customReportViewByFilter));
		value.selectByValue(valueforCustReportViewFilter);
		
		
	}
	
	public void setValueForUserType(String usertype, WebDriver driver) {
		Select value= new Select(driver.findElement(UserType));
		value.selectByVisibleText(usertype);
	}
	
	public void setValueForCustomerDurationView(String valueForCustomerDurationView, WebDriver driver)
	{
		Select value1= new Select(driver.findElement(CustomerDurationView));
		value1.selectByValue(valueForCustomerDurationView);
	}
	
	public void setValueForUsersChk(String valueForUsersChk, WebDriver driver)
	{
		Select value2= new Select(driver.findElement(UsersChk));
		value2.selectByValue(valueForUsersChk);
	}
	
	public boolean usersChkIsEnabled(WebDriver driver) {
		boolean result = driver.findElement(UsersChk).isEnabled();
		return result;
	}
	
	public boolean daysChkIsEnabled(WebDriver driver) {
		boolean daysResult = driver.findElement(DaysChk).isEnabled();
		return daysResult;
	}
	
	public void selectLHrs(WebDriver driver) {
		driver.findElement(lHrs).click();
	}
	public void selectAvgHrs(WebDriver driver) {
		driver.findElement(avgHrs).click();
	}
	public void selectUsersChk(WebDriver driver) {
		driver.findElement(UsersChk).click();
	}
	public void selectDaysChk(WebDriver driver) {
		driver.findElement(DaysChk).click();
	}
	
	public void selectImageOption(WebDriver driver)
	{
		boolean isSelected = driver.findElement(By.xpath("//*[@id=\"all_select\"]")).isSelected();
		if(isSelected == false)
		{
			driver.findElement(By.xpath("//*[@id=\"all_select\"]")).click();
			
		}
		driver.findElement(By.id("workDayRatioValue")).clear();
		driver.findElement(By.id("workDayRatioValue")).sendKeys("0.5");
		driver.findElement(By.xpath("//*[@id=\"daysDialogDiv\"]/div/div/div[1]/div/div/div[2]/button")).click();
	}
	
	public void clickUpdateFieldsBtn(WebDriver driver) {
		driver.findElement(UpdateFieldsBtn).click();
	}
	
	public void clickCustomBtn(WebDriver driver) {
		driver.findElement(CustomBtn).click();
	}
	
	public void selectYear(String year , WebDriver driver) {
		Select yearBy = new Select(driver.findElement(By.id("selectedMonthYear")));
		yearBy.selectByValue(year);
	}
	
	public void selectMonth(String month , WebDriver driver) {
		Select MonthBy = new Select(driver.findElement(By.id("selectedMonth")));
		MonthBy.selectByVisibleText(month);
	}
	
	public void clickFetchBtn(WebDriver driver) {
		driver.findElement(fetchBtn).click();
	}
	
	public int getTableCount(WebDriver driver) {
		int rowcount = driver.findElements(By.xpath("//*[@id='efficiencyList']/tbody/tr")).size();
		return rowcount;
	}
	
	public void calculateTotalHrsVsAvgHrsDays(WebDriver driver) {
	
		int rowcount = driver.findElements(By.xpath("//*[@id='efficiencyList']/tbody/tr")).size();
		for(int i=1;i<=rowcount;i++) {
				String totHrs = driver.findElement(By.xpath("//*[@id='efficiencyList']/tbody/tr["+i+"]/td[3]")).getText();
				String  days = driver.findElement(By.xpath("//*[@id='efficiencyList']/tbody/tr["+i+"]/td[6]")).getText();
				String averageHrs = driver.findElement(By.xpath("//*[@id='efficiencyList']/tbody/tr["+i+"]/td[4]")).getText();
				if(totHrs.length()!=0 && days.length()!=0 && averageHrs.length()!=0)
				{
					float totalHrs = Float.parseFloat(totHrs.substring(0, totHrs.length()-1));
					float totalDys = Float.parseFloat(days);
					float expectedValue = totalHrs/totalDys;
					String formattedExpectedValue = String.format("%.2f", expectedValue);
					float formattedExpectedValue1 = Float.parseFloat(formattedExpectedValue);
					float actualValue = Float.parseFloat(averageHrs.substring(0, averageHrs.length()-1));
					boolean condition1 = (formattedExpectedValue1==actualValue);
					boolean condition2 = ((formattedExpectedValue1+0.5)==actualValue);
					boolean condition3 = ((formattedExpectedValue1-0.5)==actualValue);
					Assert.assertTrue((condition1 || condition2 || condition3));
					
				}
				
		}
		
		
	}
	

	
	
}
