import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class FacebookRegister {

    WebDriver driver=null;
   /* public  static void main(String[]args)

    {
            String chromePath=System.getProperty("user.dir")+ "\\src\\main\\resources\\chromedriver.exe";
            System.setProperty("webdriver.chrome.driver",chromePath);
            WebDriver driver=new ChromeDriver();

            driver.navigate().to("https://www.google.com/");
    }*/
    @BeforeClass
    public void OpenChromeBrowser() throws InterruptedException
    {
        String chromepath=System.getProperty("user.dir")+"\\src\\main\\resources\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",chromepath);
        driver=new ChromeDriver();
        driver.navigate().to("https:\\www.google.com");

    }

    @Test(dataProvider = "testData")
    public void register (String firstname,String  lastname,String reg_email__,String reg_email_confirmation__,
                          String reg_passwd__,String day,String month,String year ,String gender)
    {

        driver.navigate().to("https:\\www.facebook.com");

        driver.findElement(By.xpath("//*[text()='Create New Account']")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.name("firstname")).sendKeys(firstname);
        driver.findElement(By.name("lastname")).sendKeys(lastname);
        driver.findElement(By.name("reg_email__")).sendKeys(reg_email__);
        driver.findElement(By.name("reg_email_confirmation__")).sendKeys(reg_email_confirmation__);

        driver.findElement(By.name("reg_passwd__")).sendKeys(reg_passwd__);

        //Select Month/Day/Year

        Select selectday=new Select( driver.findElement(By.xpath("//*[@id=\"day\"]")));
        selectday.selectByVisibleText(day);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Select selectMonth=new Select( driver.findElement(By.xpath(".//*[@id=\"month\"]")));
        selectMonth.selectByVisibleText(month);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        Select selectYear=new Select( driver.findElement(By.id("year")));
        selectYear.selectByVisibleText(year);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        //Select Male or Female
       // driver.findElement(By.xpath("//label[text()='Male']")).click();
        driver.findElement(By.xpath("//label[text()=' \" + itemText + \"']")).click();
        driver.findElement(By.xpath("//td//label[.='" + gender + "']//preceding::input[1]")).click();
       driver.findElement(By.name("websubmit")).click();



    }
   @AfterTest
    public void closeChromeBrowser()
    {
       driver.close();
    }

    @DataProvider
    public Object[][] testData() throws IOException, InvalidFormatException {
        Read_RegisterExelData obj=new Read_RegisterExelData();

        return obj.read_Sheet();
    }

}
