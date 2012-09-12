package pasoos.hotgammon.rules.factory;

import pasoos.hotgammon.rules.DiceRoller;
import pasoos.hotgammon.rules.diceroller.RandomDiceRoller;

public class EpsilonMonFactoryImpl extends AlphaMonFactoryImpl {
    @Override
    public DiceRoller createDiceRoller() {
        return new RandomDiceRoller();
    }
}
