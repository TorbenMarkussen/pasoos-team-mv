package pasoos.hotgammon.animatedgame;

import minidraw.animatedboard.AnimatedBoard;
import minidraw.animatedboard.AnimatedBoardDrawing;
import minidraw.animatedboard.AnimatedBoardDrawingFactory;
import minidraw.animation.Animation;
import minidraw.animation.engine.AnimationEngine;
import minidraw.boardgame.BoardPiece;
import minidraw.boardgame.PropAppearanceStrategy;
import org.junit.Test;
import pasoos.hotgammon.Color;
import pasoos.hotgammon.Location;
import pasoos.hotgammon.animatedgame.ui.HotgammonAnimatedBoardDrawingFactory;
import pasoos.hotgammon.builder.PieceFactoryBuilder;
import pasoos.hotgammon.builder.minidrawfactories.HotgammonPieceFactory;

import java.awt.*;

import static org.mockito.Mockito.*;

public class AnimatedBoardDrawingTest {

    @Test
    public void should_make_animated_move() {
        PropAppearanceStrategy dice = mock(PropAppearanceStrategy.class);
        AnimationEngine animationEngine = mock(AnimationEngine.class);
        PieceFactoryBuilder pieceFactory = new HotgammonPieceFactory();
        AnimatedBoardDrawingFactory factory = new HotgammonAnimatedBoardDrawingFactory(pieceFactory.build(), dice, animationEngine);
        AnimatedBoard animatedBoard = new AnimatedBoardDrawing(factory);

        BoardPiece piece = mock(BoardPiece.class);
        pieceFactory.addPiece(Location.B1, Color.BLACK, piece);
        when(piece.displayBox()).thenReturn(new Rectangle(0, 0));

        animatedBoard.moveAnimated(Location.B1, Location.B2, null);

        verify(animationEngine, times(1)).startAnimation(any(Animation.class));
    }
}