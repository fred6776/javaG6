package page;

import com.codeborne.selenide.Selenide;
import data.DataHelper;
import org.openqa.selenium.Keys;


public class MoneyTransferPage {

    public void writeOfCard(DataHelper.Card writeOfCard, String sum) {

        Selenide.$("[data-test-id=amount] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        Selenide.$("[data-test-id=amount] input").setValue(sum);


        Selenide.$("[data-test-id=from] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        Selenide.$("[data-test-id=from] input").setValue(writeOfCard.getNumber());

        Selenide.$("[data-test-id=action-transfer]").click();

    }
}
