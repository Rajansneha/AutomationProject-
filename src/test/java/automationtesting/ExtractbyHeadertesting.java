package automationtesting;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class ExtractbyHeadertesting {

	public static void main(String[] args) {
	
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com/");
		
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        
        WebElement dropdowmoption = driver.findElement(By.className("product_sort_container"));
        Select filter = new Select(dropdowmoption);
        filter.selectByVisibleText("Name (Z to A)");
      
        List<WebElement> productTitles = driver.findElements(By.className("inventory_item_name"));
        List<WebElement> productPrices = driver.findElements(By.className("inventory_item_price"));
        
        int count = Math.min(3, Math.min(productTitles.size(), productPrices.size()));
        System.out.println("Top Products (Name Z to A):");
        for (int i = 0; i < count; i++) {
            String title = productTitles.get(i).getText();
            String price = productPrices.get(i).getText();
            System.out.println((i + 1) + ". " + title + " - " + price);
        }
	}
}
