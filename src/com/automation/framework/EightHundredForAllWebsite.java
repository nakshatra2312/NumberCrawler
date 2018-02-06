package com.automation.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class EightHundredForAllWebsite {
	public static void main(String[] args) throws InterruptedException {
		String exePath = "C:\\Users\\mohit\\Downloads\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", exePath);
		WebDriver driver = new ChromeDriver();
		driver.get("http://legacy.800forall.com/index.php");
		
		for(long number=8009000000L;number<8009000010L;number++){
			driver.navigate().refresh();
			WebElement tollFreeNumber = driver.findElement(By.xpath(".//input[@name='telnumber']"));
			tollFreeNumber.clear();
			tollFreeNumber.sendKeys(number+"");
			driver.findElement(By.xpath(".//input[@name='submit']")).click();
			Thread.sleep(1000);
			String result="";
			while(driver.findElement(By.xpath(".//*[@id='TrnResults']")).getText().equals("This process may take several minutes. Please wait ...")){
				Thread.sleep(2000);
			}		
			result = driver.findElement(By.xpath(".//*[@id='TrnResults']")).getText();
			System.out.println(result);
		}
		
		driver.quit();
	}
}
