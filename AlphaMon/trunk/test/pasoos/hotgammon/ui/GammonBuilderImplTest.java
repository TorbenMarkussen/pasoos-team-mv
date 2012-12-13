package pasoos.hotgammon.ui;

import minidraw.animatedboard.AnimatedBoard;
import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Factory;
import org.junit.Before;
import org.junit.Test;
import pasoos.hotgammon.Color;
import pasoos.hotgammon.Game;
import pasoos.hotgammon.Location;
import pasoos.hotgammon.gamestatemachine.GameEventDecorator;
import pasoos.hotgammon.gamestatemachine.GameStateController;
import pasoos.hotgammon.rules.factory.SemiMonFactory;
import pasoos.hotgammon.settings.PlayerType;
import pasoos.hotgammon.ui.status.StatusMonitor;

import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class GammonBuilderImplTest {

    private GammonBuilderImpl gammonBuilder;

    @Before
    public void setup() {
        gammonBuilder = new GammonBuilderImplTestable();
    }

    @Test
    public void should_create_default_game() {

        gammonBuilder.setGameType(SemiMonFactory.class);

        assertTrue(gammonBuilder.getGame() instanceof GameEventDecorator);
        Game game = gammonBuilder.getGame();

        gammonBuilder.setPlayer(Color.BLACK, PlayerType.ManualPlayer, "black");
        gammonBuilder.setPlayer(Color.RED, PlayerType.ManualPlayer, "red");

        for (Location l : Location.values()) {
            if (game.getColor(l) != Color.NONE) {
                for (int i = 0; i < game.getCount(l); i++) {
                    gammonBuilder.addPiece(l, game.getColor(l));
                }
            }
        }

        gammonBuilder.addDie("die1");
        gammonBuilder.addDie("die2");

        gammonBuilder.build();

        assertTrue(gammonBuilder.getController() instanceof GameStateController);

        Factory viewFactory = gammonBuilder.createViewFactory();
        DrawingEditor editor = mock(DrawingEditor.class);
        Drawing drawing = viewFactory.createDrawing(editor);
        assertTrue(drawing instanceof AnimatedBoard);


        assertTrue(gammonBuilder.getStatusMonitor() instanceof StatusMonitor);
    }
}
