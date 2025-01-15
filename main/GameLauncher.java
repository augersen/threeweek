package main;

import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.swing.SwingUtilities;

public class GameLauncher extends Application {

    //SHOUTOUT TIL MOOLERIAN STACKOVERFLOW
    private static Stage gameStage; // Reference to the game stage
    Modifiers modifiers = Modifiers.getInstance();


    @Override
    public void start(Stage primaryStage) {
        gameStage = primaryStage;

        // Create a SwingNode to embed the Swing game
        SwingNode swingNode = new SwingNode();
        createAndSetSwingContent(swingNode);

        // Add the SwingNode to a JavaFX layout
        StackPane root = new StackPane();
        root.getChildren().add(swingNode);

        // Set up the JavaFX Scene
        Scene scene = new Scene(root, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        primaryStage.setTitle("Breakout");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void createAndSetSwingContent(SwingNode swingNode) {
        SwingUtilities.invokeLater(() -> {
            View2D gamePanel = new View2D(Config.BRICK_HEIGHT, Config.BRICK_LENGTH);
            gamePanel.setGameStage(gameStage); // Pass the stage for closure handling
            gamePanel.startGameThread();
            swingNode.setContent(gamePanel);
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
