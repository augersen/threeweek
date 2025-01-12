package main.menus;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.Config;
import main.SoundController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BattlepassMenu extends Application {

    private static final String SELECTION_SOUND = "/main/resources/sounds/menuSelectSound.wav";

    private StackPane root;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Battlepass");

        root = new StackPane();
        root.getStyleClass().add("stackpane-root");
        root.setPadding(new Insets(0));


        // Title
        Text title = new Text("Battlepass");
        title.getStyleClass().addAll("title-text", "centered-text");

        // Award names
        String[] rewardNames = {
                "Musk", "Lion"
        };

        // Actions for each award
        Map<Integer, Runnable> rewardActions = new HashMap<>();
        rewardActions.put(1, this::unlockFeature1);
        rewardActions.put(2, this::unlockFeature2);

        // Awards row
        HBox awardsRow = new HBox(20);
        awardsRow.setPadding(new Insets(20));
        for (int i = 1; i <= 2; i++) {
            VBox award = new VBox(10); // Spacing between rectangle and text

            Image awardImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/main/resources/images/award" + i + ".png")));
            ImageView awardImageView = new ImageView(awardImage);
            awardImageView.setFitWidth(100); // Image size
            awardImageView.setFitHeight(100);
            awardImageView.setPreserveRatio(true);

            // Award text
            Text awardText = new Text(rewardNames[i - 1]); // Name from rewardNames
            awardText.getStyleClass().add("content-text");

            // Click event for each award
            int finalI = i;
            award.setOnMouseClicked(e -> {
                SoundController.playMenuSelectSound(SELECTION_SOUND);
                System.out.println("Clicked award: " + finalI + ", name: " + rewardNames[finalI - 1]);
                rewardActions.getOrDefault(finalI, () -> showPopUp("No action for award: " + finalI)).run();
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
        layout.getChildren().addAll(title, scrollPane, backButton);
        layout.getStyleClass().addAll("center-aligned", "scene-background");

        root.getChildren().add(layout); // add the main layout to the root

        // Scene
        Scene scene = new Scene(root, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/main/resources/styles.css")).toExternalForm()); // Import CSS class

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showPopUp(String message) {
        Text popup = new Text(message);
        popup.getStyleClass().add("popup-text");

        StackPane popUp = new StackPane(popup);
        popUp.getStyleClass().add("popup-background");
        popUp.setOpacity(0);

        root.getChildren().add(popUp);

        // Fade-in animation
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), popUp);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);

        // Fade-out animation
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), popUp);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setDelay(Duration.seconds(2)); // Wait for 2 seconds before fading out
        fadeOut.setOnFinished(e -> root.getChildren().remove(popUp));

        fadeIn.play();
        fadeOut.play();
    }

    private void unlockFeature1() {
        System.out.println("award 1");
        showPopUp("Congratulations on unlocking Musk!");
    }

    private void unlockFeature2() {
        System.out.println("award 2");
        showPopUp("Congratulations on unlocking Lion!");
    }
}
