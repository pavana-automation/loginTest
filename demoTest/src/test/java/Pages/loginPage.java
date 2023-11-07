package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



public class loginPage{
	
	By username = By.id("tlogin");
	
	By password = By.id("tpwdsaved");
	
	By login1 = By.id("loginSubmitFrm");
	
	  //Set user name in textbox

    public void  setUserName(String strUserName,WebDriver driver){

    	System.out.println(strUserName);
        driver.findElement(username).sendKeys(strUserName);
        //this.setUserName(strUserName);

    }
    
    //Set password in password textbox
    public void setPassword(String strPassword,WebDriver driver){

         driver.findElement(password).sendKeys(strPassword);
         //this.setPassword(strPassword);

    }
    
  //Click on login button
    public void clickLogin(String strUserName, String strPassword, WebDriver driver) throws InterruptedException{
    	
    	driver.findElement(username).sendKeys(strUserName);
    	driver.findElement(password).sendKeys(strPassword);
    	driver.findElement(login1).click();
    	Thread.sleep(2000);
		if(driver.findElement(By.xpath("//input[@class='btn-login']")).isDisplayed());
		{
			driver.findElement(By.xpath("//input[@class='btn-login']")).click();
		}
		Thread.sleep(2000);
    }
    
    


		
}
