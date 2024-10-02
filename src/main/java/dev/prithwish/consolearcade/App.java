package dev.prithwish.consolearcade;

import dev.prithwish.consolearcade.games.TicTacToe;

public class App {
    public static void main(String[] args) {
        System.out.println("Welcome to ConsoleArcade");
        TicTacToe game = new TicTacToe();
        game.run();
    }
}
