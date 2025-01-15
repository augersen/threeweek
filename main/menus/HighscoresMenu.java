package main.menus;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.Config;
import main.Modifiers;
import main.ScoreManager;
import main.SoundController;

import java.util.List;
import java.util.Objects;

public class HighscoresMenu extends Application {

    private static final String SELECTION_SOUND = "/main/resources/sounds/menuSelectSound.wav";



    public HighscoresMenu() {}

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Breakout");

        // Title
        Text title = new Text("Highscores");
        title.getStyleClass().add("title-text");

        // Description
        Text content = new Text("Take a look at the best performers!");
        content.getStyleClass().addAll("content-text");

        // VBox for all highscore sections
        VBox highscoreSections = new VBox(20);
        highscoreSections.setPadding(new Insets(20));

        // Load and display scores for the regular game (No Modifiers)
        addHighscoreSection(highscoreSections, "Best performances without modifiers!", "NoModifier");

        // Load and display scores for the first modifier
        addHighscoreSection(highscoreSections, "Best performances with ExampleModifier!", "ExampleModifier");

        // Load and display scores for the second modifier
        addHighscoreSection(highscoreSections, "Best performances with PlatformModifier!", "PlatformModifier");

        // Load and display scores for third modifier
        addHighscoreSection(highscoreSections, "Best performances with PowerupModifier!", "PowerupModifier");

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
        layout.getChildren().addAll(title, content, highscoreSections, backButton);
        layout.setPadding(new Insets(20));
        layout.getStyleClass().addAll("scene-background", "center-aligned");

        // Scene and Stage Setup
        Scene scene = new Scene(layout, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/main/resources/styles.css")).toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    private void addHighscoreSection(VBox container, String sectionTitle, String modifier) {
        // Title for the section
        Text title = new Text(sectionTitle);
        title.getStyleClass().addAll("highscore-title-text", "center-aligned");

        // Highscore Column
        VBox highscoreColumn = new VBox(10);
        highscoreColumn.setPadding(new Insets(10));
        highscoreColumn.getStyleClass().addAll("VBox-background", "center-aligned");

        // Load scores for the modifier
        List<Integer> scores = ScoreManager.readScores(modifier);
        if (scores.isEmpty()) {
            Text noScoresText = new Text("No scores yet!");
            noScoresText.getStyleClass().addAll("highscore-text", "center-aligned");
            highscoreColumn.getChildren().add(noScoresText);
        } else {
            for (int i = 0; i < scores.size(); i++) {
                Text highscoreText = new Text((i + 1) + ". Score: " + scores.get(i));
                highscoreText.getStyleClass().addAll("highscore-text", "center-aligned");
                highscoreColumn.getChildren().add(highscoreText);
            }
        }

        // Add the section title and highscore column to the container
        container.getStyleClass().add("center-aligned");
        container.getChildren().addAll(title, highscoreColumn);
    }


}
