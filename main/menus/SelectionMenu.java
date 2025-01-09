package main.menus;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.Main;

import java.util.logging.Logger;
import java.util.logging.Level;

public class SelectionMenu extends Application {

    private static final Logger LOGGER = Logger.getLogger(HighscoresMenu.class.getName());

    @Override
    public void start(Stage primaryStage)  {
        primaryStage.setTitle("Breakout");

        //Breakout title
        Text title = new Text("Select your modifiers!");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        title.setFill(new LinearGradient(
                0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.RED),
                new Stop(0.5, Color.PINK),
                new Stop(1, Color.RED)
        ));
        //Start button
        Button startButton = new Button("Start Game");
        startButton.setOnAction(e -> startGame(primaryStage));
        startButton.setStyle("-fx-font-size: 20; -fx-background-color: GREEN;");

        // Back Button
        Button backButton = new Button("Back to Menu");
        backButton.setOnAction(e -> {
            StartMenu mainMenu = new StartMenu();
            try {
                mainMenu.start(primaryStage);
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, "An error occurred while returning to the StartMenu", ex);
            }
        });
        backButton.setStyle("-fx-font-size: 16; -fx-background-color: DARKGRAY;");

        //Layout
        VBox menu = new VBox(20);
        menu.getChildren().addAll(title, startButton, backButton);
        menu.setStyle("-fx-alignment: center; -fx-background-color: black;");

        Scene scene = new Scene(menu, 768, 576*2);

        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.setAlwaysOnTop(true);
    }

    private void startGame(Stage primaryStage) {
        Main.startGame();
        primaryStage.close();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
