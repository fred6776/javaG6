package page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");

    public DashboardPage() {
        heading.shouldBe(visible);
    }


    // к сожалению, разработчики не дали нам удобного селектора, поэтому так
    private ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";


    public int getCardBalance(String id) {
        for (SelenideElement card : cards) {
            String idFact = card.attr("data-test-id");
            if (id.equals(idFact)) {
                return extractBalance(card.text());
            }
        }
        return -1;
    }

    private int extractBalance(String text) {
        var start = text.indexOf(balanceStart);
        var finish = text.indexOf(balanceFinish);
        var value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public void topUpCard(String id, String sum) {
        for (SelenideElement card : cards) {
            String idFact = card.attr("data-test-id");
            if (id.equals(idFact)) {
                card.$(byText("Пополнить")).click();

                Selenide.$("[data-test-id=amount] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
                Selenide.$("[data-test-id=amount] input").setValue(sum);

                String from = "5559 0000 0000 0001";
                if (id.equals(("92df3f1c-a033-48e6-8390-206f6b1f56c0"))) {
                    from = "5559 0000 0000 0002";
                }
                Selenide.$("[data-test-id=from] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
                Selenide.$("[data-test-id=from] input").setValue(from);

                Selenide.$("[data-test-id=action-transfer]").click();

                break;
            }
        }
    }
}
