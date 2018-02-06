package com.automation.framework;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class GoogleSearch {
	public static List<String> readFile(String filename){
		BufferedReader br = null;
		List<String> companyList = new ArrayList<String>();
		try {

			String sCurrentLine;
			br = new BufferedReader(new FileReader(filename));

			while ((sCurrentLine = br.readLine()) != null) {
				companyList.add(sCurrentLine);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null){
					br.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return companyList;
	}
	
	public static void openFile(){
		
	}
	
	public static void closeFile(){
		
	}
	
	public static void writeFile(String content){
		FileOutputStream fop = null;
		File file;
		
		try {

			file = new File("c:/newfile.txt");
			fop = new FileOutputStream(file);

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			// get the content in bytes
			byte[] contentInBytes = content.getBytes();

			fop.write(contentInBytes);
			fop.flush();
			fop.close();

			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fop != null) {
					fop.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		TollFreeNumberWebsite tl = new TollFreeNumberWebsite();
		String exePath = "C:\\Users\\mohit\\Downloads\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", exePath);
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.google.com/webhp?sourceid=chrome-instant&ion=1&espv=2&ie=UTF-8");
		List<String> companyList = new ArrayList<String>();
		companyList = readFile("C:\\My Drive\\Study Material\\CNS\\Project\\Fortune1000CompanyNames.txt");
		
		FileOutputStream fop = null;
		File file;
		
		try {
			file = new File("C:\\Users\\mohit\\Desktop\\newfile.txt");
			fop = new FileOutputStream(file);

			if (!file.exists()) {
				file.createNewFile();
			}
		
		for(String company : companyList){
			driver.get("https://www.google.com/webhp?sourceid=chrome-instant&ion=1&espv=2&ie=UTF-8");
			Thread.sleep(5000);
			WebElement tollFreeNumber = driver.findElement(By.xpath(".//*[@id='lst-ib']"));
			Actions build = new Actions(driver); 
			build.moveToElement(tollFreeNumber).build().perform(); // Here you perform hover mouse over the needed elemnt to triger the visibility of the hidden
//			tollFreeNumber.clear();
			tollFreeNumber.sendKeys(company + " toll free number");
			tollFreeNumber.submit();		
		
			Thread.sleep(5000);
			String content="";
			try{
				Actions build2 = new Actions(driver);
				WebElement number = driver.findElement(By.xpath(".//*[@class='_XWk']"));
				build2.moveToElement(number).build().perform();
				content = company +"\t"+number.getText()+"\n";
				byte[] contentInBytes = content.getBytes();				
				fop.write(contentInBytes);
				fop.flush();
//				System.out.println(company +"\t"+number.getText());				
			}catch(Exception e){
				content = company +"\tNotFound\n";
				byte[] contentInBytes = content.getBytes();				
				fop.write(contentInBytes);
				fop.flush();
//				System.out.println(company +"\tNotFound");	
			}	
		}
		
			fop.close();	
			System.out.println("Done");	
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fop != null) {
					fop.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		driver.quit();

	}
}
