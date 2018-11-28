import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import static org.junit.Assert.assertEquals;

public class Step {
    WebDriver driver = null;
    ExtentReports report = new ExtentReports("C:\\Users\\Admin\\Desktop\\reports\\login_reports.html");;
    ExtentTest test;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\Desktop\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @After
    public void teardown() {
        driver.quit();
    }

    @Given("^that you are on the Log In Page$")
    public void that_you_are_on_the_Log_In_Page() throws Throwable {
        test = report.startTest("Task 1: Allow users to log in to the application");
        LoginPage login = PageFactory.initElements(driver, LoginPage.class);
        driver.get("http://localhost:3000/");
        assertEquals("Sign In", login.getSignInTxt().getText());
        test.log(LogStatus.INFO, "Log in page opened");
    }

    @When("^the correct  user details \"([^\"]*)\" username, \"([^\"]*)\" password are entered on the Log In Page$")
    public void the_correct_user_details_username_password_are_entered_on_the_Log_In_Page(String arg1, String arg2) throws Throwable {
        LoginPage login = PageFactory.initElements(driver, LoginPage.class);
        login.enterDetails(arg1, arg2);
        test.log(LogStatus.INFO, "User details entered");
    }

    @When("^the details are submitted on the Log In Page$")
    public void the_details_are_submitted_on_the_Log_In_Page() throws Throwable {
        LoginPage login = PageFactory.initElements(driver, LoginPage.class);
        login.submitDetails();
        test.log(LogStatus.INFO, "User details submitted for validation");
        Thread.sleep(1000);
    }

    @Then("^the \"([^\"]*)\" email should be displayed on the Profile Page$")
    public void the_email_should_be_displayed_on_the_Profile_Page(String arg1) throws Throwable {
        ProfilePage profile = PageFactory.initElements(driver, ProfilePage.class);
        if (arg1.equals(profile.getEmailLink().getText())) {
            test.log(LogStatus.PASS, "User logged in successfully");
        } else {
            test.log(LogStatus.FAIL, "User failed to log in");
        }
        report.flush();
        report.endTest(test);
        assertEquals(arg1, profile.getEmailLink().getText());
    }

    @When("^the incorrect correct user details \"([^\"]*)\" username, \"([^\"]*)\" password are entered on the Log In Page$")
    public void the_incorrect_correct_user_details_username_password_are_entered_on_the_Log_In_Page(String arg1, String arg2) throws Throwable {
        LoginPage login = PageFactory.initElements(driver, LoginPage.class);
        login.enterDetails(arg1, arg2);
        login.submitDetails();
    }

    @Then("^the user should be denied access$")
    public void the_user_should_be_denied_access() throws Throwable {
        LoginPage login = PageFactory.initElements(driver, LoginPage.class);
        assertEquals("Sign In", login.getSignInTxt().getText());
    }
}
