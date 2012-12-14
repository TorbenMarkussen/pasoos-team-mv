package pasoos.hotgammon.animatedgame.ui;

import minidraw.animation.*;
import minidraw.animation.engine.AnimationEngine;
import minidraw.boardgame.BoardPiece;
import minidraw.boardgame.PropAppearanceStrategy;
import pasoos.hotgammon.Color;
import pasoos.hotgammon.Game;
import pasoos.hotgammon.easings.Shaker;
import pasoos.hotgammon.animatedgame.GammonStateMachine;
import pasoos.hotgammon.sounds.SoundResource;

import java.util.Random;

public class GammonDice implements PropAppearanceStrategy {

    private Game game;
    private GammonStateMachine gammonStateMachine;
    private BoardPiece die1;
    private BoardPiece die2;
    private AnimationEngine animationEngine;
    private SoundResource soundResource;
    private boolean rolling = false;
    private Random rollingDieNumberGenerator;

    public GammonDice(Game game, GammonStateMachine state, AnimationEngine animationEngine, SoundResource soundResource) {
        this.game = game;
        this.gammonStateMachine = state;
        rollingDieNumberGenerator = new Random();
        this.animationEngine = animationEngine;
        this.soundResource = soundResource;
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

    public void roll() {
        rolling = true;
        soundResource.playDiceRollerSound();
        updateDieColor();
        animateDieRoll();
    }

    private void updateDieColor() {
        die1.changeImage(calculateImageNameForPropWithKey(die1.toString()));
        die2.changeImage(calculateImageNameForPropWithKey(die2.toString()));
    }

    private void animateDieRoll() {
        TimeInterval timeInterval = TimeInterval.fromNow().duration(1000);
        Animation dieNumberAnimation1 = getDieNumberAnimation(die1, timeInterval);
        Animation dieNumberAnimation2 = getDieNumberAnimation(die2, timeInterval);

        Animation dieShakerAnimation1 = getDieShakerAnimation(die1, timeInterval, true);
        Animation dieShakerAnimation2 = getDieShakerAnimation(die2, timeInterval, false);

        animationEngine.startAnimation(dieNumberAnimation1);
        animationEngine.startAnimation(dieNumberAnimation2);
        animationEngine.startAnimation(dieShakerAnimation1);
        animationEngine.startAnimation(dieShakerAnimation2);
    }

    private Animation getDieShakerAnimation(BoardPiece die, TimeInterval timeline, boolean callNextTurn) {

        Animation animation = new MoveAnimation(die, die.displayBox().getLocation(), timeline, new Shaker());
        if (callNextTurn)
            addFinalChangeListener(animation, this);
        return animation;
    }

    private void addFinalChangeListener(Animation a, final GammonDice gammonDice) {
        a.addAnimationChangeListener(new AnimationChangeListener() {

            @Override
            public void onAnimationCompleted(AnimationChangeEvent ace) {
                System.out.println("<nextturn>");
                gammonDice.setRolling(false);
                game.nextTurn();
            }
        });
    }

    private Animation getDieNumberAnimation(final BoardPiece die, TimeInterval timeInterval) {
        final double stepSize = 1.0 / 6.0;
        return new BaseAnimation(die, timeInterval) {
            public double nextImageRefresh;

            @Override
            public void begin() {
                nextImageRefresh = stepSize;
            }

            @Override
            public void step() {
                double elapsedTime = timeInterval.elapsed();
                if (elapsedTime > nextImageRefresh) {
                    die.changeImage(calculateImageNameForPropWithKey(die.toString()));
                    nextImageRefresh += stepSize;
                }
            }
        };
    }

    @Override
    public String calculateImageNameForPropWithKey(String keyOfProp) {
        String diePrefix;

        diePrefix = getDiePrefix();

        if (rolling) {
            return getDiePrefixRolling() + getRandomValue();
        } else if (keyOfProp.equals("die1")) {
            return diePrefix + game.diceThrown()[0];
        } else if (keyOfProp.equals("die2")) {
            return diePrefix + game.diceThrown()[1];
        }
        return "die0";
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

    private String getRandomValue() {
        Integer value = rollingDieNumberGenerator.nextInt(6) + 1;
        return value.toString();
    }

    public void setRolling(boolean rolling) {
        this.rolling = rolling;
    }
}
