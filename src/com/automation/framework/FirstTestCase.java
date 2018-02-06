package com.automation.framework;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstTestCase {
	 
	public static void main(String[] args) throws InterruptedException {
		
		String exePath = "C:\\Users\\mohit\\Downloads\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", exePath);
		WebDriver driver = new ChromeDriver();
		driver.get("http://search.tollfreenumbers.com/Lookups.asp?acode=8**&pnumber=");
//		System.out.println(driver.getPageSource());
//		Thread.sleep(3000);
		String bot = "";
		try {
			bot = driver.findElement(By.xpath(".//*[@class='formblue2']")).getText();
			String[] botSplit = bot.split("plus");
			String[] botSplit2 = botSplit[1].split("=");
			int ans = Integer.parseInt(botSplit[0].trim()) + Integer.parseInt(botSplit2[0].trim());
			System.out.println(ans);
			WebElement textBox = driver.findElement(By.xpath(".//*[@class='formblue2']/input"));
			textBox.sendKeys(ans+"");
			driver.findElement(By.xpath(".//*[@id='B3']")).click();
			
		} catch (NoSuchElementException e) {
		    Thread.sleep(1000);
			System.out.println("Element Not Found");
		}
		

		
		WebElement tollFreeNumber = driver.findElement(By.xpath(".//*[@id='pnumber']"));
		tollFreeNumber.sendKeys("3456789");
		driver.findElement(By.xpath(".//*[@id='B1']")).click();
		
		String searchResult = driver.findElement(By.xpath(".//*[@class='std']")).getText();
		System.out.println(searchResult);
		
        //Launch the Online Store Website
//		driver.get("http://www.store.demoqa.com");
		
 
        // Print a Log In message to the screen
        System.out.println("Successfully opened the website www.Store.Demoqa.com");
 
		//Wait for 5 Sec
		Thread.sleep(5);
		
        // Close the driver
  //      driver.quit();
    }
}