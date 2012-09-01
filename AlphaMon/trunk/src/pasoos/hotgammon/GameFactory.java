package pasoos.hotgammon;

import pasoos.hotgammon.gameengine.GameImpl;
import pasoos.hotgammon.rules.RulesFactoryImpl;

/**
 * Created with IntelliJ IDEA.
 * User: PASMA00T
 * Date: 29-08-12
 * Time: 12:14
 * To change this template use File | Settings | File Templates.
 */
public class GameFactory {
    public static Game Get(HotGammonTypes type) {
        return new GameImpl(new RulesFactoryImpl(type));
    }
}
