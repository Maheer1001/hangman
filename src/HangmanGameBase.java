import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public abstract class HangmanGameBase {
    protected final WordProvider wordProvider;
    protected final UI ui;
    protected String wordToGuess;
    protected final Set<Character> guessedLetters = new HashSet<>();
    protected int attempts;

    public HangmanGameBase(WordProvider wordProvider, UI ui) {
        this.wordProvider = wordProvider;
        this.ui = ui;
    }

    protected abstract String getDifficultyName();
    protected abstract int getAttempts();

    public void start() {
        guessedLetters.clear();
        List<String> words = wordProvider.getWords(getDifficultyName());
        wordToGuess = words.get(new Random().nextInt(words.size()));
        attempts = getAttempts();
        play();
    }

    private void play() {
        while (!getMaskedWord().equals(wordToGuess) && attempts > 0) {
            ui.showGameState(getMaskedWord(), attempts);
            char guess = ui.askLetter(guessedLetters);
            if (!guessedLetters.add(guess)) {
                System.out.println("You have already guessed this!");
            } else if (wordToGuess.contains(String.valueOf(guess))) {
                System.out.println("Correct!");
            } else {
                System.out.println("Incorrect!");
                attempts--;
            }
            guessedLetters.add(guess);
        }
        ui.showResult(getMaskedWord().equals(wordToGuess), wordToGuess);
    }

    private String getMaskedWord() {
        StringBuilder sb = new StringBuilder();
        for (char c : wordToGuess.toCharArray()) {
            sb.append(guessedLetters.contains(c) ? c : '_');
        }
        return sb.toString();
    }
}