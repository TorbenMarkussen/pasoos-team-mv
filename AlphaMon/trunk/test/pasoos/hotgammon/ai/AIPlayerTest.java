package pasoos.hotgammon.ai;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pasoos.hotgammon.Color;
import pasoos.hotgammon.Game;
import pasoos.hotgammon.Location;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static pasoos.hotgammon.Color.BLACK;
import static pasoos.hotgammon.Color.RED;
import static pasoos.hotgammon.Location.*;

@RunWith(MockitoJUnitRunner.class)
public abstract class AIPlayerTest {

    @Mock
    protected Game game;

    protected AIPlayer aiPlayer;

    @Test
    public void should_move_two_checkers_with_initial_board() {
        configureDefaultBoard(game);

        when(game.diceThrown()).thenReturn(new int[]{6, 1});
        aiPlayer.play();
        List<GammonMove> moves = aiPlayer.getMoves();
        assertEquals(2, moves.size());
    }

    @Test
    public void should_move_four_checkers_with_doubledice() {
        configureDefaultBoard(game);

        when(game.diceThrown()).thenReturn(new int[]{1, 1});
        aiPlayer.play();
        List<GammonMove> moves = aiPlayer.getMoves();
        assertEquals(4, moves.size());
    }

    @Test
    public void should_give_empty_movelist_for_blocked_move() {
        configureBlockedBoard(game);

        when(game.diceThrown()).thenReturn(new int[]{4, 2});
        aiPlayer.play();
        List<GammonMove> moves = aiPlayer.getMoves();
        assertEquals(0, moves.size());
    }

    private void configureBlockedBoard(Game game) {
        Map<Location, Integer> board = new HashMap<Location, Integer>();
        for (Location l : Location.values()) {
            board.put(l, 0);
        }
        board.put(R1, RED.getSign() * 2);
        board.put(R2, RED.getSign() * 2);
        board.put(R3, RED.getSign() * 2);
        board.put(R4, RED.getSign() * 2);
        board.put(R5, RED.getSign() * 2);
        board.put(R6, RED.getSign() * 2);

        board.put(R_BAR, RED.getSign() * 3);

        board.put(B1, BLACK.getSign() * 2);
        board.put(B2, BLACK.getSign() * 2);
        board.put(B3, BLACK.getSign() * 2);
        board.put(B4, BLACK.getSign() * 2);
        board.put(B5, BLACK.getSign() * 2);
        board.put(B6, BLACK.getSign() * 2);

        board.put(B_BAR, BLACK.getSign() * 3);

        for (Location l : Location.values()) {
            when(game.getCount(l)).thenReturn(Math.abs(board.get(l)));
            when(game.getColor(l)).thenReturn(Color.getColorFromNumerical(board.get(l)));
        }

    }


    protected void configureDefaultBoard(Game game) {
        Map<Location, Integer> board = new HashMap<Location, Integer>();
        for (Location l : Location.values()) {
            board.put(l, 0);
        }
        board.put(R1, BLACK.getSign() * 2);
        board.put(R6, RED.getSign() * 5);
        board.put(R8, RED.getSign() * 3);
        board.put(R12, BLACK.getSign() * 5);

        board.put(B1, RED.getSign() * 2);
        board.put(B6, BLACK.getSign() * 5);
        board.put(B8, BLACK.getSign() * 3);
        board.put(B12, RED.getSign() * 5);

        for (Location l : Location.values()) {
            when(game.getCount(l)).thenReturn(Math.abs(board.get(l)));
            when(game.getColor(l)).thenReturn(Color.getColorFromNumerical(board.get(l)));
        }
    }

}
