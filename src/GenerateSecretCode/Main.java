package GenerateSecretCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BullsAndCows.generateSecretCode();
        //BullsAndCows game = new BullsAndCows("9305");
        //game.Run();
    }
}

class BullsAndCows {
    private static final Scanner scanner = new Scanner(System.in);
    private String secretCode;
    BullsAndCows(String secretCode) {
        this.secretCode = secretCode;
    }

    public void Run() {
        String guess = scanner.next();
        int bulls = 0;
        int cows = 0;

        for(int i = 0; i < 4; i++) {
            if (guess.charAt(i) == secretCode.charAt(i)) {
                bulls++;
            } else {
                if (secretCode.contains(String.valueOf(guess.charAt(i)))) {
                    cows++;
                }
            }
        }

        if (bulls == 0 && cows == 0) {
            System.out.printf("Grade: None. The secret code is %s", secretCode);
        } else if (bulls > 0 && cows > 0){
            System.out.printf("Grade: %d bull(s) and %d cow(s). The secret code is %s", bulls, cows, secretCode);
        } else if (bulls > 0) {
            System.out.printf("Grade: %d bull(s). The secret code is %s", bulls, secretCode);
        } else {
            System.out.printf("Grade: %d cow(s). The secret code is %s", cows, secretCode);
        }
    }

    public static String generateSecretCode() {
        int length = scanner.nextInt();

        if (length > 10) {
            System.out.printf("Error: can't generate a secret number with a length of %d because there aren't enough unique digits.", length);
            return "";
        }

        ArrayList<Character> numbers = new ArrayList<>(Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9'));
        String secretCode = "";

        for (int i = 0; i < length; i++) {
            int randomIndex;
            if (i == 0) {
                randomIndex = getRandomNumber(1, 10);
            } else {
                randomIndex = getRandomNumber(0, numbers.size());
            }
            secretCode += numbers.get(randomIndex);
            numbers.remove(numbers.get(randomIndex));
        }

        System.out.printf("The random secret number is %s.", secretCode);
        return secretCode;
    }

    public static int getRandomNumber(int lowerLimit, int upperLimit) {
        Random random = new Random();
        return random.nextInt(upperLimit - lowerLimit) + lowerLimit;
    }

}