package MenuPlay.ElementsMenu;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Music {
    private static Clip clip;
    private static double level = 1;

    public Music() {

    }

    public static void play() {
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        try {
            clip.open(AudioSystem.getAudioInputStream(new File("music_menu.wav")));
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
        clip.loop(100);
        clip.setMicrosecondPosition(2000000);
        clip.start();
    }

    public static void setLevel(double slevel) {
        level = slevel;
        Music.setVolume();
    }

    public static double getLevel() {
        return level;
    }

    public static void setVolume() {
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        float dB = (float) (Math.log(getLevel()) / Math.log(10.0) * 20.0);
        gainControl.setValue(dB);

        BooleanControl muteControl = (BooleanControl) clip.getControl(BooleanControl.Type.MUTE);
        muteControl.setValue(true);

        muteControl.setValue(false);
    }

    public static void stop() {
        clip.stop();
    }
}
