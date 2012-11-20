package pasoos.view;

import pasoos.hotgammon.rules.HotGammonFactory;

public interface GameSettings {
    Class<? extends HotGammonFactory> getGameFactoryType();

}
