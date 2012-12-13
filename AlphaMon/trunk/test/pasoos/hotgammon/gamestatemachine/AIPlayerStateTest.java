package pasoos.hotgammon.gamestatemachine;

import minidraw.animatedboard.MoveAnimationCallbacks;
import minidraw.boardgame.BoardPiece;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import pasoos.hotgammon.Game;
import pasoos.hotgammon.Location;
import pasoos.hotgammon.ai.AIPlayer;
import pasoos.hotgammon.ai.GammonMove;
import pasoos.hotgammon.sounds.SoundResource;

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
    private SoundResource soundMock;
    private BoardPiece b1Piece;
    private BoardPiece b3Piece;

    @Before
    public void setup() {
        context = mock(StateContext.class);
        aiPlayer = mock(AIPlayer.class);
        game = mock(Game.class);
        soundMock = mock(SoundResource.class);
        configureDefaultContextBehaviour();
        playerState = new AIPlayerState(StateId.BlackPlayer, "gerry", aiPlayer);
        playerState.setContext(context);

        b1Piece = mock(BoardPiece.class);
        when(b1Piece.displayBox()).thenReturn(new Rectangle(0, 0, 42, 42));

        b3Piece = mock(BoardPiece.class);
        when(b3Piece.displayBox()).thenReturn(new Rectangle(0, 0, 42, 42));
    }

    private void configureDefaultContextBehaviour() {
        when(context.getSoundMachine()).thenReturn(soundMock);
        when(context.getGame()).thenReturn(game);

        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Location from = (Location) invocation.getArguments()[0];
                Location to = (Location) invocation.getArguments()[1];

                MoveAnimationCallbacks cb = (MoveAnimationCallbacks) invocation.getArguments()[2];
                cb.beforeMoveStart(from, to);
                cb.afterMoveStart(from, to);
                cb.beforeMoveEnd(from, to);
                cb.afterMoveEnd(from, to);
                return null;
            }
        }).when(context).moveAnimated(any(Location.class), any(Location.class), any(MoveAnimationCallbacks.class));
    }

    @Test
    public void should_roll_dice_on_entry() {
        playerState.onEntry();
        verify(context).rollDice();
    }

    @Test
    public void should_invoke_gerry_play_on_dice_rolled() {
        playerState.diceRolled(new int[]{4, 2});
        verify(aiPlayer).play();
    }

    @Test
    public void should_invoke_animation_for_ai_moves() {
        List<GammonMove> moves = new ArrayList<GammonMove>();
        moves.add(new GammonMove(B1, B2));
        moves.add(new GammonMove(B3, B4));

        when(game.getCount(B1)).thenReturn(1);
        when(aiPlayer.getMoves()).thenReturn(moves);

        playerState.diceRolled(new int[]{4, 2});
        InOrder inOrder = inOrder(aiPlayer, game, context);
        inOrder.verify(aiPlayer).play();

        inOrder.verify(context).moveAnimated(eq(Location.B1), eq(Location.B2), any(MoveAnimationCallbacks.class));
        inOrder.verify(game).move(B1, B2);

        playerState.checkerMoved(B1, B2);

        inOrder.verify(context).moveAnimated(eq(Location.B3), eq(Location.B4), any(MoveAnimationCallbacks.class));
        inOrder.verify(game).move(B3, B4);

        playerState.checkerMoved(B3, B4);

        playerState.turnEnded();

        inOrder.verify(context).playerTurnEnded(playerState);

    }

    @Test
    public void should_not_invoke_playerTurnEnded_until_animation_ended() {
        List<GammonMove> moves = new ArrayList<GammonMove>();
        moves.add(new GammonMove(B1, B2));
        moves.add(new GammonMove(B3, B4));

        when(game.getCount(B1)).thenReturn(1);
        when(aiPlayer.getMoves()).thenReturn(moves);

        playerState.diceRolled(new int[]{4, 2});
        InOrder inOrder = inOrder(aiPlayer, context);
        inOrder.verify(aiPlayer).play();

        inOrder.verify(context).moveAnimated(eq(Location.B1), eq(Location.B2), any(MoveAnimationCallbacks.class));

        playerState.checkerMoved(B1, B2);
        playerState.turnEnded();

        inOrder.verify(context).moveAnimated(eq(Location.B3), eq(Location.B4), any(MoveAnimationCallbacks.class));

        playerState.checkerMoved(B3, B4);

        inOrder.verify(context).playerTurnEnded(playerState);
    }


}
