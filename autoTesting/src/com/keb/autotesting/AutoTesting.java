package com.keb.autotesting;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AutoTesting {
    /**
     * auto testing tool using selenium library 
    */
    private WebDriver driver;
    private WebElement webElement;
    
    public static void main(String[] args) throws InterruptedException {
        AutoTesting main = new AutoTesting();
        
        main.driver.get(mainUrl);
        Thread.sleep(2000);        
                  
        main.insertData();
        main.updateData();
        main.deleteData();
        
        //driver close
        main.driver.close();
    }
    
    
    
    public AutoTesting() {
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
        driver = new ChromeDriver();
        
    }
    //driver id and path
    public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
    public static final String WEB_DRIVER_PATH = "C:\\Users\\inna\\Desktop\\importFile\\chromedriver.exe";
    
    //index page url
    public static final String mainUrl = "http://192.168.23.34:8080/DB03/main.jsp";
    //number of data to insert
    public final static int last = 10;
    
    /**
     * method : insertData
     * @param none
    */    
    public void insertData() {    
        try {
            int count = 0;
            while (true) {
                //click button to write
                driver.findElement(By.xpath("/html/body/div/button")).click();
                
                //get id of input box where input title
                webElement = driver.findElement(By.id("input_title"));
                String title = String.format("test %d", count);
                webElement.sendKeys(title);
                Thread.sleep(2000);
                
                //get id of input box where input article
                webElement = driver.findElement(By.id("article"));
                String article = String.format("This is test text %d", count);
                webElement.sendKeys(article);
                Thread.sleep(2000);
                
                //click the submit button
                driver.findElement(By.id("submit")).click();
                Thread.sleep(3000);
                
                //back to the main list
                driver.findElement(By.id("back")).click();
                Thread.sleep(2000);
                
                count++;
                if (count == last) break;
            }
        } catch (Exception e) {
            String err = e.getMessage();
            System.out.println(err);
        } finally {}
    }
    
    
    /**
     * method : updatetData
     * @param none
    */  
    public void updateData() {
        List<WebElement> titles = new ArrayList<WebElement>();
        
        try {
            int count = 0;
            int index = 0;
            String title = "";
            while (true) {
                //get class name into arraylist
                //when reload the web page, then session is also reset there values
                //can't execute something with elements that we got before
                titles = driver.findElements(By.className("title"));
                
                String[] titleValue = new String[titles.size()];
                for (int i = 0; i < titles.size(); i++) {
                    titleValue[i] = titles.get(i).getText();
                }
                
                title = titleValue[index];
                if (title.contains("test")) {
                    webElement = titles.get(index);
                    webElement.click();
                    Thread.sleep(2000);
                    
                    //click modify button
                    driver.findElement(By.id("modify")).click();
                    Thread.sleep(2000);
                    
                    if (count % 2 == 0) {
                        //get id of input box where input article
                        webElement = driver.findElement(By.id("article"));
                        String upArticle = String.format("This is update test %d", count);
                        webElement.clear();
                        webElement.sendKeys(upArticle);
                        Thread.sleep(2000);
                    } else {
                        //get id of input box where input title
                        webElement = driver.findElement(By.id("input_title"));
                        String upTitle = String.format("updated test %d", count);
                        webElement.clear();
                        webElement.sendKeys(upTitle);
                        Thread.sleep(2000);
                    }
                    
                    //click the modify button
                    driver.findElement(By.id("modify")).click();
                    Thread.sleep(3000);
                    
                    //back to the main list
                    driver.findElement(By.id("back")).click();
                    Thread.sleep(4000);
                    count++;
                }

                index++;
                if (count == last) break;
            }
        } catch (Exception e) {
            String err = e.getMessage();
            System.out.println(err);
            e.printStackTrace();
        } finally {}
    }
    
    /**
     * method : deleteData
     * @param none
    */  
    public void deleteData() {
        try {
            int count = 0;
            while (true) {
                WebElement title = driver.findElement(By.className("title"));
                
                if (title.getText().contains("test")) {
                    title.click();
                    Thread.sleep(2000);
                    
                    //click the modify button 
                    driver.findElement(By.id("modify")).click();
                    Thread.sleep(3000);
                    
                    //click the delete button
                    driver.findElement(By.xpath("/html/body/div/form/input[2]")).click();
                    Thread.sleep(3000);
                    
                    //back to the main list
                    driver.findElement(By.id("btnToList")).click();
                    Thread.sleep(4000);
                    count++;
                }

                if (count == last) break;
            }
        } catch (Exception e) {
            String err = e.getMessage();
            System.out.println(err);
            e.printStackTrace();
        } finally {}
    }

}
