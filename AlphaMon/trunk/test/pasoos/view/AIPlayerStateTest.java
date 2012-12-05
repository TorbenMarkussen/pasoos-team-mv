package pasoos.view;

import minidraw.boardgame.BoardPiece;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import pasoos.hotgammon.Game;
import pasoos.hotgammon.ai.AIPlayer;
import pasoos.hotgammon.minidraw_controller.GammonMove;
import pasoos.hotgammon.gamestatemachine.AIPlayerState;
import pasoos.hotgammon.gamestatemachine.StateContext;
import pasoos.hotgammon.gamestatemachine.StateId;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static pasoos.hotgammon.Location.*;

public class AIPlayerStateTest {

    private StateContext context;
    private AIPlayerState playerState;
    private AIPlayer aiPlayer;
    private Game game;

    @Before
    public void setup() {
        context = mock(StateContext.class);
        aiPlayer = mock(AIPlayer.class);
        game = mock(Game.class);

        when(context.getGame()).thenReturn(game);
        playerState = new AIPlayerState(StateId.BlackPlayer, "gerry", aiPlayer);
        playerState.setContext(context);
    }

    @Test
    public void should_not_roll_dice_on_first_entry() {
        playerState.onEntry();
        verify(context, times(0)).rollDice();
    }

    @Test
    public void should_roll_dice_on_2nd_entry() {
        playerState.onEntry();
        playerState.onEntry();
        verify(context).rollDice();
    }

    @Test
    public void should_invoke_gerry_play_on_dice_rolled() {
        playerState.diceRolled(new int[]{4, 2});
        verify(aiPlayer).play();
    }

    @Ignore
    @Test
    public void should_invoke_animation_for_ai_moves() {
        List<GammonMove> moves = new ArrayList<GammonMove>();
        moves.add(new GammonMove(B1, B2));
        moves.add(new GammonMove(B3, B4));

        BoardPiece b1Piece = mock(BoardPiece.class);
        when(b1Piece.displayBox()).thenReturn(new Rectangle(0, 0, 42, 42));

        BoardPiece b3Piece = mock(BoardPiece.class);
        when(b3Piece.displayBox()).thenReturn(new Rectangle(0, 0, 42, 42));

        when(game.getCount(B1)).thenReturn(1);
        when(aiPlayer.getMoves()).thenReturn(moves);

        playerState.diceRolled(new int[]{4, 2});
        verify(aiPlayer).play();

        //verify(context).startAnimation(any(Animation.class), eq(Location.B1), eq(Location.B2));

        playerState.checkerMoved(B1, B2);

        //verify(context).startAnimation(any(Animation.class), eq(Location.B3), eq(Location.B4));

        playerState.checkerMoved(B3, B4);

    }

    @Test
    public void should_something_something() {
    }
}
