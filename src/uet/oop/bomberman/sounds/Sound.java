package uet.oop.bomberman.sounds;
import uet.oop.bomberman.graphics.SpriteSheet;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import javax.sound.sampled.*;

public class Sound {
    public static final String STAGE_START = "/sound/02_StageStart.wav";
    public static final String STAGE_THEME = "/sound/03_StageTheme.wav";
    public static final String STAGE_COMPLETE = "/sound/05_StageComplete.wav";
    public static final String INVINCIBILITY = "/sound/07_Invincibility.wav";
    public static final String LIFE_LOST = "/sound/08_LifeLost.wav";
    public static final String ENDING = "/sound/10_Ending.wav";
    public static final String ITEM_USED = "/sound/14_ItemUsed.wav";
    public static final String EXPLOSION = "/sound/15_ExplosionDec.wav";
    public static final String PLACE_BOMB = "/sound/13_PlaceBomb.wav";

    public static final Sound STAGE_START_SOUND =  new Sound(Sound.STAGE_START,false);
    public static final Sound STAGE_THEME_SOUND = new Sound(Sound.STAGE_THEME,true);
    public static final Sound STAGE_COMPLETE_SOUND = new Sound(Sound.STAGE_COMPLETE,false);
    public static final Sound INVINCIBILITY_SOUND = new Sound(Sound.INVINCIBILITY,true);
    public static final Sound LIFE_LOST_SOUND = new Sound(Sound.LIFE_LOST,false);
    public static final Sound ENDING_SOUND = new Sound(Sound.ENDING,true);
    public static final Sound ITEM_USED_SOUND = new Sound(Sound.ITEM_USED,false);

    private Boolean loop;
    private Clip clip;

    public enum Volume {
        MUTE, LOW, MEDIUM, HIGH
    }
    public static Volume volume = Volume.HIGH;

    public Sound(String soundFileName, Boolean loop) {
        try {
            URL url = Sound.class.getResource(soundFileName);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            this.loop = loop;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public Boolean isNotPlay(){
        return !clip.isRunning();
    }

    public Boolean isPlay(){
        return clip.isRunning();
    }

    public void play() {
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
//        Sound sound = new Sound(Sound.POWERED_UP,false);
//        sound.play();
//        //Sound.POWERED_UP_SOUND.play();
        Sound.STAGE_START_SOUND.play();
        while (true){}
    }
}