package main.menus;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.Config;
import main.ScoreManager;
import main.SoundController;

import java.util.*;

public class HighscoresMenu extends Application {

    private static final String SELECTION_SOUND = "/main/resources/sounds/menuSelectSound.wav";

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Breakout");

        // Title
        Text title = new Text("Highscores");
        title.getStyleClass().add("title-text");

        // Description
        Text content = new Text("Take a look at the best performers!");
        content.getStyleClass().addAll("content-text");

        // Highscore Column
        VBox highscoreColumn = new VBox(10);
        highscoreColumn.setPadding(new Insets(20));
        highscoreColumn.getStyleClass().addAll("center-aligned", "VBox-background");

        // Load and display scores
        List<Integer> scores = ScoreManager.readScores();
        for (int i = 0; i < scores.size(); i++) {
            Text highscoreText = new Text((i + 1) + ". Score: " + scores.get(i));
            highscoreText.getStyleClass().add("highscore-text");
            highscoreColumn.getChildren().add(highscoreText);
        }

        //title for regular game highscores
        Text regularTitle = new Text("Best performances without modifiers!");
        regularTitle.getStyleClass().add("highscore-title-text");

        // Back Button
        Button backButton = new Button("Back to Menu");
        backButton.setOnAction(e -> {
            SoundController.playMenuSelectSound(SELECTION_SOUND);
            try {
                StartMenu mainMenu = new StartMenu();
                mainMenu.start(primaryStage);
            } catch (Exception ex) {
                System.out.println("An error occurred while returning to the StartMenu");
            }
        });
        backButton.getStyleClass().add("default-button");

        // Layout
        VBox layout = new VBox(20);
        layout.getChildren().addAll(title, content,regularTitle, highscoreColumn, backButton);
        layout.setPadding(new Insets(20));
        layout.getStyleClass().addAll("scene-background", "center-aligned");

        // Scene and Stage Setup
        Scene scene = new Scene(layout, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/main/resources/styles.css")).toExternalForm()); //import css class
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }


}