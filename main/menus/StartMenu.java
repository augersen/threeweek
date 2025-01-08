package main.menus;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import main.Main;

public class StartMenu extends Application {
    //test
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Breakout");

        //Breakout title
        Text title = new Text("Breakout");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        title.setFill(new LinearGradient(
                0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.RED),
                new Stop(0.5, Color.ORANGE),
                new Stop(1, Color.YELLOW)
        ));


        //Start button
        Button startButton = new Button("Start Game");
        startButton.setOnAction(e -> startGame(primaryStage));
        startButton.setStyle("-fx-font-size: 20; -fx-background-color: GREEN;");

        //Start button
        Button battlepassButton = new Button("Battle Pass");
        battlepassButton.setOnAction(e -> {
            BattlepassMenu battlepassMenu = new BattlepassMenu();
            try {
                battlepassMenu.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        battlepassButton.setStyle("-fx-font-size: 20; -fx-background-color: YELLOW;");

        //Highscores
        Button highscoresButton = new Button("Highscores");
        highscoresButton.setOnAction(e -> {
            HighscoresMenu highscoresMenu = new HighscoresMenu();
            try {
                highscoresMenu.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        highscoresButton.setStyle("-fx-font-size: 20; -fx-background-color: LIGHTBLUE;");

        //Quit Button
        Button quitButton = new Button("Quit Game");
        quitButton.setOnAction(e -> System.exit(0));
        quitButton.setStyle("-fx-font-size: 20; -fx-background-color: RED;");

        //Layout
        VBox menu = new VBox(20);
        menu.getChildren().addAll(title, startButton,battlepassButton, highscoresButton, quitButton);
        menu.setStyle("-fx-alignment: center; -fx-background-color: black;");

        Scene scene = new Scene(menu, 768, 576*2);

        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.setAlwaysOnTop(true);
    }

    private void startGame(Stage primaryStage) {
        Main.startGame();
        primaryStage.close();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
