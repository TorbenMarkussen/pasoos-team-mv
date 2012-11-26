package pasoos.view;

import pasoos.hotgammon.Color;
import pasoos.hotgammon.rules.HotGammonFactory;

public interface GameSettings {
    Class<? extends HotGammonFactory> getGameFactoryType();

    PlayerType getPlayerType(Color color);

    String getName(Color color);
}
