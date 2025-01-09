package main.menus;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;

import javafx.util.Duration;

import java.util.Objects;
import java.util.logging.Logger;
import java.util.logging.Level;

public class StartMenu extends Application {

    private static final Logger LOGGER = Logger.getLogger(StartMenu.class.getName());

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Breakout");

        // Title
        Text title = new Text("Breakout");
        title.getStyleClass().addAll("title-text", "centered-text");

        //attempt at animation
        // Scaling animation
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(1.5), title);
        scaleTransition.setFromX(1);
        scaleTransition.setFromY(1);
        scaleTransition.setToX(1.2);
        scaleTransition.setToY(1.2);
        scaleTransition.setAutoReverse(true);
        scaleTransition.setCycleCount(ScaleTransition.INDEFINITE);

        // Combine animation
        SequentialTransition animation = new SequentialTransition(scaleTransition);
        animation.play();
        //animations over

        //select modifiers
        Button selectionButton = new Button("Play!");
        selectionButton.getStyleClass().add("play-button");
        selectionButton.setOnAction(e -> {
            try {
                SelectionMenu selectionMenu = new SelectionMenu();
                selectionMenu.start(primaryStage);
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, "An error occurred while opening SelectionMenu", ex);
            }

        });

        //battlepass
        Button battlepassButton = new Button("Battlepass");
        battlepassButton.getStyleClass().add("battlepass-button");
        battlepassButton.setOnAction(e -> {
            try {
                BattlepassMenu battlepassMenu = new BattlepassMenu();
                battlepassMenu.start(primaryStage);
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, "An error occurred while opening BattlepassMenu", ex);
            }

        });

        //highscores
        Button highscoresButton = new Button("Highscores");
        highscoresButton.getStyleClass().add("highscore-button");
        highscoresButton.setOnAction(e -> {
            try {
                HighscoresMenu highscoresMenu = new HighscoresMenu();
                highscoresMenu.start(primaryStage);
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, "An error occurred while opening HighscoresMenu", ex);
            }

        });

        //quit button
        Button quitButton = new Button("Quit Game");
        quitButton.getStyleClass().add("quit-button");
        quitButton.setOnAction(e -> System.exit(0));

        // Layout
        VBox menu = new VBox();
        menu.getStyleClass().addAll("center-aligned", "scene-background");
        menu.getChildren().addAll(title, selectionButton, battlepassButton, highscoresButton, quitButton);

        // Scene
        Scene scene = new Scene(menu, 768, 576 * 2);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/main/resources/styles.css")).toExternalForm()); //import css class

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
