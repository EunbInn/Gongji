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
        
        String gwangjin = "https://www.gwangjin.go.kr/portal/bbs/B0000001/list.do?menuNo=200190";
        try {
            //광진구청 홈페이지 들어가기
            driver.get(gwangjin);
            Thread.sleep(3000);
            int listCnt = 1;
            driver.findElement(By.xpath("/html/body/div[2]/div[2]/main/div[2]/div[3]/ul/li[14]/a")).click();
            Thread.sleep(2000);
            
            ///////////////////////////////////////////////////////////
            /**마지막 페이지 번호 가져오기*/
            String lastPage = driver.getCurrentUrl();
            int last = Integer.parseInt(lastPage.split("pageIndex=")[1]);
            System.out.println(last);
            while (true) {
                //게시글 날짜 가져오기, 오늘 날짜가 아니면 break
                String dateWeb = driver.findElement(By.xpath(" /html/body/div[2]/div[2]/main/div[2]/div[2]/ul/li[" + listCnt + "]/span[3]")).getText();
                if (!dateWeb.equals(today)) break;
                
                //공지글 개별 url 및 제목 긁기
                String getUrl = driver.findElement(By.xpath("/html/body/div[2]/div[2]/main/div[2]/div[2]/ul/li[" + listCnt + "]/div/a")).getAttribute("href");
                String getTitle = driver.findElement(By.xpath("/html/body/div[2]/div[2]/main/div[2]/div[2]/ul/li[" + listCnt + "]/div/a/span")).getText();
           
                //해당 목록 글 들어가서 부서, 작성자명, 전화번호, 공지내용 긁기
                driver.get(getUrl);
                Thread.sleep(3000);
               
                String getField = driver.findElement(By.xpath("/html/body/div[2]/div[2]/main/div[2]/div[1]/div[1]/dl[1]/dd")).getText();
                String getName = driver.findElement(By.xpath("/html/body/div[2]/div[2]/main/div[2]/div[1]/div[1]/dl[2]/dd")).getText();
                String getTel = driver.findElement(By.xpath("/html/body/div[2]/div[2]/main/div[2]/div[1]/div[1]/dl[3]/dd")).getText();
                String getArticle = driver.findElement(By.xpath("/html/body/div[2]/div[2]/main/div[2]/div[1]/div[2]")).getText();
                Thread.sleep(2000);
                
                value = new Values(dateWeb, getTitle, getUrl, getField, getName, getTel, getArticle);
                arr.add(value);
                
                driver.get(gwangjin);
                Thread.sleep(3000);
                listCnt++;
            }
            
            
//         마지막목록    /html/body/div[2]/div[2]/main/div[2]/div[3]/ul/li[14]/a
//         리스트   /html/body/div[2]/div[2]/main/div[2]/div[2]/ul/li[7]/div/a/span
            driver.close();
        } catch (Exception e) {
            e.printStackTrace();
            String err = e.getMessage();
            System.out.println(err);
        }
            wc.write(arr);
        

    }

}
