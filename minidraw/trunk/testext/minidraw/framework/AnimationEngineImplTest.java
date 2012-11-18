package minidraw.framework;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;

import static org.mockito.Mockito.*;

public class AnimationEngineImplTest {
    private AnimationTimer mockedAnimationTimer;
    private ArgumentCaptor<Runnable> timerRunnableArgument;
    private AnimationEngineImpl animationEngine;

    @Before
    public void setUp() throws Exception {
        mockedAnimationTimer = mock(AnimationTimer.class);
        timerRunnableArgument = ArgumentCaptor.forClass(Runnable.class);

        animationEngine = new AnimationEngineImpl(mockedAnimationTimer);
        verify(mockedAnimationTimer).setTimeoutReceiver(timerRunnableArgument.capture());
    }

    @Test
    public void should_bind_to_timer_source() {
        Assert.assertTrue(timerRunnableArgument.getValue().equals(animationEngine));
    }

    @Test
    public void should_start_timer_when_starting_animation() {
        Animation a = createDefaultAnimationMock();
        animationEngine.startAnimation(a);
        verify(mockedAnimationTimer, times(1)).start();
    }

    @Test
    public void should_run_begin_on_animation_on_timer_pulse() {
        Animation a = createDefaultAnimationMock();
        animationEngine.startAnimation(a);
        timerRunnableArgument.getValue().run();
        verify(a, times(1)).begin();
        verify(a, times(1)).step();
    }

    @Test
    public void should_run_begin_once_and_step_twice_for_two_timer_pulses() {
        Animation a = createDefaultAnimationMock();
        animationEngine.startAnimation(a);
        timerRunnableArgument.getValue().run();
        timerRunnableArgument.getValue().run();
        verify(a, times(1)).begin();
        verify(a, times(2)).step();
    }

    private Animation createDefaultAnimationMock() {
        Animation a = mock(Animation.class);
        when(a.isStartable()).thenReturn(true);
        when(a.isCompleted()).thenReturn(false);
        return a;
    }

    @Test
    public void should_run_begin_step_end_for_animation_that_completes_immediately() {
        Animation a = createDefaultAnimationMock();
        when(a.isCompleted()).thenReturn(true);
        animationEngine.startAnimation(a);
        timerRunnableArgument.getValue().run();
        timerRunnableArgument.getValue().run();
        timerRunnableArgument.getValue().run();
        InOrder inorder = inOrder(a);
        inorder.verify(a).begin();
        inorder.verify(a).step();
        inorder.verify(a).end();
        inorder.verifyNoMoreInteractions();
    }

}
