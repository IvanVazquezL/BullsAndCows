package FirstGuess;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BullsAndCows game = new BullsAndCows("9305");
        game.Run();
    }
}

class BullsAndCows {
    private final Scanner scanner = new Scanner(System.in);
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

}