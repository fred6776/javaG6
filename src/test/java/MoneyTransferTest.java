import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import data.DataHelper;
import page.DashboardPage;
import page.LoginPage;
import page.MoneyTransferPage;

import static com.codeborne.selenide.Selenide.open;

class MoneyTransferTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");

        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);


    }

    @Test
    void shouldTopUpFirstCard() {
        DashboardPage dash = new DashboardPage();
        MoneyTransferPage money = new MoneyTransferPage();
        int expected1 = dash.getCardBalance(DataHelper.getCard1());
        int expected2 = dash.getCardBalance(DataHelper.getCard2());
        dash.topUpCardClick(DataHelper.getCard1(), "300");
        money.topUpCard(DataHelper.getCard1(), "300");
        expected1 += 300;
        int actual1 = dash.getCardBalance(DataHelper.getCard1());
        Assertions.assertEquals(expected1, actual1);
        expected2 -= 300;
        int actual2 = dash.getCardBalance(DataHelper.getCard2());
        Assertions.assertEquals(expected2, actual2);
    }

    @Test
    void shouldTopUpSecondCard() {
        DashboardPage dash = new DashboardPage();
        MoneyTransferPage money = new MoneyTransferPage();
        int expected1 = dash.getCardBalance(DataHelper.getCard1());
        int expected2 = dash.getCardBalance(DataHelper.getCard2());
        dash.topUpCardClick(DataHelper.getCard2(), "400");
        money.topUpCard(DataHelper.getCard2(), "400");
        expected1 -= 400;
        int actual1 = dash.getCardBalance(DataHelper.getCard1());
        Assertions.assertEquals(expected1, actual1);
        expected2 += 400;
        int actual2 = dash.getCardBalance(DataHelper.getCard2());
        Assertions.assertEquals(expected2, actual2);
    }

}