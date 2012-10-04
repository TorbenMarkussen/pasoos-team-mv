package pasoos.hotgammon.gerry;

import pasoos.hotgammon.Game;
import pasoos.hotgammon.Location;

public class GameAdapterImpl implements GameAdapter {
    private Game game;

    GameAdapterImpl(Game game) {
        this.game = game;
    }

    @Override
    public int[] getBoard() {
        int[] board = new int[28];
        board[0] = game.getCount(Location.B1) * game.getColor(Location.B1).getSign();
        //...
        //...
        return board;
    }

    @Override
    public int[] getDices() {
        return game.diceValuesLeft();
    }

    @Override
    public void move(int from, int to) {
        Location fl = ConvertGerryPositionToLocation(from);
        Location tl = ConvertGerryPositionToLocation(to);
        game.move(fl, tl);
    }

    private Location ConvertGerryPositionToLocation(int from) {
        return null;  //To change body of created methods use File | Settings | File Templates.
    }
}
