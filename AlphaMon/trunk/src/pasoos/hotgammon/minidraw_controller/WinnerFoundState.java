package pasoos.hotgammon.minidraw_controller;

public class WinnerFoundState extends GameControllerStateImpl {
    public WinnerFoundState(GameContext gameContext) {
        super(gameContext);
    }

    @Override
    public void entry() {
        gameContext.updateStatusText("Game winner is " + hgvm.winner());
    }
}
