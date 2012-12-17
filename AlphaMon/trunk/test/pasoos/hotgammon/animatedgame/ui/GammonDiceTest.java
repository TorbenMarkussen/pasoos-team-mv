package pasoos.hotgammon.animatedgame.ui;

import minidraw.animation.Animation;
import minidraw.animation.engine.AnimationEngine;
import minidraw.boardgame.BoardPiece;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import pasoos.hotgammon.Game;
import pasoos.hotgammon.Location;
import pasoos.hotgammon.animatedgame.GammonStateMachine;
import pasoos.hotgammon.sounds.SoundResource;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;
import static pasoos.hotgammon.Color.RED;

public class GammonDiceTest {

    GammonDice gammonDice;
    Game game;
    GammonStateMachine stateMachine;
    AnimationEngine engine;
    SoundResource sound;
    private BoardPiece die1;
    private BoardPiece die2;

    @Before
    public void setup() {
        game = mock(Game.class);
        stateMachine = mock(GammonStateMachine.class);
        engine = mock(AnimationEngine.class);
        sound = mock(SoundResource.class);
        die1 = mock(BoardPiece.class);
        die2 = mock(BoardPiece.class);
        Map<String, BoardPiece> diceMap = new HashMap<String, BoardPiece>();
        diceMap.put("die1", die1);
        diceMap.put("die2", die2);
        gammonDice = new GammonDice(game, engine, sound, diceMap);
    }

    @Ignore
    @Test
    public void should_request_dice_rool() {
//        gd.rollRequest();
//        verify(stateMachine, times(1)).rollDiceRequest();
    }

    @Test
    public void should_animate_on_rool() {
        when(game.getCount(any(Location.class))).thenReturn(0);
        when(game.getColor(any(Location.class))).thenReturn(RED);
        when(die1.displayBox()).thenReturn(new Rectangle(0, 0));
        when(die2.displayBox()).thenReturn(new Rectangle(0, 0));

        gammonDice.roll();

        verify(sound, times(1)).playDiceRollerSound();
        verify(engine, times(4)).startAnimation(any(Animation.class));
    }

    @Test
    public void should_invoke_next_turn_on_animation_compleated() {
        gammonDice.onAnimationCompleted(null);
        verify(game, times(1)).nextTurn();
    }
}
