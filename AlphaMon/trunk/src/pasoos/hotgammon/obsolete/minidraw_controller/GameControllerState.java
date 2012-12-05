package pasoos.hotgammon.obsolete.minidraw_controller;

import minidraw.framework.Tool;

public interface GameControllerState extends Tool {
    void entry();

    void exit();

    void setGameContext(GameContext context);
}
