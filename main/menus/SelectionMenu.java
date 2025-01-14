package main.menus;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import main.Config;
import main.GameLauncher;
import main.SoundController;

import java.util.Objects;

public class SelectionMenu extends Application {

    private static final String SELECTION_SOUND = "/main/resources/sounds/menuSelectSound.wav";

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Breakout");

        // Title
        Text title = new Text("Select your modifiers!");
        title.getStyleClass().addAll("title-text", "centered-text");

        // Start Button
        Button startButton = new Button("Start Game");
        startButton.getStyleClass().add("start-button");
        startButton.setOnAction(e -> {
            SoundController.playMenuSelectSound(SELECTION_SOUND);
            SoundController.stopBackgroundMusic();
            try {
                new GameLauncher().start(new Stage()); // Launch the game
                primaryStage.close(); // Close the current menu
            } catch (Exception ex) {
                System.out.println("An error occurred while launching the game");
                ex.printStackTrace();
            }
        });

        // Checkboxes
        VBox checkboxRow = new VBox(20);
        checkboxRow.getStyleClass().add("center-aligned");
        for (int i = 1; i <= 6; i++) {
            CheckBox checkBox = new CheckBox("Modifier: " + i);
            checkBox.getStyleClass().add("check-box");
            checkboxRow.getChildren().add(checkBox);

            int finalI = i;
            checkBox.setOnAction(e -> {
                if (checkBox.isSelected()) {
                    switch (finalI) {
                        case 1 -> exampleModifier();
                        case 2 -> System.out.println(1);
                        case 3 -> System.out.println(2);
                        case 4 -> System.out.println(3);
                        case 5 -> System.out.println(4);
                        case 6 -> System.out.println(5);
                        default -> System.out.println("Unknown modifier activated");
                    }
                } else {
                    switch (finalI) {
                        case 1 -> disableExampleModifier();
                        case 2 -> System.out.println(5);
                        case 3 -> System.out.println(4);
                        case 4 -> System.out.println(3);
                        case 5 -> System.out.println(2);
                        case 6 -> System.out.println(1);
                        default -> System.out.println("Unknown modifier deactivated");
                    }
                }
            });

        }

        // Back Button
        Button backButton = new Button("Back to Menu");
        backButton.getStyleClass().add("default-button");
        backButton.setOnAction(e -> {
            SoundController.playMenuSelectSound(SELECTION_SOUND);
            try {
                StartMenu mainMenu = new StartMenu();
                mainMenu.start(primaryStage);
            } catch (Exception ex) {
                System.out.println("An error occurred while returning to the StartMenu");
            }

        });

        // Layout
        VBox layout = new VBox(20);
        layout.getChildren().addAll(title, startButton, checkboxRow, backButton);
        layout.getStyleClass().addAll("center-aligned", "scene-background");

        // Scene
        Scene scene = new Scene(layout, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/main/resources/styles.css")).toExternalForm()); //import css class

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void exampleModifier() {
        System.out.println("Example modifier");
    }
    public void disableExampleModifier() {
        System.out.println("Disable example modifier");
    }

}