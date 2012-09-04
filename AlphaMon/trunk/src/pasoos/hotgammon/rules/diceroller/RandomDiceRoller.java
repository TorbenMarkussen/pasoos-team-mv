package pasoos.hotgammon.rules.diceroller;

import pasoos.hotgammon.rules.DiceRoller;

import java.util.Random;

public class RandomDiceRoller implements DiceRoller {

    private Random random;

    public RandomDiceRoller() {
        random = new Random();
        random.setSeed(System.currentTimeMillis());
    }

    @Override
    public int[] roll() {
        return new int[]{random.nextInt(6) + 1, random.nextInt(6) + 1};
    }
}
