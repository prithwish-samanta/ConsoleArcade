package dev.prithwish.consolearcade.games;

import dev.prithwish.consolearcade.constants.GameStates;

import java.util.Arrays;
import java.util.Random;

public class Hangman extends Game {
    private int remainingAttempts;

    public Hangman() {
        remainingAttempts = GameStates.HANGMAN_MAX_ATTEMPTS;
    }

    // Method to display the rules of the game
    public void showRules() {
        System.out.println("Welcome to Hangman!");
        System.out.println("Rules:");
        System.out.println("1. A random word will be chosen, and you must guess it letter by letter.");
        System.out.println("2. You have " + remainingAttempts + " attempts to guess the correct letters.");
        System.out.println("3. Each time you guess a letter, if it's in the word, it will be revealed.");
        System.out.println("4. If your guess is wrong, you lose one attempt.");
        System.out.println("5. If you guess the word before using all attempts, you win.");
        System.out.println("6. If you run out of attempts, you lose, and the correct word will be revealed.");
        System.out.println();
        System.out.println("Let's start guessing!");
        System.out.println("Good luck!");
    }

    // Method to choose a random word from the list of words
    private String chooseRandomWord() {
        Random rand = new Random();
        return GameStates.HANGMAN_WORDS[rand.nextInt(GameStates.HANGMAN_WORDS.length)];
    }

    // Method to reveal a few random letters at the start of the game
    private char[] revealInitialGuessesWord(String chosenWord) {
        char[] guessedWord = new char[chosenWord.length()];
        Arrays.fill(guessedWord, '_');
        Random rand = new Random();
        int lettersToReveal = Math.max(1, chosenWord.length() / 3);
        for (int i = 0; i < lettersToReveal; i++) {
            int randomIndex;
            do {
                randomIndex = rand.nextInt(chosenWord.length());
            } while (guessedWord[randomIndex] != '_');
            guessedWord[randomIndex] = chosenWord.charAt(randomIndex);
        }
        return guessedWord;
    }

    // Check if the game is over
    public boolean isGameOver(String chosenWord, char[] guessedWord) {
        if (remainingAttempts <= 0) {
            System.out.println("GAME OVER! THE WORD WAS: " + chosenWord.toLowerCase());
            return true;
        }
        if (String.valueOf(guessedWord).equals(chosenWord)) {
            System.out.println("CONGRATULATIONS! THE WORD WAS: " + chosenWord.toUpperCase());
            return true;
        }
        return false;
    }

    // Method to show the current state of the guessed word
    public void showGuessedWord(char[] guessedWord) {
        System.out.println("Word: " + String.valueOf(guessedWord).toUpperCase());
    }

    // Method to take user input and handle guess validation
    public void takeUserInput(String chosenWord, char[] guessedWord) {
        System.out.print("Enter a letter: ");
        String input = scanner.nextLine().toLowerCase();
        if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
            System.out.println("Invalid input. Please enter a single letter.");
            return;
        }
        char guessedLetter = input.charAt(0);
        if (chosenWord.contains(String.valueOf(guessedLetter))) {
            boolean correctGuess = false;
            for (int i = 0; i < chosenWord.length(); i++) {
                if (chosenWord.charAt(i) == guessedLetter && guessedWord[i] == '_') {
                    guessedWord[i] = guessedLetter;
                    correctGuess = true;
                }
            }
            if(correctGuess){
                System.out.println("Good guess!");
            }
            else{
                System.out.println("You already guessed that letter.");
            }
        }
        else{
            remainingAttempts--;
            System.out.println("Wrong guess! Remaining attempts: " + remainingAttempts);
        }
    }

    public void run() {
        showRules();
        if (startGame()) {
            String chosenWord = chooseRandomWord();
            char[] guessedWord = revealInitialGuessesWord(chosenWord);
            while (!isGameOver(chosenWord, guessedWord)) {
                showGuessedWord(guessedWord);
                takeUserInput(chosenWord, guessedWord);
            }
        } else {
            System.out.println("Explore other games.");
        }
    }
}
