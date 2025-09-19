import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import data.DataHelper;
import page.LoginPage;

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

        DataHelper.topUpCard("5559 0000 0000 0001", "300");
        int expected = 10300;
        int actual = DataHelper.getCardBalance(DataHelper.getCard1());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTopUpSecondCard() {

        DataHelper.topUpCard("5559 0000 0000 0002", "400");
        int expected = 10100;
        int actual = DataHelper.getCardBalance(DataHelper.getCard2());
        Assertions.assertEquals(expected, actual);
    }

}