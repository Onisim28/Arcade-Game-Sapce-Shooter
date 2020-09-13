package SpaceShoother;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;


public class Sounds {

    public void playMusic(String musicLocation, double volume) {
        try {
            File musicPath = new File(musicLocation);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicPath);

            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            setVolume(volume, clip);

            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (Exception e) {
            System.out.println("No sound file here!");
        }

    }


    public void playsound(String musicLocation, double volume) {
        try {
            File musicPath = new File(musicLocation);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicPath);

            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            setVolume(volume, clip);

            clip.start();

        } catch (Exception e) {
            System.out.println("No sound file here!");
        }

    }

    public void setVolume(double volume, Clip clip) {

        FloatControl gain = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        float db = (float) (Math.log(volume) / Math.log(10) * 20);
        gain.setValue(db);
    }


}


