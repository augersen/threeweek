package main.menus;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.Main;

import java.util.Objects;
import java.util.logging.Logger;
import java.util.logging.Level;

public class SelectionMenu extends Application {

    private static final Logger LOGGER = Logger.getLogger(SelectionMenu.class.getName());

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Breakout");

        // Title
        Text title = new Text("Select your modifiers!");
        title.getStyleClass().addAll("title-text", "centered-text");

        // Start Button
        Button startButton = new Button("Start Game");
        startButton.getStyleClass().add("start-button");
        startButton.setOnAction(e -> startGame(primaryStage));

        // Checkboxes
        VBox checkboxRow = new VBox(20);
        checkboxRow.getStyleClass().add("center-aligned");
        for (int i = 1; i <= 6; i++) {
            CheckBox checkBox = new CheckBox("Modifier: " + i);
            checkBox.getStyleClass().add("check-box");
            checkboxRow.getChildren().add(checkBox);
        }


        // Back Button
        Button backButton = new Button("Back to Menu");
        backButton.getStyleClass().add("default-button");
        backButton.setOnAction(e -> {
            try {
                StartMenu mainMenu = new StartMenu();
                mainMenu.start(primaryStage);
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, "An error occurred while returning to the StartMenu", ex);
            }

        });

        // Layout
        VBox layout = new VBox(20);
        layout.getChildren().addAll(title, startButton, checkboxRow, backButton);
        layout.getStyleClass().addAll("center-aligned", "scene-background");

        // Scene
        Scene scene = new Scene(layout, 768, 576 * 2);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/main/resources/styles.css")).toExternalForm()); //import css class

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void startGame(Stage primaryStage) {
        Main.startGame();
        primaryStage.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
