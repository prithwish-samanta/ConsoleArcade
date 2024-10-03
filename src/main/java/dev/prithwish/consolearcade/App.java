package dev.prithwish.consolearcade;

import dev.prithwish.consolearcade.games.RockPaperScissors;
import dev.prithwish.consolearcade.games.TicTacToe;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        System.out.println("Welcome to ConsoleArcade");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Available Games");
            System.out.println("1. Tic Tac Toe");
            System.out.println("2. Rock Paper Scissors");
            System.out.println("Enter your choice: ");
            String choice = scanner.nextLine().trim();
            if ("1".equals(choice)) {
                TicTacToe ticTacToe = new TicTacToe();
                ticTacToe.run();
                break;
            } else if ("2".equals(choice)) {
                RockPaperScissors rockPaperScissors = new RockPaperScissors();
                rockPaperScissors.run();
                break;
            }
            else {
                System.out.println("Invalid choice");
            }
        }
    }
}
