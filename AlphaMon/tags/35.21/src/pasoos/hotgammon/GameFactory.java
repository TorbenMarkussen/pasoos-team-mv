package pasoos.hotgammon;

import pasoos.hotgammon.gameengine.GameImpl;
import pasoos.hotgammon.rules.HotGammonFactory;

class GameFactory {

    public static Game createGame(Class<? extends HotGammonFactory> rulesClazz) throws IllegalAccessException, InstantiationException {
        return createGame(rulesClazz.newInstance());
    }

    public static Game createGame(HotGammonFactory rf) {
        return new GameImpl(rf);
    }

}
