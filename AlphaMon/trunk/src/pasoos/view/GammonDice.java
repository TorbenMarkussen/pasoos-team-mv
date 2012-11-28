package pasoos.view;

import minidraw.boardgame.BoardPiece;
import minidraw.boardgame.Command;
import pasoos.hotgammon.Game;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

public class GammonDice implements Command {

    private Game game;
    private State state;
    private BoardPiece die1;
    private BoardPiece die2;

    public GammonDice(Game game, State state) {
        this.game = game;
        this.state = state;
    }

    public void addDie1(BoardPiece piece) {
        die1 = piece;
    }

    public void addDie2(BoardPiece piece) {

        die2 = piece;
    }

    @Override
    public void setFromCoordinates(int fromX, int fromY) {
    }

    @Override
    public void setToCoordinates(int toX, int toY) {
    }

    @Override
    public boolean execute() {
        play("Shake And Roll Dice-Sound.wav");
        state.rollDiceRequest();
        return true;
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


    public void play(String filename) {
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
