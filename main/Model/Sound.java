package main.Model;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

    Clip clip;
    URL[] soundURL = new URL[10];

    public Sound() {

        soundURL[0] = getClass().getResource("/main/resources/sounds/retroBassEffectForLoop.wav");
        soundURL[1] = getClass().getResource("/main/resources/sounds/droppingRocks5996.wav");
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
