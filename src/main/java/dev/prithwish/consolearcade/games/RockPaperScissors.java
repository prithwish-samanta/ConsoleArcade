package dev.prithwish.consolearcade.games;

import dev.prithwish.consolearcade.constants.GameStates;

import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {
    private final Scanner scanner;

    public RockPaperScissors() {
        this.scanner = new Scanner(System.in);
    }

    // Method to display the rules of the game
    public void showRules() {
        System.out.println("Welcome to Rock, Paper, Scissors!");
        System.out.println("Rules:");
        System.out.println("1. This is a two-player game (you vs. the computer).");
        System.out.println("2. Both players select one of the following options:");
        System.out.println("   - Rock (R)");
        System.out.println("   - Paper (P)");
        System.out.println("   - Scissors (S)");
        System.out.println("3. The winner is decided based on the following conditions:");
        System.out.println("   - Rock beats Scissors.");
        System.out.println("   - Scissors beats Paper.");
        System.out.println("   - Paper beats Rock.");
        System.out.println("4. If both players choose the same option, it's a draw.");
        System.out.println();
        System.out.println("How to Play:");
        System.out.println("1. You will be prompted to choose 'R' for Rock, 'P' for Paper, or 'S' for Scissors.");
        System.out.println("2. The computer will randomly select its move.");
        System.out.println("3. The winner will be determined based on the choices made.");
        System.out.println("Good luck!");
    }

    // Start the game after confirmation
    public boolean startGame() {
        System.out.println("Enter 'S' to start or 'E' to exit the game: ");
        String input = scanner.nextLine().trim().toLowerCase();
        return input.equals("s") || input.equals("start");
    }

    // Method to convert 'R', 'P', 'S' to full names
    public String getFullName(char choice) {
        switch (choice) {
            case 'R':
                return GameStates.ROCK;
            case 'P':
                return GameStates.PAPER;
            case 'S':
                return GameStates.SCISSORS;
            default:
                return "Invalid";
        }
    }

    // Method to take the user's choice
    public String getUserChoice() {
        String userChoice;
        while (true) {
            System.out.println("Enter your choice: 'R' for Rock, 'P' for Paper, 'S' for Scissors");
            String input = scanner.nextLine().toUpperCase();
            if (input.length() == 1 && (input.charAt(0) == 'R' || input.charAt(0) == 'P' || input.charAt(0) == 'S')) {
                userChoice = getFullName(input.charAt(0));
                break;
            } else {
                System.out.println("Invalid input! Please enter 'R', 'P', or 'S'.");
            }
        }
        return userChoice;
    }

    // Method to randomly select a move for the computer
    public String getComputerChoice() {
        Random random = new Random();
        int choice = random.nextInt(3);
        switch (choice) {
            case 0:
                return GameStates.ROCK;
            case 1:
                return GameStates.PAPER;
            case 2:
                return GameStates.SCISSORS;
            default:
                return "Invalid";
        }
    }

    // Method to determine the winner
    public void determineWinner(String userChoice, String computerChoice) {
        if (userChoice.equals(computerChoice)) {
            System.out.println("IT'S A DRAW!");
        } else if ((GameStates.ROCK.equals(userChoice) && GameStates.SCISSORS.equals(computerChoice)) ||
                (GameStates.SCISSORS.equals(userChoice) && GameStates.PAPER.equals(computerChoice)) ||
                (GameStates.PAPER.equals(userChoice) && GameStates.ROCK.equals(computerChoice))) {
            System.out.println("YOU WIN!");
        } else {
            System.out.println("COMPUTER WINS!");
        }
    }

    public void run() {
        showRules();
        if (startGame()) {
            String userChoice = getUserChoice();
            String computerChoice = getComputerChoice();
            System.out.println("You selected: " + userChoice);
            System.out.println("Computer selected: " + computerChoice);
            determineWinner(userChoice, computerChoice);
        } else {
            System.out.println("Explore other games.");
        }
    }
}
