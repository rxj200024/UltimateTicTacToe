package UltimateTicTacToe;

import java.util.Random;

public class Mark {
    private String mark = "";

    public Mark() {

    }

    public String getRandomMark() { // will be used to get a random char
        Random r = new Random();
        // generate random ascii value from 33 to 126
        int randomChar = r.nextInt(93);
        randomChar += 33;

        // randomChar will now be a value from 33 to 126

        char randVal = (char)randomChar;

        return mark + randVal;
    }

}
