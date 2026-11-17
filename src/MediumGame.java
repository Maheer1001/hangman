public class MediumGame extends HangmanGameBase {
    public MediumGame(WordProvider wp, UI ui) { super(wp, ui); }
    protected String getDifficultyName() { return "medium"; }
    protected int getAttempts() { return 8; }
}