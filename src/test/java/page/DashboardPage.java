package page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import static com.codeborne.selenide.Condition.attribute;
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


    public int getCardBalance(DataHelper.Card id) {
        SelenideElement card = cards.findBy(attribute("data-test-id", id.getId()));

        if (card != null) {
            return extractBalance(card.text());
        }

        return -1;
    }

    private int extractBalance(String text) {
        var start = text.indexOf(balanceStart);
        var finish = text.indexOf(balanceFinish);
        var value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public void topUpCardClick(DataHelper.Card id, String sum) {
        SelenideElement card = cards.findBy(attribute("data-test-id", id.getId()));
        if (id.equals(DataHelper.getCard1())) {
            if (getCardBalance(DataHelper.getCard2()) - Integer.parseInt(sum) <= 0) {
                System.out.println("Во второй карте недостаточно средств");
            } else card.$(byText("Пополнить")).click();
        } else if (id.equals(DataHelper.getCard2())) {
            if (getCardBalance(DataHelper.getCard1()) - Integer.parseInt(sum) <= 0) {
                System.out.println("В первой карте недостаточно средств");
            } else card.$(byText("Пополнить")).click();
        }
    }
}
