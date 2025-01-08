package main.menus;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.menus.StartMenu;

public class BattlepassMenu extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Battlepass");

        // Title
        Text title = new Text("Battlepass");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        title.setFill(new LinearGradient(
                0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.RED),
                new Stop(0.5, Color.ORANGE),
                new Stop(1, Color.YELLOW)
        ));

        // Create a scrollable row for awards
        HBox awardsRow = new HBox(20); // Increased spacing for better readability
        awardsRow.setPadding(new Insets(20));

        // Generate 20 awards as rectangles
        for (int i = 1; i <= 20; i++) {
            VBox award = new VBox(10); // Spacing between rectangle and text
            Rectangle awardBox = new Rectangle(100, 100, Color.LIGHTBLUE);
            Text awardText = new Text("Award " + i);
            awardText.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
            awardText.setFill(Color.WHITE); // text color
            award.getChildren().addAll(awardBox, awardText);
            award.setStyle("-fx-alignment: center;");
            awardsRow.getChildren().add(award);
        }

        // Wrap the awards row in a ScrollPane
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(awardsRow);
        scrollPane.setFitToHeight(true); // Fit the height of the ScrollPane
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setStyle("-fx-background: #000000;");

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
        backButton.setStyle("-fx-font-size: 16; -fx-background-color: DARKGRAY;");

        // Layout
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(title, scrollPane, backButton);
        layout.setStyle("-fx-alignment: center; -fx-background-color: black;");

        Scene scene = new Scene(layout, 768, 576 * 2);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
