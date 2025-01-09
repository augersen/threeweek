package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    // Clip is used for opening audio files
    Clip clip;
    // URL is used as an array for storing file paths for our sound files
    URL soundURL[] = new URL[10];

    public Sound() {

        soundURL[0] = getClass().getResource("/main/sound/retroBassEffectForLoop.wav");
        soundURL[1] = getClass().getResource("/main/sound/droppingRocks5996.wav");
        soundURL[2] = getClass().getResource("/main/sound/menuMusic.wav");

    }

    public void setFile(int i) {

        try {

            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);

        } catch (Exception e) {
            System.out.println("What");
        }
    }

    public void play() {

        clip.start();
    }

    public void loop() {

        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    // Stop
    public void stop() {

        clip.stop();
    }

    // SOUND
    public void playMusic(int i) {

        setFile(i);
        play();
        loop();
    }

    public void stopMusic() {

        stop();
    }

    public void playSE(int i) {

        setFile(i);
        play();
    }
}
