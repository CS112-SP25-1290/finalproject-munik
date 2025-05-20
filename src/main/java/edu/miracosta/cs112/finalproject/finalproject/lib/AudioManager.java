package edu.miracosta.cs112.finalproject.finalproject.lib;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.net.URL;

public class AudioManager {

    private MediaPlayer mediaPlayer;
    private String soundtrackFileLocation;

    public AudioManager(String soundtrackFileLocation) {
        this.soundtrackFileLocation = soundtrackFileLocation;
    }

    public AudioManager() {}

    public void playBackgroundMusic() {
        try {
            URL resource = getClass().getResource(soundtrackFileLocation);
            if (resource == null) {
                System.out.println("Music file not found!");
                return;
            }
            Media media = new Media(resource.toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Loop music
            mediaPlayer.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    public void playSound(String soundLocation) {
        String soundPath = getClass().getResource(soundLocation).toString();
        AudioClip clip = new AudioClip(soundPath);
        clip.play();
    }



}

