package pasoos.hotgammon.settings;

import pasoos.hotgammon.Color;
import pasoos.hotgammon.rules.HotGammonFactory;
import pasoos.hotgammon.rules.factory.SemiMonFactory;

import java.util.Properties;

public class GameSettingsImpl implements GameSettings {


    public GameSettingsImpl(String[] args) {
    }

    @Override
    public Class<? extends HotGammonFactory> getGameFactoryType() {
        Properties systemProperties = System.getProperties();

        Class<? extends HotGammonFactory> defaultClass = SemiMonFactory.class;

        String clazzname = systemProperties.getProperty("gammon.type", defaultClass.getSimpleName());

        Class<? extends HotGammonFactory> factoryClazz;
        try {
            factoryClazz = (Class<? extends HotGammonFactory>) Class.forName(clazzname);
        } catch (ClassNotFoundException e) {
            factoryClazz = defaultClass;
        }

        return factoryClazz;
    }

    @Override
    public PlayerType getPlayerType(Color color) {
        Properties systemProperties = System.getProperties();
        String type;

        if (color == Color.BLACK) {
            type = systemProperties.getProperty("black_type", PlayerType.DummyPlayer.toString());
        } else {
            type = systemProperties.getProperty("red_type", PlayerType.DummyPlayer.toString());
        }
        return PlayerType.valueOf(type);
    }

    @Override
    public String getName(Color color) {
        Properties systemProperties = System.getProperties();

        String name;
        if (color == Color.BLACK) {
            name = systemProperties.getProperty("black_name", color.toString());
        } else {
            name = systemProperties.getProperty("red_name", color.toString());
        }

        return name;
    }
}
