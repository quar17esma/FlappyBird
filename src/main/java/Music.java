import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.*;


public class Music {
    public static Thread backgroundMusic;
    public static Clip clipBG;

    public synchronized void musicBackgroundPlay() {
        backgroundMusic = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    clipBG = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                            getClass().getResourceAsStream("ThemeBackground.wav"));
                    clipBG.open(inputStream);
                    clipBG.loop(Clip.LOOP_CONTINUOUSLY);
                    clipBG.start();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.err.println(e.getMessage());
                }
            }
        });
        backgroundMusic.start();
    }

    public synchronized void musicBackgroundStop(){
        clipBG.stop();
    }

    public void jumpSound(){
        try{
        Clip clipJump = AudioSystem.getClip();
        AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                getClass().getResourceAsStream("jump.wav"));
        clipJump.open(inputStream);
        clipJump.start();
    } catch (Exception e){e.printStackTrace();}
    }

    public void gameOverSound(){
        try{
            Clip clipJump = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                    getClass().getResourceAsStream("MarioDie.wav"));
            clipJump.open(inputStream);
            clipJump.start();
        } catch (Exception e){e.printStackTrace();}
    }


}
