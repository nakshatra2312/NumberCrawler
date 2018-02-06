package com.automation.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TollFreeNumberWebsite {
	
	public boolean handleCaptcha(WebDriver dri) throws InterruptedException{
		boolean result = false;
		try {
			String bot = "";
			bot = dri.findElement(By.xpath(".//*[@class='formblue2']")).getText();
			if(!bot.contains("Search using numbers, letters, or asterisks (*)")){
				String[] botSplit = bot.split("plus");
				if(!botSplit.equals("")){
					String[] botSplit2 = botSplit[1].split("=");
					int ans = Integer.parseInt(botSplit[0].trim()) + Integer.parseInt(botSplit2[0].trim());
					WebElement textBox = dri.findElement(By.xpath(".//*[@class='formblue2']/input"));
					textBox.sendKeys(ans+"");
					dri.findElement(By.xpath(".//*[@id='B3']")).click();
					result = true;						
				}				
			}
			
		} catch (NoSuchElementException e) {
		    Thread.sleep(1000);
			System.out.println("Element Not Found");
		}
		return result;
	}
	
	public static void main(String[] args) throws InterruptedException {
		TollFreeNumberWebsite tl = new TollFreeNumberWebsite();
		String exePath = "C:\\Users\\mohit\\Downloads\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", exePath);
		WebDriver driver = new ChromeDriver();
		driver.get("http://search.tollfreenumbers.com/Lookups.asp?acode=800&pnumber=0000000");
		
		for(long i = 9000000L; i<9000010L;i++){
//			Thread.sleep(2000);
			tl.handleCaptcha(driver);
			WebElement tollFreeNumber = driver.findElement(By.xpath(".//*[@id='pnumber']"));
			tollFreeNumber.clear();
			tollFreeNumber.sendKeys(i+"");
			driver.findElement(By.xpath(".//*[@id='B1']")).click();
//			System.out.println("1");
			String searchResult = driver.findElement(By.xpath(".//*[@class='std']")).getText();
			System.out.println(searchResult);
//			System.out.println("2");
//			String[] searchArr = searchResult.split("\\s");
			
		}
		
		driver.quit();
			

    }
}
