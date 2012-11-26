package pasoos.view;

import minidraw.boardgame.BoardPiece;
import minidraw.boardgame.Command;
import pasoos.hotgammon.Game;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class GammonDice implements Command {

    private Game game;
    private BoardPiece die1;
    private BoardPiece die2;

    public GammonDice(Game game) {
        this.game = game;
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
        play("C:\\ws\\Pasoos\\AlphaMon\\resource\\Shake And Roll Dice-Sound.wav");
        if (isTurnCompleted()) {
            game.nextTurn();
        }
        return true;
    }

    private boolean isTurnCompleted() {
        return game.diceValuesLeft().length == 0;
    }

    public static void play(String filename) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(filename)));
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
    }
}
