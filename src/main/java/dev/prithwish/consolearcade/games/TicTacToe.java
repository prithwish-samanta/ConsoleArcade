package dev.prithwish.consolearcade.games;

import dev.prithwish.consolearcade.constants.GameStates;

import java.util.Arrays;
import java.util.Scanner;

public class TicTacToe {
    private final Scanner scanner;
    private final char[] grid;

    public TicTacToe() {
        this.scanner = new Scanner(System.in);
        this.grid = new char[]{'-', '-', '-', '-', '-', '-', '-', '-', '-'};
    }

    // Method to display the rules of the game
    public void showRules() {
        System.out.println("Welcome to Tic-Tac-Toe!");
        System.out.println("Rules:");
        System.out.println("1. The game is played on a 3x3 grid.");
        System.out.println("2. Players take turns marking a square with either 'X' or 'O'.");
        System.out.println("3. The first player to get 3 of their marks in a horizontal, vertical, or diagonal row wins.");
        System.out.println("4. If all 9 squares are filled and no player has 3 in a row, the game is a draw.");
        System.out.println();
        System.out.println("Here is the grid with numbered positions:");
        System.out.println();
        System.out.println(" 1 | 2 | 3 ");
        System.out.println("-----------");
        System.out.println(" 4 | 5 | 6 ");
        System.out.println("-----------");
        System.out.println(" 7 | 8 | 9 ");
        System.out.println();
        System.out.println("Use these numbers to select the position where you'd like to place your mark.");
    }

    // Start the game after confirmation
    public boolean startGame() {
        System.out.print("Enter 'S' to start or 'E' to exit the game: ");
        String input = scanner.nextLine().trim().toLowerCase();
        return input.equals("s") || input.equals("start");
    }

    // Method to display the current state of the grid
    public void showGrid() {
        System.out.println();
        System.out.println(" " + grid[0] + " | " + grid[1] + " | " + grid[2] + " ");
        System.out.println("-----------");
        System.out.println(" " + grid[3] + " | " + grid[4] + " | " + grid[5] + " ");
        System.out.println("-----------");
        System.out.println(" " + grid[6] + " | " + grid[7] + " | " + grid[8] + " ");
        System.out.println();
    }

    // Get the available grid positions
    public char[] getRemainingGridNo() {
        StringBuilder remainingBoxes = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            if (grid[i] == '-') {
                remainingBoxes.append(i + 1);
            }
        }
        return remainingBoxes.toString().toCharArray();
    }

    // Method to handle user input for marking a square
    public void takeUserInput(boolean isPlayer1) {
        while (true) {
            System.out.println(isPlayer1 ? "Player 1: 'X'" : "Player 2: 'O'");
            char[] remaining = getRemainingGridNo();
            System.out.print("Enter the grid number: (remaining: " + Arrays.toString(remaining) + ") ");
            try {
                int idx = Integer.parseInt(scanner.nextLine().trim());
                if (idx < 1 || idx > 9 || grid[idx - 1] != '-') {
                    System.out.println("Invalid input or grid already filled. Try again.");
                } else {
                    grid[--idx] = isPlayer1 ? 'X' : 'O';
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    // Method to check if the game is over (win/draw)
    public String isGameOver() {
        int[][] winningCombinations = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // Horizontal
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // Vertical
                {0, 4, 8}, {2, 4, 6}             // Diagonal
        };

        for (int[] combo : winningCombinations) {
            if (grid[combo[0]] != '-' && grid[combo[0]] == grid[combo[1]] && grid[combo[1]] == grid[combo[2]]) {
                return grid[combo[0]] == 'X' ? GameStates.TIC_TAC_TOE_PLAYER1_WINS : GameStates.TIC_TAC_TOE_PLAYER2_WINS;
            }
        }

        // Check if there are any empty cells left
        boolean isDraw = true;
        for (char c : grid) {
            if (c == '-') {
                isDraw = false;
                break;
            }
        }
        return isDraw ? GameStates.TIC_TAC_TOE_DRAW : GameStates.TIC_TAC_TOE_NOT_FINISHED;
    }

    // Main loop to run the game
    public void run() {
        showRules();
        if (startGame()) {
            showGrid();
            boolean isPlayer1 = true;
            while (true) {
                takeUserInput(isPlayer1);
                showGrid();
                String state = isGameOver();
                switch (state) {
                    case GameStates.TIC_TAC_TOE_PLAYER1_WINS:
                        System.out.println("PLAYER 1 (X) WINS!");
                        return;
                    case GameStates.TIC_TAC_TOE_PLAYER2_WINS:
                        System.out.println("PLAYER 2 (O) WINS!");
                        return;
                    case GameStates.TIC_TAC_TOE_DRAW:
                        System.out.println("MATCH TIED!");
                        return;
                    default:
                        isPlayer1 = !isPlayer1; // Switch player turns
                }
            }
        } else {
            System.out.println("Explore other games.");
        }
    }
}
