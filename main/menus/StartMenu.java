package main.menus;

import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.Config;
import main.SoundController;

import java.util.Objects;


public class StartMenu extends Application {





    private static final String BACKGROUND_MUSIC = "/main/resources/sounds/menuMusic.wav";
    private static final String SELECTION_SOUND = "/main/resources/sounds/menuSelectSound.wav";

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Breakout");

        // Title
        Text title = new Text("Breakout");
        title.getStyleClass().addAll("title-text", "centered-text");

        // Play background music
        SoundController.playBackgroundMusic(BACKGROUND_MUSIC);

        //attempt at animation
        // Scaling animation
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(1.5), title);
        scaleTransition.setFromX(1);
        scaleTransition.setFromY(1);
        scaleTransition.setToX(1.2);
        scaleTransition.setToY(1.2);
        scaleTransition.setAutoReverse(true);
        scaleTransition.setCycleCount(ScaleTransition.INDEFINITE);

        SequentialTransition animation = new SequentialTransition(scaleTransition);
        animation.play();
        //animations over

        //select modifiers
        Button selectionButton = new Button("Play!");
        selectionButton.getStyleClass().add("play-button");
        selectionButton.setOnAction(e -> {
            SoundController.playMenuSelectSound(SELECTION_SOUND);
            try {
                SelectionMenu selectionMenu = new SelectionMenu();
                selectionMenu.start(primaryStage);
            } catch (Exception ex) {
                System.out.println("An error occurred while opening SelectionMenu");
            }
        });


        //battlepass
        Button battlepassButton = new Button("Battlepass");
        battlepassButton.getStyleClass().add("battlepass-button");
        battlepassButton.setOnAction(e -> {
            SoundController.playMenuSelectSound(SELECTION_SOUND);
            try {
                BattlepassMenu battlepassMenu = new BattlepassMenu();
                battlepassMenu.start(primaryStage);
            } catch (Exception ex) {
                System.out.println("An error occurred while opening BattlepassMenu");
            }

        });

        //highscores
        Button highscoresButton = new Button("Highscores");
        highscoresButton.getStyleClass().add("highscore-button");
        highscoresButton.setOnAction(e -> {
            SoundController.playMenuSelectSound(SELECTION_SOUND);
            try {
                HighscoresMenu highscoresMenu = new HighscoresMenu();
                highscoresMenu.start(primaryStage);
            } catch (Exception ex) {
                System.out.println("An error occurred while opening HighscoresMenu");
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
        Scene scene = new Scene(menu, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/main/resources/styles.css")).toExternalForm()); //import css class

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

}
