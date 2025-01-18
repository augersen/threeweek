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
import main.ScoreManager;
import main.SoundController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class BattlepassMenu extends Application {
    
    private static final String SELECTION_SOUND = "/main/resources/sounds/menuSelectSound.wav";
    private static final String AWARD_SELECT = "/main/resources/sounds/fanfare.wav";

    private StackPane root;

    public static String battlepassConfig = "NothingApplied";

    private int calculateTotalScore() {
        // List score-files
        List<String> modifiers = List.of(
            "NoModifier",
            "ExampleModifier",
            "PlatformModifier",
            "PowerupModifier",
            "PlaceholderModifier"
        );
    
        int totalScore = 0;
    
        // Finds the sum
        for (String modifier : modifiers) {
            List<Integer> scores = ScoreManager.readScores(modifier);
            totalScore += scores.stream().mapToInt(Integer::intValue).sum();
        }
    
        return totalScore;
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Battlepass");

        root = new StackPane();
        root.getStyleClass().add("stackpane-root");
        root.setPadding(new Insets(0));

        int totalScore = calculateTotalScore();
        int unlockedAwards = totalScore / 500; // 1 award for every 500 score
        
        // Title
        Text title = new Text("Battlepass");
        title.getStyleClass().addAll("title-text", "centered-text");

        // Score text
        Text scoreText;
        if (totalScore > 0) {
            scoreText = new Text("Total Score: " + totalScore);
        } else {
            scoreText = new Text("No scores found. Play to earn points!");
        }
        scoreText.getStyleClass().addAll("score-text", "center-aligned");

        // Award names
        String[] rewardNames = {
                "Musky", "Big Cat", "Shreek", "Gigachad", "Rock", "Feels good, man", "Morbin' time", "Dark Side", "Big Chungus", "The States", 
                "To the moon", "Idiot sandwich", "Donda 4", "Pingo", "Wubba Lubba Dub Dub", "Jeff", "Precious", "Batman", "Thomas", "The Woz",
        };

        

        // Actions for each award
        Map<Integer, Runnable> rewardActions = new HashMap<>();
        for (int i = 1; i <= rewardNames.length; i++) {
            int finalI = i; // Needs to be final for use of i lambda
            rewardActions.put(i, () -> unlockFeature(finalI, rewardNames[finalI - 1]));
}


        // Awards row
        HBox awardsRow = new HBox(20);
        awardsRow.setPadding(new Insets(20));
        for (int i = 1; i <= rewardNames.length; i++) {
            VBox award = new VBox(10); // Spacing between rectangle and text

            // Show picture if unlocked, else locked-image
            String imagePath = i <= unlockedAwards ? "/main/resources/images/award" + i + ".png" : "/main/resources/images/locked.png"; 
            
            Image awardImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
            ImageView awardImageView = new ImageView(awardImage);
            awardImageView.setFitWidth(100); // Image size
            awardImageView.setFitHeight(100);
            awardImageView.setPreserveRatio(true);

            // Award text
            Text awardText = new Text(i <= unlockedAwards ? rewardNames[i - 1] : "Locked"); // Shows 'Locked' if item is locked
            awardText.getStyleClass().add("content-text");

            // Points required text for locked awards
            Text pointsRequiredText = new Text();
            if (i > unlockedAwards) {
                int pointsRequired = i * 500; // Points needed to unlock this award
                pointsRequiredText.setText("Requires total score of " + pointsRequired);
                pointsRequiredText.getStyleClass().add("content-text");
            }

            // Click event for each award
            int finalI = i;
            if (i <= unlockedAwards) {  
                award.setOnMouseClicked(e -> {
                    SoundController.playMenuSelectSound(AWARD_SELECT);
                    System.out.println("Clicked award: " + finalI + ", name: " + rewardNames[finalI - 1]);
                    rewardActions.getOrDefault(finalI, () -> showPopUp("No action for award: " + finalI)).run();
                });
            }

            award.getChildren().addAll(awardImageView, awardText);
            if (i > unlockedAwards) {
                award.getChildren().add(pointsRequiredText); // Adds points required text only for locked awards
            }
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
        layout.getChildren().addAll(title, scoreText, scrollPane, backButton);
        layout.setPadding(new Insets(20));
        layout.getStyleClass().addAll("center-aligned", "scene-background");

        root.getChildren().add(layout); // add the main layout to the root

        // Scene
        Scene scene = new Scene(root, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/main/resources/styles.css")).toExternalForm()); // Import CSS class
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
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

    private void unlockFeature(int awardId, String awardName) {
        System.out.println("award " + awardId);
        showPopUp("Congratulations on unlocking " + awardName + "!");
        battlepassConfig = "Battlepass" + awardId;
    }    
}