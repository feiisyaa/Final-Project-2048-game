package pkg2048game;

import javax.sound.sampled.*;
import java.io.File;

public class SoundManager {

    private static Clip bgClip;

    public static void playBackgroundMusic() {
        try {
            File file = new File("bgmusic.wav");

            AudioInputStream audio =
                    AudioSystem.getAudioInputStream(file);

            bgClip = AudioSystem.getClip();
            bgClip.open(audio);
            bgClip.loop(Clip.LOOP_CONTINUOUSLY);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void stopBackgroundMusic() {
        if(bgClip != null) {
            bgClip.stop();
        }
    }

    public static void resumeBackgroundMusic() {
        if(bgClip != null) {
            bgClip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }
}