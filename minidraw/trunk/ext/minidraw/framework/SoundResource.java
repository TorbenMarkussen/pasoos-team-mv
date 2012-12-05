package minidraw.framework;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;


public class SoundResource {
    private boolean soundsEnabled;

    public SoundResource(boolean soundsEnabled) {
        this.soundsEnabled = soundsEnabled;
    }

    public void playDiceRollerSound() {
        play("Shake And Roll Dice-Sound.wav");
    }

    public void playVictorySound() {
        play("Crowd_Cheer.wav");
    }

    public void playCheckerMoveSound() {
        play("Checker-Move.wav");
    }

    public void playErrorSound() {
        play("Checker-WrongMove.wav");
    }

    private File url2dir(URL _url) {
        // Convert URL to a File (from Kohsuke Kawaguchi's Blog)
        File dir;
        try {
            dir = new File(_url.toURI());
        } catch (URISyntaxException e) {
            dir = new File(_url.getPath());
        }
        return dir;
    }

    private void play(String filename) {
        if (!soundsEnabled) {
            return;
        }

        final String RESOURCE_PATH = "/resource/";
        java.net.URL _url = getClass().getResource(RESOURCE_PATH);
        if (_url == null) {
            throw new RuntimeException("ImageManager: URL/folder '" + RESOURCE_PATH + "' does not exist.");
        }

        File dir = url2dir(_url);

        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(dir + "/" + filename)));
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
    }
}
