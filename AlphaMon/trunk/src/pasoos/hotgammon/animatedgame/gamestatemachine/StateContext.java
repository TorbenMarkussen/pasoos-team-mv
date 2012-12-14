package pasoos.hotgammon.animatedgame.gamestatemachine;

import minidraw.animatedboard.MoveAnimationCallbacks;
import pasoos.hotgammon.Game;
import pasoos.hotgammon.Location;
import pasoos.hotgammon.animatedgame.ui.status.StatusObserver;
import pasoos.hotgammon.sounds.SoundResource;

public interface StateContext {
    Game getGame();

    void setState(StateId stateId);

    void updateStatus(GammonState state, String message);

    void rollDice();

    void notifyPieceMovedEvent(Location from, Location to);

    void notifyDiceRolled(int[] values);

    String getWinnerName();

    SoundResource getSoundMachine();

    void moveAnimated(Location from, Location to, MoveAnimationCallbacks<Location> cb);

    void addStatusObserver(StatusObserver statusObserver);

    GammonState getCurrentState();

    void playerTurnEnded(GammonPlayer player);
}
