package pasoos.view;

import minidraw.boardgame.BoardPiece;
import minidraw.boardgame.PropAppearanceStrategy;
import minidraw.framework.*;
import pasoos.hotgammon.Color;
import pasoos.hotgammon.Game;
import pasoos.view.gamestatemachine.GammonStateMachine;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

public class GammonDice implements PropAppearanceStrategy {

    private Game game;
    private GammonStateMachine gammonStateMachine;
    private BoardPiece die1;
    private BoardPiece die2;
    private AnimationEngine animationEngine;

    public GammonDice(Game game, GammonStateMachine state) {
        this.game = game;
        this.gammonStateMachine = state;
    }

    public void rollRequest() {
        gammonStateMachine.rollDiceRequest();
    }

    public void setDie1(BoardPiece piece) {
        die1 = piece;
    }

    public void setDie2(BoardPiece piece) {
        die2 = piece;
    }

    private File url2dir(URL _url) {
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

    public void roll() {
        play("Shake And Roll Dice-Sound.wav");
        TimeInterval timeline = TimeInterval.fromNow().duration(1000);

        Animation a1 = new MoveAnimation(die1, die1.displayBox().getLocation(), timeline, new Shaker());
        Animation a2 = new MoveAnimation(die2, die2.displayBox().getLocation(), timeline, new Shaker());
        a1.addAnimationChangeListener(new AnimationChangeListener() {
            @Override
            public void onAnimationCompleted(AnimationChangeEvent ace) {
                game.nextTurn();
            }
        });
        animationEngine.startAnimation(a1);
        animationEngine.startAnimation(a2);
    }

    public void setAnimationEngine(AnimationEngine animationEngine) {
        this.animationEngine = animationEngine;
    }

    @Override
    public String calculateImageNameForPropWithKey(String keyOfProp) {
        String diePrefix;

        if (game.getPlayerInTurn() == Color.RED)
            diePrefix = "rdie";
        else
            diePrefix = "bdie";

        if (keyOfProp.equals("die1")) {
            return diePrefix + game.diceThrown()[0];
        } else if (keyOfProp.equals("die2")) {
            return diePrefix + game.diceThrown()[1];
        }
        return "die0";
    }

}
