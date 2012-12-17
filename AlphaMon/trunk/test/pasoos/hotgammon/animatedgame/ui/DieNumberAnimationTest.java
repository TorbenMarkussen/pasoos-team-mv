package pasoos.hotgammon.animatedgame.ui;


import minidraw.animation.Animation;
import minidraw.animation.TimeInterval;
import minidraw.boardgame.BoardPiece;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class DieNumberAnimationTest {

    @Test
    public void should_update_image_six_times_during_animation() {
        double callInterval = 1.00 / 100.0;
        double currentElapsed = callInterval;
        BoardPiece die = mock(BoardPiece.class);
        TimeInterval timeInterval = mock(TimeInterval.class);
        Animation a = new DieNumberAnimation(die, timeInterval, "die1");
        a.begin();
        for (int i = 1; i <= 100; i++) {
            when(timeInterval.elapsed()).thenReturn(currentElapsed);
            currentElapsed += callInterval;
            a.step();
        }
        verify(die, times(6)).changeImage(anyString());
    }
}
