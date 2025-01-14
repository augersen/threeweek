package main;

import javafx.scene.media.AudioClip;

import java.util.Objects;

public class SoundController {

    private static AudioClip backgroundMusic;
    private static AudioClip menuSelectSound;
    private static AudioClip deathSound;

    // Play the background music
    public static void playBackgroundMusic(String musicFilePath) {
        if (backgroundMusic == null || !backgroundMusic.isPlaying()) {
            backgroundMusic = new AudioClip(Objects.requireNonNull(SoundController.class.getResource(musicFilePath)).toString());
            backgroundMusic.setCycleCount(AudioClip.INDEFINITE); // Loop indefinitely
            backgroundMusic.play();
        }
    }

    //Play death sound
    public static void playDeathSound(String musicFilePath) {
        if (deathSound == null || !deathSound.isPlaying()) {
            deathSound = new AudioClip(Objects.requireNonNull(SoundController.class.getResource(musicFilePath)).toString());
            deathSound.setCycleCount(1); // NO LOOP
            deathSound.play();
        }
    }

    // Stop the background music
    public static void stopBackgroundMusic() {
        if (backgroundMusic != null) {
            backgroundMusic.stop();
        }
    }

    public static void playMenuSelectSound(String soundFilePath){
        if (menuSelectSound == null || !menuSelectSound.isPlaying()) {
            menuSelectSound = new AudioClip(Objects.requireNonNull(SoundController.class.getResource(soundFilePath)).toString());
            menuSelectSound.setCycleCount(1); // NO LOOP
            menuSelectSound.play();
        }
    }

    // TODO: VIRKER IKKE
    public static void setVolume(double volume) {
        if (backgroundMusic != null) {
            backgroundMusic.setVolume(Math.max(0.0, Math.min(1.0, volume))); // Clamp volume between 0.0 and 1.0
        }
    }
}
