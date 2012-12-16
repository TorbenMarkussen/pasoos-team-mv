package pasoos.hotgammon.animatedgame.ui;

import minidraw.animation.BaseAnimation;
import minidraw.animation.TimeInterval;
import minidraw.boardgame.BoardPiece;

import java.util.Random;

public class DieNumberAnimation extends BaseAnimation {
    private BoardPiece die;
    public double nextImageRefresh;
    private double stepSize = 1 / 6;
    private String figureNamePrefix;
    private Random rollingDieNumberGenerator;

    public DieNumberAnimation(BoardPiece die, TimeInterval timeInterval, String figureNamePrefix) {
        super(die, timeInterval);
        this.die = die;
        this.figureNamePrefix = figureNamePrefix;
        rollingDieNumberGenerator = new Random();
    }

    @Override
    public void begin() {
        nextImageRefresh = stepSize;
    }

    @Override
    public void step() {
        double elapsedPercentage = timeInterval.elapsed();
        if (elapsedPercentage > nextImageRefresh) {
            die.changeImage(getRandomDie());
            nextImageRefresh += stepSize;
        }
    }

    private String getRandomDie() {
        return figureNamePrefix + randomValue();
    }

    private String randomValue() {
        Integer value = rollingDieNumberGenerator.nextInt(6) + 1;
        return value.toString();
    }
}
