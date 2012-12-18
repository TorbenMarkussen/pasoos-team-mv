package pasoos.hotgammon.animatedgame.ui;

import minidraw.animation.*;
import minidraw.animation.engine.AnimationEngine;
import minidraw.boardgame.BoardPiece;
import minidraw.boardgame.PropAppearanceStrategy;
import pasoos.hotgammon.Color;
import pasoos.hotgammon.Game;
import pasoos.hotgammon.easings.Shaker;
import pasoos.hotgammon.sounds.SoundResource;

import java.awt.*;
import java.util.Map;

public class GammonDice implements PropAppearanceStrategy, AnimationChangeListener {

    private Game game;
    private BoardPiece die1;
    private BoardPiece die2;
    private AnimationEngine animationEngine;
    private SoundResource soundResource;

    public GammonDice(Game game, AnimationEngine animationEngine, SoundResource soundResource, Map<String, BoardPiece> diceMap) {
        this.game = game;
        this.animationEngine = animationEngine;
        this.soundResource = soundResource;
        die1 = diceMap.get("die1");
        die2 = diceMap.get("die2");
    }

    public void roll() {
        soundResource.playDiceRollerSound();
        animateDieRoll();
    }

    private void animateDieRoll() {
        TimeInterval timeInterval = TimeInterval.fromNow().duration(1000);
        Animation die1NumberAnimation = createDieNumberAnimation(die1, timeInterval);
        Animation die2NumberAnimation = createDieNumberAnimation(die2, timeInterval);

        Animation die1ShakerAnimation = createDieShakerAnimation(die1, timeInterval);
        Animation die2ShakerAnimation = createDieShakerAnimation(die2, timeInterval);

        die2ShakerAnimation.addAnimationChangeListener(this);

        animationEngine.startAnimation(die1NumberAnimation);
        animationEngine.startAnimation(die2NumberAnimation);
        animationEngine.startAnimation(die1ShakerAnimation);
        animationEngine.startAnimation(die2ShakerAnimation);
    }

    private Animation createDieShakerAnimation(BoardPiece die, TimeInterval timeline) {
        Point destination = die.displayBox().getLocation();
        Animation animation = new MoveAnimation(die, destination, timeline, new Shaker());
        return animation;
    }

    private Animation createDieNumberAnimation(final BoardPiece die, TimeInterval timeInterval) {
        return new DieNumberAnimation(die, timeInterval, getDiePrefixRolling());
    }

    @Override
    public void onAnimationCompleted(AnimationChangeEvent ace) {
        System.out.println("<nextturn>");
        game.nextTurn();
    }

    @Override
    public String calculateImageNameForPropWithKey(String keyOfProp) {
        String diePrefix;

        diePrefix = getDiePrefix();
        int[] diceThrown = game.diceThrown();

        if (diceThrown != null && diceThrown.length == 2) {
            if (keyOfProp.equals("die1")) {
                return diePrefix + game.diceThrown()[0];
            } else if (keyOfProp.equals("die2")) {
                return diePrefix + game.diceThrown()[1];
            }
        }

        return "idie";
    }

    private String getDiePrefixRolling() {
        String diePrefix;
        if (game.getPlayerInTurn() == Color.BLACK)
            diePrefix = "rdie";
        else
            diePrefix = "bdie";
        return diePrefix;
    }

    private String getDiePrefix() {
        String diePrefix;
        if ((game.getPlayerInTurn() == Color.RED && game.getNumberOfMovesLeft() != 0))
            diePrefix = "rdie";
        else
            diePrefix = "bdie";
        return diePrefix;
    }

}
