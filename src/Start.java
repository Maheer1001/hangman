import java.util.*;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Start {
    private final Scanner scanner;
    private List<String> easyBank = new ArrayList<>();
    private List<String> mediumBank = new ArrayList<>();
    private List<String> hardBank = new ArrayList<>();
    private static String hangmanWord;
    private HashSet<Character> guessedLetters = new HashSet<>();
    private int attempts;

    public Start(Scanner scanner) {
        this.scanner = scanner;
    }

    public void readWords() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("words.txt"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length < 2) {
                    System.out.println("Skipping invalid line: " + line);
                    continue;
                }
                String word = parts[0];
                String difficulty = parts[1].toLowerCase();

                switch (difficulty) {
                    case "easy" -> easyBank.add(word);
                    case "medium" -> mediumBank.add(word);
                    case "hard" -> hardBank.add(word);
                    default -> System.out.println("Skipping unknown difficulty: " + difficulty);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    public String difficultySelector() {
        System.out.println("\n" + "Welcome to Hangman");
        System.out.print("Select your preferred difficulty (easy / medium / hard): ");
        String choice = scanner.nextLine().toLowerCase();
        while (!List.of("easy", "medium", "hard").contains(choice)) {
            System.out.println("Invalid choice! ");
            System.out.print("Select your preferred difficulty (easy / medium / hard): ");
            choice = scanner.nextLine().toLowerCase();
        }
        System.out.println("You have chosen the " + choice + " difficulty");
        return choice;
    }

    private String getCurrentGuess() {
        StringBuilder guess = new StringBuilder();
        for (char letter : hangmanWord.toCharArray()) {
            guess.append(guessedLetters.contains(letter) ? letter : "_");
        }
        return guess.toString();
    }

    public void wordSelector(String difficulty) {
        Random random = new Random();
        Map<String, List<String>> wordBank = Map.of("easy", easyBank, "medium", mediumBank, "hard", hardBank);
        Map<String, Integer> difficultyAttempts = Map.of("easy", 10, "medium", 8, "hard", 6);
        List<String> selectedBank = wordBank.get(difficulty);
        hangmanWord = selectedBank.get(random.nextInt(selectedBank.size())).toLowerCase();
        attempts = difficultyAttempts.get(difficulty);
        System.out.println("\nYour word is: " + getCurrentGuess() + "\n");
    }

    public void guessingGame() {
        while (!getCurrentGuess().equals(hangmanWord) && attempts > 0) {
            char guessedLetter;
            while (true) {
                System.out.print("Enter a single letter (A-Z or a-z): ");
                String input = scanner.nextLine().toLowerCase();
                if (input.length() == 1 && Character.isLetter(input.charAt(0))) {
                    guessedLetter = input.charAt(0);
                    break;
                }
                System.out.println("Invalid input. Please enter only ONE letter (A-Z or a-z).");
            }

            if (!guessedLetters.add(guessedLetter)) {
                System.out.println("You have already guessed this!");
            } else if (hangmanWord.contains(String.valueOf(guessedLetter))) {
                System.out.println("Correct!");
            } else {
                System.out.println("Incorrect!");
                attempts--;
            }

            System.out.println("Your word is: " + getCurrentGuess());
            System.out.println("Attempts left: " + attempts + "\n");
        }

        System.out.println("The word was " + hangmanWord);
        System.out.println(getCurrentGuess().equals(hangmanWord) ? "You win!" : "You lose!");
        System.out.println();
    }

    public boolean repeatTry() {
        System.out.print("Do you want to play again? (yes/no): ");
        if (scanner.nextLine().equalsIgnoreCase("no")) {
            System.out.println("Thank you for playing! Goodbye.");
            return false;
        }
        guessedLetters.clear();
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Start game = new Start(scanner);
            game.readWords();
            game.wordSelector(game.difficultySelector());
            game.guessingGame();
            if (!game.repeatTry()) break;
        }
        scanner.close();
    }
}