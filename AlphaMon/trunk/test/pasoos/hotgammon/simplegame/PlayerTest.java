package pasoos.hotgammon.simplegame;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pasoos.hotgammon.Color;
import pasoos.hotgammon.Game;
import pasoos.hotgammon.Location;
import pasoos.hotgammon.ai.AIPlayer;
import pasoos.hotgammon.ai.GammonMove;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PlayerTest {
    @Mock
    private Game game;

    @Mock
    private AIPlayer aiPlayer;

    @Mock
    private Runnable Invoker;


    @Test
    public void should_invoke_nextTurn() {
        Player player = createPlayer(Color.BLACK);
        when(game.getNumberOfMovesLeft()).thenReturn(0);
        when(game.getPlayerInTurn()).thenReturn(Color.RED);

        player.checkerMove(Location.B1, Location.B2);

        verify(Invoker).run();
    }

    @Test
    public void should_not_invoke_nextTurn_if_same_playercolor() {
        Player player = createPlayer(Color.BLACK);
        when(game.getNumberOfMovesLeft()).thenReturn(0);
        when(game.getPlayerInTurn()).thenReturn(Color.BLACK);

        player.checkerMove(Location.B1, Location.B2);

        verify(Invoker, times(0)).run();
    }

    @Test
    public void should_commit_ai_player_move() {
        Player player = createPlayer(Color.BLACK);
        when(game.getNumberOfMovesLeft()).thenReturn(2);
        when(game.getPlayerInTurn()).thenReturn(Color.BLACK);
        when(game.diceThrown()).thenReturn(new int[]{4, 2});
        List<GammonMove> moves = new ArrayList<GammonMove>();
        moves.add(new GammonMove(Location.B1, Location.B5));
        moves.add(new GammonMove(Location.B5, Location.B7));

        when(aiPlayer.getMoves()).thenReturn(moves);

        player.diceRolled(new int[]{4, 2});

        InOrder inOrder = inOrder(aiPlayer, game);
        inOrder.verify(aiPlayer).play();
        inOrder.verify(aiPlayer).getMoves();
        inOrder.verify(game).move(Location.B1, Location.B5);
        inOrder.verify(game).move(Location.B5, Location.B7);

    }


    private Player createPlayer(Color color) {
        return new Player(game, aiPlayer, color) {
            @Override
            protected void invokeNextTurn() {
                Invoker.run();
            }
        };
    }
}
