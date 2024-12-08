// GiftPage.java
package gift.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GiftPage {

    @FindBy(id = "targetName")
    private WebElement targetNameField;

    @FindBy(id = "age")
    private WebElement ageField;

    @FindBy(id = "relationship")
    private WebElement relationshipField;

    @FindBy(id = "status")
    private WebElement statusField;

    @FindBy(id = "itemCount")
    private WebElement itemCountField;

    @FindBy(id = "submit-button")
    private WebElement submitButton;

    @FindBy(id = "recommendation-result")
    private WebElement recommendationResult;

    public GiftPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public String requestGiftRecommendation(
            String targetName,
            int age,
            String relationship,
            String status,
            int itemCount) {
        targetNameField.clear();
        targetNameField.sendKeys(targetName);

        ageField.clear();
        ageField.sendKeys(String.valueOf(age));

        relationshipField.clear();
        relationshipField.sendKeys(relationship);

        statusField.clear();
        statusField.sendKeys(status);

        itemCountField.clear();
        itemCountField.sendKeys(String.valueOf(itemCount));

        submitButton.click();

        return recommendationResult.getText();
    }
}