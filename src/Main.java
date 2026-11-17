public class Main {
    public static void main(String[] args) {
        WordProvider wordProvider = new FileWordProvider("words.txt");
        UI ui = new ConsoleUI();

        while (true) {
            String difficulty = ui.askDifficulty();
            HangmanGameBase game = switch (difficulty) {
                case "easy" -> new EasyGame(wordProvider, ui);
                case "medium" -> new MediumGame(wordProvider, ui);
                case "hard" -> new HardGame(wordProvider, ui);
                default -> throw new IllegalArgumentException("Invalid difficulty: " + difficulty);
            };
            game.start();
            if (!ui.askReplay()) break;
        }
    }
}