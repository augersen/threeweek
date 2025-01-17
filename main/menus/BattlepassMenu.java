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
    private static final String AWARD_SELECT = "/main/resources/sounds/fanfare.wav";

    private StackPane root;

    public static String battlepassConfig = "NothingApplied";

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
                "Musk", "Lion", "award3", "award4", "award5", "award6", "award7", "award8", "award9", "award10", 
                "award11", "award12", "award13", "award14", "award15", "award16", "award17", "award18", "award19", "award20",
        };

        // Actions for each award
        Map<Integer, Runnable> rewardActions = new HashMap<>();
        rewardActions.put(1, this::unlockFeature1);
        rewardActions.put(2, this::unlockFeature2);
        rewardActions.put(3, this::unlockFeature3);
        rewardActions.put(4, this::unlockFeature4);
        rewardActions.put(5, this::unlockFeature5);
        rewardActions.put(6, this::unlockFeature6);
        rewardActions.put(7, this::unlockFeature7);
        rewardActions.put(8, this::unlockFeature8);
        rewardActions.put(9, this::unlockFeature9);
        rewardActions.put(10, this::unlockFeature10);
        rewardActions.put(11, this::unlockFeature11);
        rewardActions.put(12, this::unlockFeature12);
        rewardActions.put(13, this::unlockFeature13);
        rewardActions.put(14, this::unlockFeature14);
        rewardActions.put(15, this::unlockFeature15);
        rewardActions.put(16, this::unlockFeature16);
        rewardActions.put(17, this::unlockFeature17);
        rewardActions.put(18, this::unlockFeature18);
        rewardActions.put(19, this::unlockFeature19);
        rewardActions.put(20, this::unlockFeature20);

        // Awards row
        HBox awardsRow = new HBox(20);
        awardsRow.setPadding(new Insets(20));
        for (int i = 1; i <= rewardNames.length; i++) {
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
                SoundController.playMenuSelectSound(AWARD_SELECT);
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
        battlepassConfig = "Battlepass1";
    }

    private void unlockFeature2() {
        System.out.println("award 2");
        showPopUp("Congratulations on unlocking Lion!");
        battlepassConfig = "Battlepass2";
    }

    private void unlockFeature3() {
        System.out.println("award 3");
        showPopUp("Congratulations on unlocking award3!");
        battlepassConfig = "Battlepass3";
    }

    private void unlockFeature4() {
        System.out.println("award 4");
        showPopUp("Congratulations on unlocking award4!");
        battlepassConfig = "Battlepass4";
    }

    private void unlockFeature5() {
        System.out.println("award 5");
        showPopUp("Congratulations on unlocking award5!");
        battlepassConfig = "Battlepass5";
    }

    private void unlockFeature6() {
        System.out.println("award 6");
        showPopUp("Congratulations on unlocking award6!");
        battlepassConfig = "Battlepass6";
    }

    private void unlockFeature7() {
        System.out.println("award 7");
        showPopUp("Congratulations on unlocking award7!");
        battlepassConfig = "Battlepass7";
    }

    private void unlockFeature8() {
        System.out.println("award 8");
        showPopUp("Congratulations on unlocking award8!");
        battlepassConfig = "Battlepass8";
    }

    private void unlockFeature9() {
        System.out.println("award 9");
        showPopUp("Congratulations on unlocking award9!");
        battlepassConfig = "Battlepass9";
    }

    private void unlockFeature10() {
        System.out.println("award 10");
        showPopUp("Congratulations on unlocking award10!");
        battlepassConfig = "Battlepass10";
    }

    private void unlockFeature11() {
        System.out.println("award 11");
        showPopUp("Congratulations on unlocking award11!");
        battlepassConfig = "Battlepass11";
    }

    private void unlockFeature12() {
        System.out.println("award 12");
        showPopUp("Congratulations on unlocking award12!");
        battlepassConfig = "Battlepass12";
    }

    private void unlockFeature13() {
        System.out.println("award 13");
        showPopUp("Congratulations on unlocking award13!");
        battlepassConfig = "Battlepass13";
    }

    private void unlockFeature14() {
        System.out.println("award 14");
        showPopUp("Congratulations on unlocking award14!");
        battlepassConfig = "Battlepass14";
    }

    private void unlockFeature15() {
        System.out.println("award 15");
        showPopUp("Congratulations on unlocking award15!");
        battlepassConfig = "Battlepass15";
    }

    private void unlockFeature16() {
        System.out.println("award 16");
        showPopUp("Congratulations on unlocking award16!");
        battlepassConfig = "Battlepass16";
    }

    private void unlockFeature17() {
        System.out.println("award 17");
        showPopUp("Congratulations on unlocking award17!");
        battlepassConfig = "Battlepass17";
    }

    private void unlockFeature18() {
        System.out.println("award 18");
        showPopUp("Congratulations on unlocking award18!");
        battlepassConfig = "Battlepass18";
    }

    private void unlockFeature19() {
        System.out.println("award 19");
        showPopUp("Congratulations on unlocking award19!");
        battlepassConfig = "Battlepass19";
    }

    private void unlockFeature20() {
        System.out.println("award 20");
        showPopUp("Congratulations on unlocking award20!");
        battlepassConfig = "Battlepass20";
    }
}
