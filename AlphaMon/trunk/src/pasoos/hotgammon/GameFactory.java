package pasoos.hotgammon;

import pasoos.hotgammon.gameengine.GameImpl;
import pasoos.hotgammon.rules.RulesFactory;
import pasoos.hotgammon.rules.RulesFactoryImpl;

class GameFactory {

    public static Game Get(HotGammonTypes type) {
        return new GameImpl(new RulesFactoryImpl(type));
    }

    public static Game Get(RulesFactory rf) {
        return new GameImpl(rf);
    }

}
