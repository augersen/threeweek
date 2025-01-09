package main;

import javafx.application.Application;
import main.menus.StartMenu;

import javax.swing.JFrame;

public class testMain {
    public static void main(String[] args){
        startGame();

        //Startmenu

    }

    public static void startGame(){ //Will later become main, this is temporary for testing purposes.
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Breakout");

        View2D GameWindow = new View2D();
        window.add(GameWindow);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        GameWindow.startGameThread();
    }
}