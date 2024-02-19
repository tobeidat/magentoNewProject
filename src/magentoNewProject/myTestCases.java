package magentoNewProject;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class myTestCases {
	WebDriver driver = new ChromeDriver();
	String websitePage="https://magento.softwaretestingboard.com/";
	String logoutPage="https://magento.softwaretestingboard.com/customer/account/logout/";
	String bagSection = "https://magento.softwaretestingboard.com/gear/bags.html";
	String[] userFirstNames= {"ahmad","omar","leen","majd"};
	String[] userLastNames= {"amjd","yaman","ayham","bisher"};
	Random rand = new Random();
	int randomIndex1=rand.nextInt(userFirstNames.length);
	int randomIndex2=rand.nextInt(userFirstNames.length);
	String userEmail= userFirstNames[randomIndex1]+userLastNames[randomIndex2]+rand.nextInt(9999)+"@gmail.com";
	String userPassword="ABCabc3210";
	

	
	@BeforeTest
	public void setup()
	{
		driver.get(websitePage);
		driver.manage().window().maximize();
	}
	@Test(priority = 1)
	public void signup() throws InterruptedException
	{
		WebElement createAnAccountButton=driver.findElement(By.linkText("Create an Account"));
		createAnAccountButton.click();
		
		WebElement firstName=driver.findElement(By.name("firstname"));
		WebElement lastName=driver.findElement(By.name("lastname"));
		WebElement Email=driver.findElement(By.id("email_address"));
		WebElement password=driver.findElement(By.id("password"));
		WebElement confirmpassword=driver.findElement(By.id("password-confirmation"));
		
		firstName.sendKeys(userFirstNames[randomIndex1]);
		lastName.sendKeys(userFirstNames[randomIndex2]);
		Email.sendKeys(userEmail);
		password.sendKeys(userPassword);
		confirmpassword.sendKeys(userPassword);
		
		WebElement createAccountButton=driver.findElement(By.cssSelector("button[title='Create an Account'] span"));
		createAccountButton.click();
		Thread.sleep(2000);
	}
	@Test(priority = 2)
	public void logout()
	{
		driver.get(logoutPage);
	}
	@Test(priority = 3)
	public void login() throws InterruptedException
	{
		WebElement signInButton=driver.findElement(By.cssSelector("div[class='panel header'] li[data-label='or'] a"));
		signInButton.click();
		
		WebElement userLoginEmail=driver.findElement(By.id("email"));
		WebElement passwordElement=driver.findElement(By.id("pass"));
		
		userLoginEmail.sendKeys(userEmail);
		passwordElement.sendKeys(userPassword);

		WebElement signIn=driver.findElement(By.name("send"));
		signIn.click();
		Thread.sleep(3000);
		}
	@Test(priority = 4)
	public void bagSection() throws InterruptedException
	{
		driver.get(bagSection);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
		
		WebElement bagContainer =driver.findElement(By.cssSelector(".products.list.items.product-items"));
		List<WebElement> allItems=bagContainer.findElements(By.className("product-item"));
		for(int i=0;i<allItems.size();i++)
			if(i % 2 != 0)
			{
			allItems.get(i).click();
			WebElement addButton=driver.findElement(By.id("product-addtocart-button"));
			addButton.click();
			Thread.sleep(2000);
			driver.get(bagSection);
			bagContainer = driver.findElement(By.cssSelector(".products.list.items.product-items"));
            allItems = bagContainer.findElements(By.className("product-item"));
		   
			}//the Impulse Duffle item is not available
		String expItemInTheCart= "5";
		WebElement numberOfItemInCart=driver.findElement(By.className("counter-number"));
		String itemInTheCart=numberOfItemInCart.getText();
		Assert.assertEquals(itemInTheCart, expItemInTheCart);
		
	}

	
	@AfterTest
	public void postTest()
	{}

}
