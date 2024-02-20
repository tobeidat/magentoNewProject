package magentoNewProject;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class myTestCases extends parameters {

	@BeforeTest
	public void setup() {
		driver.get(websitePage);
		driver.manage().window().maximize();
	}

	@Test(priority = 1)
	public void signup() throws InterruptedException {
		WebElement createAnAccountButton = driver.findElement(By.linkText("Create an Account"));
		createAnAccountButton.click();

		WebElement firstName = driver.findElement(By.name("firstname"));
		WebElement lastName = driver.findElement(By.name("lastname"));
		WebElement Email = driver.findElement(By.id("email_address"));
		WebElement password = driver.findElement(By.id("password"));
		WebElement confirmpassword = driver.findElement(By.id("password-confirmation"));

		firstName.sendKeys(userFirstNames[randomIndex1]);
		lastName.sendKeys(userFirstNames[randomIndex2]);
		Email.sendKeys(userEmail);
		password.sendKeys(userPassword);
		confirmpassword.sendKeys(userPassword);

		WebElement createAccountButton = driver.findElement(By.cssSelector("button[title='Create an Account'] span"));
		createAccountButton.click();
		Thread.sleep(2000);
	}

	@Test(priority = 2)
	public void logout() {
		driver.get(logoutPage);
	}

	@Test(priority = 3)
	public void login() throws InterruptedException {
		WebElement signInButton = driver.findElement(By.cssSelector("div[class='panel header'] li[data-label='or'] a"));
		signInButton.click();

		WebElement userLoginEmail = driver.findElement(By.id("email"));
		WebElement passwordElement = driver.findElement(By.id("pass"));

		userLoginEmail.sendKeys(userEmail);
		passwordElement.sendKeys(userPassword);

		WebElement signIn = driver.findElement(By.name("send"));
		signIn.click();
		Thread.sleep(3000);
	}

	@Test(priority = 4)
	public void bagSection() throws InterruptedException {
		driver.get(bagSection);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));

		WebElement bagContainer = driver.findElement(By.cssSelector(".products.list.items.product-items"));
		List<WebElement> allItems = bagContainer.findElements(By.className("product-item"));
		int count = 0;
		for (int i = 0; i < allItems.size(); i++)
			if (i % 2 != 0) {
				if (i != 7) {// the Impulse Duffle item is not available
					count = count + 1;
					allItems.get(i).click();
					WebElement addButton = driver.findElement(By.id("product-addtocart-button"));
					addButton.click();
					Thread.sleep(2000);
					driver.get(bagSection);
					bagContainer = driver.findElement(By.cssSelector(".products.list.items.product-items"));
					allItems = bagContainer.findElements(By.className("product-item"));

				}
			}
		Thread.sleep(3000);
		int expItemInTheCart = count;
		WebElement numberOfItemInCart = driver.findElement(By.className("counter-label"));
		int itemInTheCart = Integer.parseInt(numberOfItemInCart.getText());
		Assert.assertEquals(itemInTheCart, expItemInTheCart);

	}

	@Test(priority = 5)
	public void checkOutProccess() throws InterruptedException {

		WebElement theCartElement = driver.findElement(By.className("showcart"));
		theCartElement.click();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		WebElement checkoutButton = driver.findElement(By.id("top-cart-btn-checkout"));
		checkoutButton.click();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement company = driver.findElement(By.name("company"));
		company.sendKeys("Mawdoo3");

		WebElement street = driver.findElement(By.name("street[0]"));
		street.sendKeys("Yaser Sabaaneh Street");

		WebElement city = driver.findElement(By.name("city"));
		city.sendKeys("Amman");

		WebElement region = driver.findElement(By.name("region_id"));
		Select select = new Select(region);
		select.selectByValue("2");

		WebElement postcode = driver.findElement(By.name("postcode"));
		postcode.sendKeys("12345-6789");

		WebElement phone = driver.findElement(By.name("telephone"));
		phone.sendKeys("0798886554");

		Thread.sleep(5000);

		List<WebElement> tab = driver.findElements(By.className("radio"));
		Random rand = new Random();
		int num = rand.nextInt(2);
		tab.get(num).click();

		WebElement nextButton = driver.findElement(By.cssSelector(".button.action.continue.primary"));
		nextButton.click();
		
		Thread.sleep(7000);

		WebElement placeOrder = driver.findElement(By.className("checkout"));
		placeOrder.click();

	}

	@AfterTest
	public void postTest() {
	}

}
