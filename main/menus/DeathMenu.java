package main.menus;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.Config;
import main.Main;
import main.SoundController;
import java.util.Objects;



public class DeathMenu extends Application {

    private static final String SELECTION_SOUND = "/main/resources/sounds/menuSelectSound.wav";
    private int score;

    public DeathMenu(int score) {
        this.score = score;
    }

    public DeathMenu() {
        this.score = 0;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Breakout");

        // TOU DIED
        Text youDiedText = new Text("YOU DIED");
        youDiedText.getStyleClass().add("you-died-text");

        Text scoreText = new Text("Score: " + score);
        scoreText.getStyleClass().addAll("content-text", "center-aligned");

        // Center the text in the window
        StackPane root = new StackPane();
        root.getChildren().addAll(youDiedText, scoreText);
        root.setStyle("-fx-background-color: black;");

        //fade-in effect for YOU DIED
        FadeTransition fadeInYouDied = new FadeTransition(Duration.seconds(2), youDiedText);
        fadeInYouDied.setFromValue(0); // Fully transparent
        fadeInYouDied.setToValue(1);   // Fully visible
        fadeInYouDied.play();

        //fade-in effect for score
        FadeTransition fadeInScore = new FadeTransition(Duration.seconds(4), scoreText);
        fadeInScore.setFromValue(0);
        fadeInScore.setToValue(1);
        fadeInScore.play();



        // Start Button
        Button playAgainButton = new Button("Play again!");
        playAgainButton.getStyleClass().add("start-button");
        playAgainButton.setOnAction(e -> {
            SoundController.playMenuSelectSound(SELECTION_SOUND);
            SoundController.stopBackgroundMusic();
            try {
                startGame(primaryStage);
            } catch (Exception ex) {
                System.out.println("An error occurred while starting the game");
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

        //fade-in effect for score
        FadeTransition fadeButtonPlay = new FadeTransition(Duration.seconds(6), playAgainButton);
        fadeButtonPlay.setFromValue(0);
        fadeButtonPlay.setToValue(1);
        fadeButtonPlay.play();

        //fade-in effect for score
        FadeTransition fadeButtonBack = new FadeTransition(Duration.seconds(6), backButton);
        fadeButtonBack.setFromValue(0);
        fadeButtonBack.setToValue(1);
        fadeButtonBack.play();

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
        primaryStage.show();
    }

    private void startGame(Stage primaryStage) {
        Main.startGame(Config.BRICK_HEIGHT, Config.BRICK_LENGTH);
        primaryStage.close();
    }

}