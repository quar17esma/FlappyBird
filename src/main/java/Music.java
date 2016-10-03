import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

//класс отвечает за звуки и музыку
public class Music {
    private static Thread backgroundMusic;
    private static Clip clipBG;
    public static final String BACKGROUND_MUSIC_FILE = "sounds/ThemeBackground.wav";
    public static final String JUMP_SOUND_FILE = "sounds/jump.wav";
    public static final String GAMEOVER_SOUND_FILE = "sounds/MarioDie.wav";

    //создает и запускает поток воспроизводящий фоновою музыку
    public synchronized void musicBackgroundPlay() {
        backgroundMusic = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    clipBG = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                            getClass().getResourceAsStream(BACKGROUND_MUSIC_FILE));
                    clipBG.open(inputStream);
                    clipBG.loop(Clip.LOOP_CONTINUOUSLY);
                    clipBG.start();
                } catch (Exception e) {
                    System.out.println("[Run BG Music] E Error: " + e.getMessage());
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
        Clip clipJump = AudioSystem.getClip();
        AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                getClass().getResourceAsStream(JUMP_SOUND_FILE));
        clipJump.open(inputStream);
        clipJump.start();
    } catch (Exception e){
            System.out.println("[Run Jump Sound] E Error: " + e.getMessage());
        }
    }

    //воспроизводит звук проигрыша
    public void gameOverSound(){
        try{
            Clip clipGameOver = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                    getClass().getResourceAsStream(GAMEOVER_SOUND_FILE));
            clipGameOver.open(inputStream);
            clipGameOver.start();
        } catch (Exception e){
            System.out.println("[Run Gameover Sound] E Error: " + e.getMessage());
        }
    }
}
