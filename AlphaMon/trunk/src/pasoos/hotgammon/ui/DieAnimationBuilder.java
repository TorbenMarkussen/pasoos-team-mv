package pasoos.hotgammon.ui;

import minidraw.boardgame.BoardPiece;
import minidraw.framework.*;
import pasoos.hotgammon.Game;
import pasoos.hotgammon.easings.Shaker;

import java.awt.*;
import java.util.HashMap;

public class DieAnimationBuilder {
    int numberCount = 6;
    private final int duration;

    public DieAnimationBuilder(int duration) {
        this.duration = duration;
    }

    public Animation build(final BoardPiece die, Point location, final boolean callNextTurn, final Game game, final AnimationEngine animationEngine, final GammonDice gammonDice) {
        final HashMap<Integer, Animation> animationList = new HashMap<Integer, Animation>();
        Shaker shaker = new Shaker();
//        updateDieImage(gammonDice, die);

        for (int i = numberCount; i >= 1; i--) {
            TimeInterval timeline = TimeInterval.fromNow().duration((duration / numberCount) * i);
            Animation a = new MoveAnimation(die, location, timeline, shaker);
            animationList.put(i, a);
            if (i < numberCount) {
                addRollingChangeListener(die, animationEngine, gammonDice, animationList, i, a);
            } else {
                if (callNextTurn)
                    addFinalChangeListener(game, gammonDice, a);
            }
        }
        return animationList.get(1);
    }

    private void addRollingChangeListener(final BoardPiece die, final AnimationEngine animationEngine, final GammonDice gammonDice, final HashMap<Integer, Animation> animationList, int i, Animation a) {
        final int finalI = i;
        a.addAnimationChangeListener(new AnimationChangeListener() {

            @Override
            public void onAnimationCompleted(AnimationChangeEvent ace) {
                System.out.println("<shaking die>");
                Animation nextAnimation = animationList.get(finalI + 1);
                animationEngine.startAnimation(nextAnimation);
                updateDieImage(gammonDice, die);
            }
        });
    }

    private void addFinalChangeListener(final Game game, final GammonDice gammonDice, Animation a) {
        a.addAnimationChangeListener(new AnimationChangeListener() {

            @Override
            public void onAnimationCompleted(AnimationChangeEvent ace) {
                System.out.println("<nextturn>");
                gammonDice.setRolling(false);
                game.nextTurn();
            }
        });
    }

    private void updateDieImage(GammonDice gammonDice, BoardPiece die) {
        String nameOfImageToShow =
                gammonDice.calculateImageNameForPropWithKey(die.toString());
        die.changeImage(nameOfImageToShow);
    }
}
