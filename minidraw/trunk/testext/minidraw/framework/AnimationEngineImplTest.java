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
    public void should_stop_timer_on_idle() {
        Animation a = createDefaultAnimationMock();
        animationEngine.startAnimation(a);

        pulseTimeout(3);
        when(a.isCompleted()).thenReturn(true);
        pulseTimeout(1);

        verify(a, times(1)).begin();
        verify(a, times(4)).step();
        verify(a, times(1)).end();

        verify(mockedAnimationTimer, times(1)).stop();

    }

    @Test
    public void should_run_begin_on_animation_on_timer_pulse() {
        Animation a = createDefaultAnimationMock();
        animationEngine.startAnimation(a);
        pulseTimeout(1);
        verify(a, times(1)).begin();
        verify(a, times(1)).step();
    }

    @Test
    public void should_run_begin_once_and_step_twice_for_two_timer_pulses() {
        Animation a = createDefaultAnimationMock();
        animationEngine.startAnimation(a);
        pulseTimeout(2);
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
        pulseTimeout(3);
        InOrder inorder = inOrder(a);
        inorder.verify(a).begin();
        inorder.verify(a).step();
        inorder.verify(a).end();
        inorder.verifyNoMoreInteractions();
    }

    @Test
    public void should_not_start_animation_until_startable() {
        Animation a = createDefaultAnimationMock();
        when(a.isStartable()).thenReturn(false);
        animationEngine.startAnimation(a);
        pulseTimeout(2);
        verify(a, times(0)).begin();

        when(a.isStartable()).thenReturn(true);
        pulseTimeout(1);
        verify(a, times(1)).begin();

    }

    @Test
    public void should_two_animations_simultaneously() {
        Animation a1 = createDefaultAnimationMock();
        Animation a2 = createDefaultAnimationMock();
        animationEngine.startAnimation(a1);
        animationEngine.startAnimation(a2);

        pulseTimeout(1);
        verify(a1, times(1)).begin();
        verify(a1, times(1)).step();
        verify(a2, times(1)).begin();
        verify(a2, times(1)).step();
    }

    @Test
    public void should_survive_runtime_exception_in_step() {
        Animation a = createDefaultAnimationMock();
        animationEngine.startAnimation(a);
        pulseTimeout(1);
        verify(a, times(1)).begin();
        verify(a, times(1)).step();

        doThrow(new RuntimeException()).when(a).step();
        pulseTimeout(1);
        verify(a, times(1)).begin();
        verify(a, times(2)).step();

        when(a.isCompleted()).thenReturn(true);
        pulseTimeout(1);
        verify(a, times(1)).begin();
        verify(a, times(3)).step();
        verify(a, times(1)).end();
    }

    @Test
    public void should_survive_runtime_exception_in_begin() {
        Animation a = createDefaultAnimationMock();
        doThrow(new RuntimeException()).when(a).begin();
        animationEngine.startAnimation(a);
        pulseTimeout(1);
        verify(a, times(1)).begin();
        verify(a, times(1)).step();
        verify(a, times(0)).end();

        when(a.isCompleted()).thenReturn(true);
        pulseTimeout(2);
        verify(a, times(1)).begin();
        verify(a, times(2)).step();
        verify(a, times(1)).end();
    }

    @Test
    public void should_survive_runtime_exception_in_end() {
        Animation a = createDefaultAnimationMock();
        doThrow(new RuntimeException()).when(a).end();
        animationEngine.startAnimation(a);
        pulseTimeout(1);
        verify(a, times(1)).begin();
        verify(a, times(1)).step();
        verify(a, times(0)).end();

        when(a.isCompleted()).thenReturn(true);
        pulseTimeout(2);
        verify(a, times(1)).begin();
        verify(a, times(2)).step();
        verify(a, times(1)).end();
    }

    private void pulseTimeout(int count) {
        for (int i = 0; i < count; i++)
            timerRunnableArgument.getValue().run();
    }

    @Test
    public void should_not_stop_timer_fo_overlapping_animations() {
        Animation a1 = createDefaultAnimationMock();
        animationEngine.startAnimation(a1);
        pulseTimeout(10);
        verify(a1, times(1)).begin();
        verify(a1, times(10)).step();
        verify(a1, times(0)).end();

        when(a1.isCompleted()).thenReturn(true);
        Animation a2 = createDefaultAnimationMock();
        animationEngine.startAnimation(a2);
        pulseTimeout(1);
        verify(a1, times(1)).begin();
        verify(a1, times(11)).step();
        verify(a1, times(1)).end();

        verify(mockedAnimationTimer, times(0)).stop();
        verify(a2, times(1)).begin();
        verify(a2, times(1)).step();
        verify(a2, times(0)).end();
    }

}
