import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
    @FindBy(xpath = "//*[@id=\"root\"]/div/div/center/form/div[1]/h1")
    private WebElement signInTxt;

    @FindBy(id = "exampleEmail")
    private WebElement emailTxt;

    @FindBy(id = "examplePassword")
    private WebElement passwordTxt;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/center/form/div[4]/button")
    private WebElement loginBtn;

    public void enterDetails(String email, String password) {
        emailTxt.sendKeys(email);
        passwordTxt.sendKeys(password);
    }

    public void submitDetails() {
        loginBtn.click();
    }

    public WebElement getSignInTxt() {
        return signInTxt;
    }
}
