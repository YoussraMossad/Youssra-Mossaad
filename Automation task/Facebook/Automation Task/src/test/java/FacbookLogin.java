import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class FacbookLogin {
    WebDriver driver=null;
    @BeforeClass
    public void OpenChromeBrowser() throws InterruptedException
    {
        String chromepath=System.getProperty("user.dir")+"\\src\\main\\resources\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",chromepath);
        driver=new ChromeDriver();
        driver.navigate().to("https:\\www.facebook.com");

    }
   @Test(dataProvider = "testData")
    public void login(String email,String pass)
    {
        driver.manage().window().maximize();
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("pass")).sendKeys(pass);
        driver.findElement(By.name("login")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    @AfterTest
    public void closeChromeBrowser()
    {
        driver.close();
    }

    @DataProvider
    public Object[][] testData() throws IOException, InvalidFormatException {
        Read_LoginExcelData obj=new Read_LoginExcelData();

        return obj.read_Sheet();
    }
}
