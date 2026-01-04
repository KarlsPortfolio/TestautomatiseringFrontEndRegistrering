package data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataGenerator {
    //Genererar en dynamisk emailadress utifrån textsträngen innan @, där proton.me är förvalt som email-provider
    public static String getDynamicEmail(String input){
        //Hämtar dagens tid i ISO-8601
        LocalDateTime currentDate = LocalDateTime.now();
        //Skapa formateringen av ISO-8601 enligt ett visst mönster
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        //Formatera tidsobjektet och spara till en String
        String formattedDateTime = currentDate.format(formatter);

        //Dynamisk email-adress skapas där dagens tid formaterad enligt ovan appliceras till email-adressen
        String dynamicEmail = input + formattedDateTime + "@proton.me";

        return dynamicEmail;
    }
}
