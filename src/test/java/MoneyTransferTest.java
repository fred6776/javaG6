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
        int expected1 = dash.getCardBalance(DataHelper.getCard1());
        int expected2 = dash.getCardBalance(DataHelper.getCard2());
        dash.topUpCardClick(DataHelper.getCard1(), String.valueOf(expected2));
        MoneyTransferPage money = new MoneyTransferPage();
        money.writeOfCard(DataHelper.getCard2(), String.valueOf(expected2));
        expected1 += expected2;
        int actual1 = dash.getCardBalance(DataHelper.getCard1());
        Assertions.assertEquals(expected1, actual1);
        expected2 -= expected2;
        int actual2 = dash.getCardBalance(DataHelper.getCard2());
        Assertions.assertEquals(expected2, actual2);
    }

    @Test
    void shouldTopUpSecondCard() {
        DashboardPage dash = new DashboardPage();
        int expected1 = dash.getCardBalance(DataHelper.getCard1());
        int expected2 = dash.getCardBalance(DataHelper.getCard2());
        dash.topUpCardClick(DataHelper.getCard2(), String.valueOf(expected1));
        MoneyTransferPage money = new MoneyTransferPage();
        money.writeOfCard(DataHelper.getCard1(), String.valueOf(expected1));
        expected2 += expected1;
        int actual2 = dash.getCardBalance(DataHelper.getCard2());
        Assertions.assertEquals(expected2, actual2);
        expected1 -= expected1;
        int actual1 = dash.getCardBalance(DataHelper.getCard1());
        Assertions.assertEquals(expected1, actual1);
    }

}