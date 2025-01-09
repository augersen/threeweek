package main.menus;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.logging.Logger;
import java.util.logging.Level;

public class HighscoresMenu extends Application {

    private static final Logger LOGGER = Logger.getLogger(HighscoresMenu.class.getName());

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
        VBox highscoreColumn = new VBox(10); // Reduced spacing for better readability
        highscoreColumn.setPadding(new Insets(20));
        highscoreColumn.getStyleClass().add("center-aligned");

        // Highscore Entries as Text
        for (int i = 1; i <= 3; i++) {
            Text highscoreText = new Text(i + ". Player" + i + " - " + (1000 - i * 10) + " Points");
            highscoreText.getStyleClass().add("highscore-text");

            // Add each high score text to the column
            highscoreColumn.getChildren().add(highscoreText);
        }

        // ScrollPane for Highscore Column
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(highscoreColumn);
        scrollPane.setFitToWidth(true); // Fit width of ScrollPane
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Disable horizontal scrolling
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS); // Enable vertical scrolling
        scrollPane.getStyleClass().add("scrollpane-background");

        // Back Button
        Button backButton = new Button("Back to Menu");
        backButton.setOnAction(e -> {
            try {
                StartMenu mainMenu = new StartMenu();
                mainMenu.start(primaryStage);
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, "An error occurred while returning to the StartMenu", ex);
            }

        });
        backButton.getStyleClass().add("default-button");

        // Layout
        VBox layout = new VBox(20);
        layout.getChildren().addAll(title, content, scrollPane, backButton);
        layout.setPadding(new Insets(20));
        layout.getStyleClass().addAll("scene-background", "center-aligned");

        // Scene and Stage Setup
        Scene scene = new Scene(layout, 768, 576 * 2);
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
