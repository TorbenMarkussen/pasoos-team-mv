package pasoos.hotgammon.rules.diceroller;

import pasoos.hotgammon.rules.DiceRoller;

public class FixedDiceRoller implements DiceRoller {

    private int nextDice = 1;

    @Override
    public int[] roll() {
        int[] dice = new int[2];
        dice[0] = nextDice++;
        dice[1] = nextDice++;

        if (nextDice > 6)
            nextDice = 1;

        return dice;
    }
}
