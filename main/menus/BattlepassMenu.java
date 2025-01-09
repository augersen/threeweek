package main.menus;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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

        //Awards
        //award names
        String[] rewardNames = {
                "Musk", "Lion"
        };

        HBox awardsRow = new HBox(20);
        awardsRow.setPadding(new Insets(20));
        for (int i = 1; i <= 2; i++) {
            VBox award = new VBox(10); // Spacing between rectangle and text

            Image awardImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/main/resources/images/award" + i + ".png"))); // image folder
            ImageView awardImageView = new ImageView(awardImage);
            awardImageView.setFitWidth(100); // image size
            awardImageView.setFitHeight(100);
            awardImageView.setPreserveRatio(true);

            // award text
            Text awardText = new Text(rewardNames[i - 1]); // name from rewardNames
            awardText.getStyleClass().add("content-text");

            //click event to the award
            int finalI = i;
            award.setOnMouseClicked(e -> {
                //logic for each award click here
                System.out.println("clicked award: " + finalI + ", name: " + rewardNames[finalI - 1]);
            });

            award.getChildren().addAll(awardImageView, awardText);
            award.getStyleClass().add("center-aligned");
            awardsRow.getChildren().add(award);
        }


        // ScrollPane
        ScrollPane scrollPane = new ScrollPane(awardsRow);
        scrollPane.getStyleClass().add("scrollpane-background");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS); // Enable horizontal scrolling
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Disable vertical scrolling

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
