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

public class BattlepassMenu extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Battlepass");

        //title
        Text title = new Text("Battlepass");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        title.setFill(new LinearGradient(
                0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.RED),
                new Stop(0.5, Color.ORANGE),
                new Stop(1, Color.YELLOW)
        ));

        // Placeholder for battlepass content
        Text content = new Text("Unlock exclusive rewards by playing the game!");
        content.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        content.setFill(Color.WHITE);

        // Back button
        Button backButton = new Button("Back to Menu");
        backButton.setOnAction(e -> {
            StartMenu mainMenu = new StartMenu();
            try {
                mainMenu.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        VBox layout = new VBox(20);
        layout.getChildren().addAll(title, content, backButton);
        layout.setStyle("-fx-alignment: center; -fx-background-color: black;");

        Scene scene = new Scene(layout, 768, 576 * 2);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.setAlwaysOnTop(true);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
