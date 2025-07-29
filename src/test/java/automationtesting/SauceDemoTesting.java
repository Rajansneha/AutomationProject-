package automationtesting;
import java.time.Duration;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
public class SauceDemoTesting {
	WebDriver driver;
	@BeforeMethod
	public void launchapp(){
		
		driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com/");
		
		}
	@Test
	public void titleverification() {
		@Nullable
		String pagetitle = driver.getTitle();
		System.out.println("PAGE TITLE IS  "+ pagetitle);
	}
	 @Test(dataProvider = "filtereditem")
	public void testingFilterandAddItemtocart(String filterOption) throws InterruptedException   {
		  
		  driver.findElement(By.id("user-name")).sendKeys("standard_user");
		  driver.findElement(By.id("password")).sendKeys("secret_sauce");
		  driver.findElement(By.id("login-button")).click();
		  Thread.sleep(3000);
		  
		  Select dropdown = new Select(driver.findElement(By.className("product_sort_container")));
		   dropdown.selectByVisibleText(filterOption);
		   //driver.findElement(By.xpath("//button[text()='OK']")).click();
		   Thread.sleep(3000);
		   
		    WebElement cheapproduct = driver.findElements(By.className("inventory_item")).get(0);
	        String productname = cheapproduct.findElement(By.className("inventory_item_name")).getText();
	        String productprice = cheapproduct.findElement(By.className("inventory_item_price")).getText();
            
	        cheapproduct.findElement(By.tagName("button")).click();
	        
	        driver.findElement(By.className("shopping_cart_link")).click();       
	        WebElement cartItem = driver.findElement(By.className("cart_item"));
	        String cartprdname = cartItem.findElement(By.className("inventory_item_name")).getText();
	        String cartprdprice = cartItem.findElement(By.className("inventory_item_price")).getText();
	        String cartprdquantity = cartItem.findElement(By.className("cart_quantity")).getText();
	        
	        SoftAssert softAssert = new SoftAssert();
	        softAssert.assertEquals(cartprdname, productname);
	        softAssert.assertEquals(cartprdprice, productprice);
	        softAssert.assertEquals(cartprdquantity, "1");
	        softAssert.assertAll();
	        System.out.println("Matching name- " + cartprdname + " : " + productname);
	        System.out.println("Matching price- " + cartprdprice + " : " + productprice );
	        System.out.println("Matching quantity- " + cartprdquantity);
	        System.out.println("The filter item by" + filterOption);
		}
	 @DataProvider(name = "filtereditem")
	    public Object[][] filtertheoption() {
	        return new Object[][] {
	            {"Price (low to high)"},
	            {"Price (high to low)"},
	            {"Name (A to Z)"}
	        };
	 }
	 public void closeapp() {
		 driver.quit(); 
	            System.out.println("Browser closed after test");
	        
	    }
}
