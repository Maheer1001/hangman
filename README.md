# Hangman (Java)

A console Hangman game with Easy, Medium and Hard difficulties. Words are loaded from a file.

> Coursework for the University of Southampton Year 1 Programming module (2024–25).

## Design

The game is split into small, swappable parts:

- `HangmanGameBase` is an abstract class holding the shared game loop: guessing, tracking attempts and checking for a win or loss.
- `EasyGame`, `MediumGame` and `HardGame` are subclasses that pick the word list and the number of wrong guesses allowed (10, 8 and 6).
- `WordProvider` is the interface for word sources, and `FileWordProvider` reads words by difficulty from `words.txt`.
- `UI` is the interface for input and output, and `ConsoleUI` is the terminal version. It asks for a difficulty and letters, shows the game state and results, and offers a replay.

With the word source and UI behind interfaces, a new front end or word list only needs a new implementation. The game logic stays the same.

## Run

```bash
javac -d out src/*.java
java -cp out Main
```

Run it from this folder so `words.txt` can be found.
