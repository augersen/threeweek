package main.menus;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.logging.Logger;
import java.util.logging.Level;

public class BattlepassMenu extends Application {

    private static final Logger LOGGER = Logger.getLogger(BattlepassMenu.class.getName());

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Battlepass");

        // Title
        Text title = new Text("Battlepass");
        title.getStyleClass().addAll("title-text", "centered-text");

        // Awards
        HBox awardsRow = new HBox(20);
        awardsRow.setPadding(new Insets(20));
        for (int i = 1; i <= 20; i++) {
            VBox award = new VBox(10); // Spacing between rectangle and text
            Rectangle awardBox = new Rectangle(100, 100);
            awardBox.getStyleClass().add("award-box"); // Assign the CSS class
            Text awardText = new Text("Award " + i);
            awardText.getStyleClass().add("content-text");
            award.getChildren().addAll(awardBox, awardText);
            award.getStyleClass().add("center-aligned");
            awardsRow.getChildren().add(award);
        }


        // ScrollPane
        ScrollPane scrollPane = new ScrollPane(awardsRow);
        scrollPane.getStyleClass().add("scrollpane-background");

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
        layout.getChildren().addAll(title, scrollPane, backButton);
        layout.getStyleClass().addAll("center-aligned", "scene-background");

        // Scene
        Scene scene = new Scene(layout, 768, 576 * 2);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/main/resources/styles.css")).toExternalForm()); //import css class

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
