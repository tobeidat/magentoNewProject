package magentoNewProject;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class parameters {
	WebDriver driver = new ChromeDriver();
	String websitePage = "https://magento.softwaretestingboard.com/";
	String logoutPage = "https://magento.softwaretestingboard.com/customer/account/logout/";
	String bagSection = "https://magento.softwaretestingboard.com/gear/bags.html";
	String[] userFirstNames = { "ahmad", "omar", "leen", "majd" };
	String[] userLastNames = { "amjd", "yaman", "ayham", "bisher" };
	Random rand = new Random();
	int randomIndex1 = rand.nextInt(userFirstNames.length);
	int randomIndex2 = rand.nextInt(userFirstNames.length);
	String userEmail = userFirstNames[randomIndex1] + userLastNames[randomIndex2] + rand.nextInt(9999) + "@gmail.com";
	String userPassword = "ABCabc3210";

}
