package pkg2048game;

import javax.sound.sampled.*;
import java.io.File;

public class SoundManager {

    private static Clip bgClip;
    private static boolean musicEnabled = false;
    
    public static void playBackgroundMusic() {

        try {

            File file = new File("bgmusic.wav");

            AudioInputStream audio =
            AudioSystem.getAudioInputStream(file);
            bgClip = AudioSystem.getClip();
            bgClip.open(audio);
            bgClip.loop(Clip.LOOP_CONTINUOUSLY);
            musicEnabled = true;

        } catch(Exception e) {

            e.printStackTrace();
        }
    }

    public static void stopBackgroundMusic() {

        if(bgClip != null) {

            bgClip.stop();
        }
        musicEnabled = false;
    }

    public static void pauseBackgroundMusic() {

        if(bgClip != null && bgClip.isRunning()) {

            bgClip.stop();
        }
    }

    public static void resumeBackgroundMusic() {

        if(bgClip != null) {

            bgClip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }
    
    public static boolean isMusicEnabled() {
    return musicEnabled;
}
}