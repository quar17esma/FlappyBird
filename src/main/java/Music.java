import org.apache.log4j.Logger;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.BufferedInputStream;

//класс отвечает за звуки и музыку
public class Music {
    //логгер
    final static Logger logger = Logger.getLogger(Music.class);

    private static Thread backgroundMusic;

    private static Clip clipBG;
    private static Clip clipJump;
    private static Clip clipGameOver;
    private static Clip clipFree;

    //файлы звуков
    private static final String BACKGROUND_MUSIC_FILE = "sounds/ThemeBackground.wav";
    private static final String JUMP_SOUND_FILE = "sounds/Jump.wav";
    private static final String GAMEOVER_SOUND_FILE = "sounds/GameOver.wav";
    private static final String FREE_SOUND_FILE = "sounds/StageClear.wav";

    //создает и запускает поток воспроизводящий фоновою музыку
    public synchronized void musicBackgroundPlay() {
        backgroundMusic = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    clipBG = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                            new BufferedInputStream(getClass().getResourceAsStream(BACKGROUND_MUSIC_FILE)));
                    clipBG.open(inputStream);
                    clipBG.loop(Clip.LOOP_CONTINUOUSLY);
                    clipBG.start();
                } catch (Exception e) {
                    logger.error("[Run BG Music] E Error: ", e);
                }
            }
        });
        backgroundMusic.start();
    }

    //останавливает фоновую музыку
    public synchronized void musicBackgroundStop(){
        clipBG.stop();
    }

    //воспроизводит звук взмаха крыла(прижка)
    public void jumpSound(){
        try{
        clipJump = AudioSystem.getClip();
        AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                new BufferedInputStream(getClass().getResourceAsStream(JUMP_SOUND_FILE)));
        clipJump.open(inputStream);
        clipJump.start();
    } catch (Exception e){
            logger.error("[Run Jump Sound] E Error: ", e);
        }
    }

    //воспроизводит звук проигрыша
    public void gameOverSoundPlay(){
        try{
            clipGameOver = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                    new BufferedInputStream(getClass().getResourceAsStream(GAMEOVER_SOUND_FILE)));
            clipGameOver.open(inputStream);
            clipGameOver.start();
        } catch (Exception e){
            logger.error("[Run Gameover Sound] E Error: ", e);
        }
    }

    public synchronized void gameOverSoundStop(){
        clipGameOver.stop();
    }

    //воспроизводит звук после прохождения все препятствий
    public void freeSoundPlay(){
        try{
            clipFree = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                    new BufferedInputStream(getClass().getResourceAsStream(FREE_SOUND_FILE)));
            clipFree.open(inputStream);
            clipFree.start();
        } catch (Exception e){
            logger.error("[Run Free Sound] E Error: ", e);
        }
    }

    //останавливает звук о прохождении всех препятствий
    public synchronized void freeSoundStop(){
        clipFree.stop();
    }

    //останавливает все звуки
    public synchronized void allSoundsStop(){
        if (clipBG != null){
            clipBG.stop();
        }
        if (clipGameOver != null){
            clipGameOver.stop();
        }
        if (clipFree != null){
            clipFree.stop();
        }
    }
}
