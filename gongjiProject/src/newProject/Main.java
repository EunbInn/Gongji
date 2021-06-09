package newProject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {

    public static void main(String[] args) {
        String WEB_DRIVER_ID = "webdriver.chrome.driver";
        String WEB_DRIVER_PATH = "C:\\Users\\inna\\Desktop\\importFile\\chromedriver.exe";
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
        WebDriver driver = new ChromeDriver();
        ArrayList<Values> arr = new ArrayList<Values>();
        Values value;
        WriteCSV wc = new WriteCSV();
        
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sdf.format(date.getTime());
        try {
            driver.get("https://www.gwangjin.go.kr/portal/bbs/B0000001/list.do?menuNo=200190");
            Thread.sleep(3000);
            int cnt = 1;
            while (true) {
                String dateWeb = driver.findElement(By.xpath(" /html/body/div[2]/div[2]/main/div[2]/div[2]/ul/li[" + cnt + "]/span[3]")).getText();
                if (!dateWeb.equals(today)) break;
                
                String getUrl = driver.findElement(By.xpath("/html/body/div[2]/div[2]/main/div[2]/div[2]/ul/li[" + cnt + "]/div/a")).getAttribute("href");
                String getTitle = driver.findElement(By.xpath("/html/body/div[2]/div[2]/main/div[2]/div[2]/ul/li[" + cnt + "]/div/a/span")).getText();
           
                driver.get(getUrl);
                Thread.sleep(3000);
               
                String getField = driver.findElement(By.xpath("/html/body/div[2]/div[2]/main/div[2]/div[1]/div[1]/dl[1]/dd")).getText();
                String getName = driver.findElement(By.xpath("/html/body/div[2]/div[2]/main/div[2]/div[1]/div[1]/dl[2]/dd")).getText();
                String getTel = driver.findElement(By.xpath("/html/body/div[2]/div[2]/main/div[2]/div[1]/div[1]/dl[3]/dd")).getText();
                String getArticle = driver.findElement(By.xpath("/html/body/div[2]/div[2]/main/div[2]/div[1]/div[2]")).getText();
                Thread.sleep(2000);
                
                value = new Values(dateWeb, getTitle, getUrl, getField, getName, getTel, getArticle);
                arr.add(value);
                
                driver.get("https://www.gwangjin.go.kr/portal/bbs/B0000001/list.do?menuNo=200190");
                Thread.sleep(3000);
                cnt++;
            }
            
            driver.close();
        } catch (Exception e) {
            e.printStackTrace();
            String err = e.getMessage();
            System.out.println(err);
        }
            wc.write(arr);
        

    }

}
