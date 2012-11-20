package pasoos.view;

import pasoos.hotgammon.rules.HotGammonFactory;
import pasoos.hotgammon.rules.factory.SemiMonFactory;

public class GameSettingsImpl implements GameSettings {
    public GameSettingsImpl(String[] args) {
    }

    @Override
    public Class<? extends HotGammonFactory> getGameFactoryType() {
        return SemiMonFactory.class;
    }
}
