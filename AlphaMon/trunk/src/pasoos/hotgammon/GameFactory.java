package pasoos.hotgammon;

import pasoos.hotgammon.gameengine.GameImpl;
import pasoos.hotgammon.rules.RulesFactory;

class GameFactory {

    public static Game createGame(Class<? extends RulesFactory> rulesClazz) throws IllegalAccessException, InstantiationException {
        return createGame(rulesClazz.newInstance());
    }

    public static Game createGame(RulesFactory rf) {
        return new GameImpl(rf);
    }

}
