package main;

import javafx.application.Application;
import main.menus.StartMenu;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        // startGame();
        // Startmenu
        Application.launch(StartMenu.class, args);
    }

    public static void startGame(int height, int length) { // Will later become main, this is temporary for testing purposes.
        //Sets up the frame that the game is encompassed in.
        JFrame window = new JFrame();
        //Makes sure that frame has a closing function (press the big red x.)
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Window cannot be resized, would mess with game.
        window.setResizable(false);
        //Title is set to breakout.
        window.setTitle("Breakout");

        //Creates the view object that takes care of the core gameplay.
        View2D GameWindow = new View2D(height, length);
        //Adds window in the frame.
        window.add(GameWindow);

        //Packs it all together.
        window.pack();

        //Should be in the middle of monitor.
        window.setLocationRelativeTo(null);
        //Is visible
        window.setVisible(true);

        //Starts the gamethread. Only ended when player dies.
        GameWindow.startGameThread();
    }
}