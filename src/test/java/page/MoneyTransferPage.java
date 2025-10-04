package page;

import com.codeborne.selenide.Selenide;
import data.DataHelper;
import org.openqa.selenium.Keys;


public class MoneyTransferPage {

    public void topUpCard(DataHelper.Card number, String sum) {

        Selenide.$("[data-test-id=amount] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        Selenide.$("[data-test-id=amount] input").setValue(sum);

        String from = "5559 0000 0000 0001";
        if (number.equals(DataHelper.getCard1())) {
            from = "5559 0000 0000 0002";
        }
        Selenide.$("[data-test-id=from] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        Selenide.$("[data-test-id=from] input").setValue(from);

        Selenide.$("[data-test-id=action-transfer]").click();

    }
}
