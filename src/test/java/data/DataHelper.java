package data;

import lombok.Value;
import page.DashboardPage;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }


    @Value
    public static class Card {
        private String card;
    }

    public static String getCard1() {
        return "5559 0000 0000 0001";
    }

    public static String getCard2() {
        return "5559 0000 0000 0002";
    }

    public static int getCardBalance(String card) {
        DashboardPage dash = new DashboardPage();
        if (card.equals("5559 0000 0000 0001")) {
            return dash.getCardBalance("92df3f1c-a033-48e6-8390-206f6b1f56c0");
        }
        if (card.equals("5559 0000 0000 0002")) {
            return dash.getCardBalance("0f3f5c2a-249e-4c3d-8287-09f7a039391d");
        }
        return -1;
    }

    public static void topUpCard(String card, String sum) {
        DashboardPage dash = new DashboardPage();
        if (card.equals("5559 0000 0000 0001")) {
            dash.topUpCard("92df3f1c-a033-48e6-8390-206f6b1f56c0", sum);
        }
        if (card.equals("5559 0000 0000 0002")) {
            dash.topUpCard("0f3f5c2a-249e-4c3d-8287-09f7a039391d", sum);
        }
    }
}