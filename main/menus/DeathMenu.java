package main.menus;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.Config;
import main.GameLauncher;
import main.ScoreManager;
import main.SoundController;

import java.util.Objects;

public class DeathMenu extends Application {

    private static final String SELECTION_SOUND = "/main/resources/sounds/menuSelectSound.wav";
    private static final String DEATH_SOUND = "/main/resources/sounds/Death.wav";

    private int score;

    public DeathMenu(int score) {
        this.score = score;
    }


    public static void main(String[] args) {
        launch(args);
    }

    private void saveScoreToManager(int score) {
        ScoreManager.saveScore(score);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        if (this.score == 0) {
            throw new IllegalStateException("Score must be set before displaying the DeathMenu.");
        }
        primaryStage.setTitle("Breakout");

        // TOU DIED
        Text youDiedText = new Text("YOU DIED");
        youDiedText.getStyleClass().add("you-died-text");

        // Play background music
        SoundController.playDeathSound(DEATH_SOUND);

        Text scoreText = new Text("Score: " + score);
        scoreText.getStyleClass().addAll("content-text", "center-aligned");

        //SAVE SCORE
        saveScoreToManager(this.score);
        System.out.println("Score saved: " + this.score);

        // Center the text in the window
        StackPane root = new StackPane();
        root.getChildren().addAll(youDiedText, scoreText);
        root.setStyle("-fx-background-color: black;");

        //fade-in effect for YOU DIED
        fadeInEffect(youDiedText, 5);

        //fade-in effect for score
        fadeInEffect(scoreText, 7);

        // Start Button
        Button playAgainButton = new Button("Play again!");
        playAgainButton.getStyleClass().add("start-button");
        playAgainButton.setOnAction(e -> {
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

        //fade-in effect for playAgainButton
        fadeInEffect(playAgainButton, 9);

        //fade-in effect for backButton
        fadeInEffect(backButton, 9);

        // Layout
        VBox menu = new VBox();
        menu.getStyleClass().addAll("center-aligned", "scene-background");
        menu.getChildren().addAll(youDiedText,scoreText, playAgainButton, backButton);

        // Scene
        Scene scene = new Scene(menu, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/main/resources/styles.css")).toExternalForm()); //import css class

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.setAlwaysOnTop(true);
        primaryStage.show();
    }

    //method for easier implementation of the fade in animation/effect
    public void fadeInEffect(Node node, int duration) {
        FadeTransition fadeEffect = new FadeTransition(Duration.seconds(duration), node);
        fadeEffect.setFromValue(0);
        fadeEffect.setToValue(1);
        fadeEffect.play();
    }

}