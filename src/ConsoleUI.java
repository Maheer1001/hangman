import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class ConsoleUI implements UI {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String askDifficulty() {
        System.out.println("\n" + "Welcome to Hangman");
        System.out.print("Select difficulty (easy, medium, hard): ");
        String input = scanner.nextLine().toLowerCase();
        while (!List.of("easy", "medium", "hard").contains(input)) {
            System.out.print("Invalid. Please choose easy, medium, or hard: ");
            input = scanner.nextLine().toLowerCase();
        }
        return input;
    }

    @Override
    public char askLetter(Set<Character> guessed) {
        System.out.print("Guess a single letter (A-Z or a-z): ");
        String input = scanner.nextLine().toLowerCase();
        while (input.length() != 1 || !Character.isLetter(input.charAt(0)) || guessed.contains(input.charAt(0))) {
            System.out.print("Invalid or You have already guessed this! Try again: ");
            input = scanner.nextLine().toLowerCase();
        }
        return input.charAt(0);
    }

    @Override
    public void showGameState(String word, int attemptsLeft) {
        System.out.println("\n" + "Your word is: " + word);
        System.out.println("Attempts left: " + attemptsLeft + "\n");
    }

    @Override
    public void showResult(boolean won, String word) {
        System.out.println(won ? "\n" + "You won!" : "\n" + "You lost!");
        System.out.println("The word was: " + word);
    }

    @Override
    public boolean askReplay() {
        System.out.print("Play again? (yes/no): ");
        return scanner.nextLine().equalsIgnoreCase("yes");
    }
}