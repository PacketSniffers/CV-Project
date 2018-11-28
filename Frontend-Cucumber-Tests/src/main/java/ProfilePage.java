import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfilePage {
    @FindBy(xpath = "//*[@id=\"emailLink\"]/a")
    private WebElement emailLink;

    public WebElement getEmailLink() {
        return emailLink;
    }
}
