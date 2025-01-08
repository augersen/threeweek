package main.menus;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.logging.Logger;
import java.util.logging.Level;


public class HighscoresMenu extends Application {

    private static final Logger LOGGER = Logger.getLogger(HighscoresMenu.class.getName());

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Breakout");

        // Title
        Text title = new Text("Highscores");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        title.setFill(new LinearGradient(
                0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.RED),
                new Stop(0.5, Color.ORANGE),
                new Stop(1, Color.YELLOW)
        ));

        // Description
        Text content = new Text("Take a look at the best performers!");
        content.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        content.setFill(Color.WHITE);

        // Highscore Column
        VBox highscoreColumn = new VBox(10); // Reduced spacing for better readability
        highscoreColumn.setPadding(new Insets(20));
        highscoreColumn.setStyle("-fx-alignment: center;");

        // Generate 20 Highscore Entries as Text
        for (int i = 1; i <= 20; i++) {
            Text highscoreText = new Text(i + ". Player" + i + " - " + (1000 - i * 10) + " Points");
            highscoreText.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
            highscoreText.setFill(new LinearGradient(
                    0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
                    new Stop(0, Color.GOLD),
                    new Stop(0.5, Color.SILVER),
                    new Stop(1, Color.PALEGREEN)
            ));

            // Add each high score text to the column
            highscoreColumn.getChildren().add(highscoreText);
        }

        // ScrollPane for Highscore Column
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(highscoreColumn);
        scrollPane.setFitToWidth(true); // Fit width of ScrollPane
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Disable horizontal scrolling
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS); // Enable vertical scrolling
        scrollPane.setStyle("-fx-background: #000000;");

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

        // Layout
        VBox layout = new VBox(20);
        layout.getChildren().addAll(title, content, scrollPane, backButton);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-alignment: center; -fx-background-color: black;");

        // Scene and Stage Setup
        Scene scene = new Scene(layout, 768, 576 * 2);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
