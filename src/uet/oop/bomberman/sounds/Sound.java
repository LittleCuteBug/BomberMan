package uet.oop.bomberman.sounds;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;

public class Sound {
    public static final String TITLE_SCREEN = "res/sound/01_TitleScreen.wav";
    public static final String STAGE_START = "res/sound/02_StageStart.wav";
    public static final String STAGE_THEME = "res/sound/03_StageTheme.wav";
    public static final String EXIT_FOUNDED = "res/sound/04_FindTheExit.wav";
    public static final String STAGE_COMPLETE = "res/sound/05_StageComplete.wav";
    public static final String BONUS_STAGE = "res/sound/06_BonusStage.wav";
    public static final String INVINCIBILITY = "res/sound/07_Invincibility.wav";
    public static final String LIFE_LOST = "res/sound/08_LifeLost.wav";
    public static final String GAME_OVER = "res/sound/09_GameOver.wav";
    public static final String ENDING = "res/sound/10_Ending.wav";
    public static final String POWERED_UP = "res/sound/11_PoweredUp.wav";
    private Clip clip;

    public enum Volume {
        MUTE, LOW, MEDIUM, HIGH
    }
    public static Volume volume = Volume.HIGH;

    public Sound(String soundFileName) {
        try {
            File file = new File(soundFileName);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play(Boolean loop) {
        if (volume != Volume.MUTE) {
            if (clip.isRunning())
                clip.stop();
            clip.setFramePosition(0);
            clip.start();
            if (loop) {
                //System.out.println("OK");
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
        }

    }

    public void stop() {
        clip.stop();
        clip.setFramePosition(0);
    }

    public void mute() {
        volume = Volume.MUTE;
    }


    public static void main(String[] args) {
        Sound sound = new Sound(Sound.POWERED_UP);
        sound.play(true);
    }
}