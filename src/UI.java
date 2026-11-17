import java.util.Set;

public interface UI {
    String askDifficulty();
    char askLetter(Set<Character> guessed);
    void showGameState(String word, int attemptsLeft);
    void showResult(boolean won, String word);
    boolean askReplay();
}