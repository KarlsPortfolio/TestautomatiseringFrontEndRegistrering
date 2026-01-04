package data;

import models.User;

public class TestFactory {
    public static User getUser(String userType) {
        User output = null;
        String dynamicEmail = DataGenerator.getDynamicEmail("testare_andersson");
        String confirmEmail = dynamicEmail;


        //Standard-användare som är myndig och ifyllt alla obligatoriska fält
        if (userType.equalsIgnoreCase("Standard User")) {
            output = new User("01-01-2008", "testare", "andersson", dynamicEmail,
                    confirmEmail, "secret_pass", "secret_pass", true);
        }

        return output;

    }
}
