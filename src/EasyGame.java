public class EasyGame extends HangmanGameBase {
    public EasyGame(WordProvider wp, UI ui) { super(wp, ui); }
    protected String getDifficultyName() { return "easy"; }
    protected int getAttempts() { return 10; }
}