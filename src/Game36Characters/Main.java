package Game36Characters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BullsAndCows game = new BullsAndCows();
        game.Run();
    }
}

class BullsAndCows {
    private static final Scanner scanner = new Scanner(System.in);
    private String secretCode;
    BullsAndCows() {}

    public void Run() {
        this.secretCode = generateSecretCode();

        System.out.println("Okay, let's start a game!");

        String guess;
        int turn = 1;

        do {
            System.out.printf("Turn %d:\n", turn);
            guess = scanner.next();
            analyzeAndGradeGuess(guess);
            turn++;
        } while (!secretCode.equals(guess));

        System.out.println("Congratulations! You guessed the secret code.");
    }

    public static String generateSecretCode() {
        System.out.println("Input the length of the secret code:");
        int length = scanner.nextInt();

        if (length > 36) {
            System.out.printf("Error: can't generate a secret number with a length of %d because there aren't enough unique digits.", length);
            return "";
        }

        System.out.println("Input the number of possible symbols in the code:");
        int possibleSymbolsLength = scanner.nextInt();

        ArrayList<Character> symbols = new ArrayList<>(Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));
        ArrayList<Character> possibleSymbols = new ArrayList<>(symbols.subList(0, possibleSymbolsLength));
        String secretCode = "";

        String numbersString = possibleSymbolsLength > 10 ? "0-9" : "0-" + possibleSymbols.get(possibleSymbolsLength - 1);
        String comma = possibleSymbolsLength > 10 ? "," : "";
        String charactersString = possibleSymbolsLength < 11 ? "" : "a-" + possibleSymbols.get(possibleSymbolsLength - 1);

        for (int i = 0; i < length; i++) {
            int randomIndex;
            randomIndex = getRandomNumber(0, possibleSymbols.size());
            secretCode += possibleSymbols.get(randomIndex);
            possibleSymbols.remove(possibleSymbols.get(randomIndex));
        }

        String asterisksLine = new String(new char[length]).replace('\0', '*');

        System.out.printf("The secret is prepared: %s (%s%s %s).", asterisksLine, numbersString, comma, charactersString);
        return secretCode;
    }

    public static int getRandomNumber(int lowerLimit, int upperLimit) {
        Random random = new Random();
        return random.nextInt(upperLimit - lowerLimit) + lowerLimit;
    }

    private void analyzeAndGradeGuess(String guess) {
        int bulls = 0;
        int cows = 0;

        for(int i = 0; i < guess.length(); i++) {
            if (guess.charAt(i) == secretCode.charAt(i)) {
                bulls++;
            } else {
                if (secretCode.contains(String.valueOf(guess.charAt(i)))) {
                    cows++;
                }
            }
        }

        gradeGuess(bulls, cows);
    }

    private void gradeGuess(int bulls, int cows) {
        String bullsString = bulls == 1 ? "bull" : "bulls";
        String cowsString = cows == 1 ? "cow" : "cows";

        String grade = "None";

        if (bulls > 0 && cows > 0){
            grade = String.format("%d %s and %d %s", bulls, bullsString, cows, cowsString, secretCode);
        } else if (bulls > 0) {
            grade = String.format("%d %s", bulls, bullsString, secretCode);
        } else if (cows > 0){
            grade = String.format("%d %s", cows, cowsString, secretCode);
        }

        System.out.printf("Grade: %s\n", grade);
    }

}