import java.util.Arrays;
import java.util.Random;

public class MastermindGame {
    private int turns;
    private int maxTurns;
    public String secretCode;
    private boolean playerWon;
    private int amountOfCorrectColorPositions;
    private int amountOfCorrectColors;

    public MastermindGame() {
        this.turns = 0;
        this.maxTurns = 8;
        this.amountOfCorrectColors = 0;
        this.amountOfCorrectColorPositions = 0;
        this.playerWon = false;
    }
    // method which generates the code by using the Random number class
    public void generateSecretCode() {
        StringBuilder code = new StringBuilder();
        Random rand = new Random();

        // loop 4 times to generate a String containing 4 color codes
        // color option : B | G | Y | R
        for(int i = 0; i < 4; i++) {
            int colorCode = rand.nextInt(4 - 0);

            // color is B (blue)
            if(colorCode == 0) {
                code.append("B");
            }
            // color is G(green)
            else if(colorCode == 1) {
                code.append("G");
            }
            // color is Y(yellow)
            else if(colorCode == 2) {
                code.append("Y");
            }
            // color is R(red)
            else if(colorCode == 3) {
                code.append("R");
            }
        }

        this.secretCode = code.toString();
    }

    // method which checks the combination the player has input
    // if the player guessed all characters right the playerWon boolean gets turned to true
    public void checkCombination(String guessString) {
        // test amount of correctly guessed characters on correct position
        char[] playerGuessCharacters  = guessString.toLowerCase().toCharArray();
        char[] secretCodeCharacters = secretCode.toLowerCase().toCharArray();
        boolean[] correctColorsCorrectPosition = {false, false, false, false}; // array to track correctly guessed characters at right position
        boolean[] correctColorsWrongPosition = {false, false, false, false}; // array to track correctly guessed colors at wrong position
        int minLength = Math.min(guessString.length(), secretCode.length());
        int correctPositionCounter = 0;
        int correctColorCounter = 0;

        // for each character from the guessString compare to secretCode
        for(int i = 0; i < minLength; i++) {
            if (playerGuessCharacters[i] == secretCodeCharacters[i]) {
                correctPositionCounter++;
                correctColorsCorrectPosition[i] = true;
            }
        }

        // this method calculates the amount of correct colors are at the wrong position
        // it iterates over each character from the guessString and compares to secretCode
        // comparison gets ignored if correctColorsCorrectPosition[i] == true ||
        // correctColorsWrongPosition[j] == true
        for(int i = 0; i < minLength; i++)
        {
            if(!correctColorsCorrectPosition[i]) {
                for (int j = 0; j < minLength; j++) {
                    if (playerGuessCharacters[j] == secretCodeCharacters[i] && !correctColorsWrongPosition[j] && !correctColorsCorrectPosition[j]) {
                        correctColorCounter++;
                        correctColorsWrongPosition[j] = true;
                        j = minLength;
                    }
                }
            }
        }

        this.amountOfCorrectColorPositions = correctPositionCounter;
        this.amountOfCorrectColors = correctColorCounter;
        this.turns++;

        if(this.amountOfCorrectColorPositions == 4) {
            this.playerWon = true;
        }
    }

    // print correctly guessed characters to console, print correctly guessed colors at wrong position to console
    public String getHint() {
        return "right colour right position: " + this.amountOfCorrectColorPositions + "\n" + "right colour wrong position: " + this.amountOfCorrectColors;
    }

    // return the playerWon boolean
    public boolean isWon() {
        return playerWon;
    }

    // return a boolean which gets set to true when the player has used 8 turns
    public boolean maxTurnsUsed() {
        return this.turns > this.maxTurns;
    }

    public String getSecretCode() {
        return secretCode;
    }
}
