package pasoos.hotgammon.rules.factory;

import pasoos.hotgammon.rules.DiceRoller;
import pasoos.hotgammon.rules.diceroller.RandomDiceRoller;

public class EpsilonMonRules extends AlphaMonRules {
    @Override
    public DiceRoller createDiceRoller() {
        return new RandomDiceRoller();
    }
}
