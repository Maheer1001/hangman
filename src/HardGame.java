public class HardGame extends HangmanGameBase {
    public HardGame(WordProvider wp, UI ui) { super(wp, ui); }
    protected String getDifficultyName() { return "hard"; }
    protected int getAttempts() { return 6; }
}