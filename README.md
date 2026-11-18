# hangman-game

A Java console Hangman game with Easy, Medium and Hard difficulties. Words are loaded from a file.

> Coursework for the University of Southampton Year 1 Programming module (2024–25).

## Design

The game is split into small, swappable parts:

- `HangmanGameBase` is an abstract class holding the shared game loop: guessing, tracking attempts and checking for a win or loss.
- `EasyGame`, `MediumGame` and `HardGame` are subclasses that pick the word list and the number of wrong guesses allowed (10, 8 and 6).
- `WordProvider` is the interface for word sources, and `FileWordProvider` reads words by difficulty from `words.txt`.
- `UI` is the interface for input and output, and `ConsoleUI` is the terminal version. It asks for a difficulty and letters, shows the game state and results, and offers a replay.

With the word source and UI behind interfaces, a new front end or word list only needs a new implementation. The game logic stays the same.

## Prerequisites

- JDK 17 or newer (uses switch expressions; tested with JDK 21). Check with `java -version` and `javac -version`.
- On Windows, install one with `winget install --id EclipseAdoptium.Temurin.21.JDK -e`.

No external libraries, build tool or API keys are needed.

## Run

From this folder (so `words.txt` can be found), in PowerShell or Git Bash:

```bash
javac -d out src/*.java
java -cp out Main
```

Choose a difficulty (`easy`, `medium`, `hard`), guess one letter at a time, and answer `yes` or `no` to play again.

## Test

There are no unit tests. A non-interactive smoke test in Git Bash pipes a full game's input and should end with `You won!` or `You lost!`:

```bash
javac -d out src/*.java
{ echo easy; for l in e a r i o t n s l c u d p m h g b f y w k v x z j q; do echo $l; done; echo no; } | java -cp out Main
```

Compiled classes go to `out/`, which is gitignored.
