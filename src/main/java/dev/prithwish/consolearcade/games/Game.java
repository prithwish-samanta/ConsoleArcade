package dev.prithwish.consolearcade.games;

import java.util.Scanner;

public class Game {
    protected Scanner scanner = new Scanner(System.in);

    // Start the game after confirmation
    public boolean startGame() {
        System.out.println("Enter 'S' to start or 'E' to exit the game: ");
        String input = scanner.nextLine().trim().toLowerCase();
        return input.equals("s") || input.equals("start");
    }
}
