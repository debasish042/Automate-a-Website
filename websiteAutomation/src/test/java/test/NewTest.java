package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class NewTest {
    private WebDriver wd;

    @BeforeClass
    public void setUp() {
        // Set up ChromeDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();
        wd = new ChromeDriver();
        wd.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
        wd.quit();
    }

    @Test(priority = 1)
    public void register() throws InterruptedException {
        wd.get("https://www.amazon.in/");
        wd.findElement(By.id("nav-link-accountList")).click();
        wd.findElement(By.id("createAccountSubmit")).click();
        wd.findElement(By.id("ap_customer_name")).sendKeys("Debasish Sahu");
        wd.findElement(By.id("ap_phone_number")).sendKeys("7008096822");
        wd.findElement(By.id("ap_email")).sendKeys("rajaseher99@gmail.com");
        wd.findElement(By.id("ap_password")).sendKeys("Raja@1234");
        Thread.sleep(3000);
        wd.findElement(By.id("continue")).click();
    }

    @Test(priority = 2)
    public void login() throws InterruptedException {
        wd.get("https://www.amazon.in/");
        wd.findElement(By.id("nav-link-accountList")).click();
        wd.findElement(By.id("ap_email")).sendKeys("rajaseher99@gmail.com");
        Thread.sleep(7000);
        wd.findElement(By.id("continue")).click();
        wd.findElement(By.id("ap_password")).sendKeys("Raja@1234");
    }

    @Test(priority = 3)
    public void searchProduct() throws InterruptedException {
        wd.get("https://www.amazon.in/");
        System.out.println(wd.getTitle());
        System.out.println(wd.getCurrentUrl());
        wd.findElement(By.id("twotabsearchtextbox")).sendKeys("Apple iPhone");
        Thread.sleep(7000);
        wd.findElement(By.id("nav-search-submit-button")).submit();
        Thread.sleep(7000);
    }
    
    @Test(priority = 4)
    public void addToCart() throws InterruptedException {
        wd.get("https://www.amazon.in/");
        System.out.println(wd.getTitle());
        System.out.println(wd.getCurrentUrl());

        // Perform the search
        wd.findElement(By.id("twotabsearchtextbox")).sendKeys("Apple iPhone 14 Midnight Black");
        Thread.sleep(3000);
        wd.findElement(By.id("nav-search-submit-button")).submit();
        Thread.sleep(5000);

        // Click on the product from the search results to view the product details
        wd.findElement(By.cssSelector("a[href*='Apple-iPhone-14-128GB-Midnight']")).click();
        Thread.sleep(3000);

        // Check if the product details page is loaded successfully
        if (wd.getCurrentUrl().contains("Apple-iPhone-14-128GB-Midnight")) {
            // Add the product to the cart
            wd.findElement(By.id("add-to-cart-button")).click();
            Thread.sleep(5000);

            // Go to the cart
            wd.get("https://www.amazon.in/gp/cart/view.html");
            Thread.sleep(5000);
        } else {
            System.out.println("Product details page not loaded properly");
        }
    }


}



