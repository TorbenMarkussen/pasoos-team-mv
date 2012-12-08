package pasoos.hotgammon.ui;

import minidraw.boardgame.BoardPiece;
import minidraw.boardgame.PropAppearanceStrategy;
import minidraw.framework.Animation;
import minidraw.framework.AnimationEngine;
import pasoos.hotgammon.Color;
import pasoos.hotgammon.Game;
import pasoos.hotgammon.gamestatemachine.GammonStateMachine;
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
    private DieAnimationBuilder dieAnimation;

    public GammonDice(Game game, GammonStateMachine state) {
        this.game = game;
        this.gammonStateMachine = state;
        dieAnimation = new DieAnimationBuilder(1000);
        rollingDieNumberGenerator = new Random();
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
        soundResource.playDiceRollerSound();
        rolling = true;
        die1.changeImage(calculateImageNameForPropWithKey(die1.toString()));
        die2.changeImage(calculateImageNameForPropWithKey(die2.toString()));

        Animation a1 = dieAnimation.build(die1, die1.displayBox().getLocation(), true, game, animationEngine, this);
        Animation a2 = dieAnimation.build(die2, die2.displayBox().getLocation(), false, game, animationEngine, this);

        animationEngine.startAnimation(a1);
        animationEngine.startAnimation(a2);
    }

    public void setAnimationEngine(AnimationEngine animationEngine) {
        this.animationEngine = animationEngine;
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

    public void setSoundEngine(SoundResource soundEngine) {
        soundResource = soundEngine;
    }

    public void setRolling(boolean rolling) {
        this.rolling = rolling;
    }
}
