package minidraw.framework;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;

public class MoveAnimationTest {
    private Figure figureMock;
    private TimeInterval timeIntervalMock;
    private EasingFunctionStrategy easingFunctionMock;
    private MoveAnimation moveAnimation;
    private Point begin;
    private Point end;

    @Before
    public void setUp() throws Exception {
        figureMock = mock(Figure.class);
        timeIntervalMock = mock(TimeInterval.class);
        easingFunctionMock = mock(EasingFunctionStrategy.class);
        begin = new Point(1000, 1000);
        end = new Point(900, 1100);
        when(figureMock.displayBox()).thenReturn(new Rectangle(begin, new Dimension(10, 10)));
        moveAnimation = new MoveAnimation(figureMock, end, timeIntervalMock, easingFunctionMock);

    }

    @Test
    public void should_be_startable_depending_on_time_interval() {
        when(timeIntervalMock.isAfterStart(anyLong())).thenReturn(false);
        Assert.assertFalse(moveAnimation.isStartable());

        when(timeIntervalMock.isAfterStart(anyLong())).thenReturn(true);
        Assert.assertTrue(moveAnimation.isStartable());
    }

    @Test
    public void should_be_completed_depending_on_time_interval() {
        when(timeIntervalMock.isAfterEnd(anyLong())).thenReturn(false);
        Assert.assertFalse(moveAnimation.isCompleted());

        when(timeIntervalMock.isAfterEnd(anyLong())).thenReturn(true);
        Assert.assertTrue(moveAnimation.isCompleted());
    }

    @Test
    public void should_move_figure_by_percentage() {
        when(timeIntervalMock.isAfterStart(anyLong())).thenReturn(true);
        when(timeIntervalMock.elapsed()).thenReturn(0.1);
        when(easingFunctionMock.calculate(0.1, begin, end)).thenReturn(new Point(990, 1010));

        moveAnimation.begin();
        moveAnimation.step();
        verify(figureMock).moveBy(-10, 10);

        when(timeIntervalMock.elapsed()).thenReturn(1.0);
        when(easingFunctionMock.calculate(1.0, begin, end)).thenReturn(new Point(end));
        moveAnimation.step();
        verify(figureMock).moveBy(-90, 90);
    }

    @Test
    public void should_notify_on_animation_completed() {
        AnimationChangeListener acl = mock(AnimationChangeListener.class);
        moveAnimation.addAnimationChangeListener(acl);

        when(timeIntervalMock.isAfterStart(anyLong())).thenReturn(true);
        when(easingFunctionMock.calculate(anyLong(), any(Point.class), any(Point.class))).thenReturn(new Point(end));

        moveAnimation.begin();
        moveAnimation.step();
        moveAnimation.end();

        verify(acl, times(1)).onAnimationCompleted(any(AnimationChangeEvent.class));
    }

    @Test
    public void should_only_notify_active_listener() {
        AnimationChangeListener acl = mock(AnimationChangeListener.class);
        AnimationChangeListener acl2 = mock(AnimationChangeListener.class);
        moveAnimation.addAnimationChangeListener(acl);
        moveAnimation.addAnimationChangeListener(acl2);

        when(timeIntervalMock.isAfterStart(anyLong())).thenReturn(true);
        when(easingFunctionMock.calculate(anyLong(), any(Point.class), any(Point.class))).thenReturn(new Point(end));

        moveAnimation.begin();
        moveAnimation.removeAnimationChangeListener(acl2);
        moveAnimation.step();
        moveAnimation.end();

        verify(acl, times(1)).onAnimationCompleted(any(AnimationChangeEvent.class));
        verify(acl2, times(0)).onAnimationCompleted(any(AnimationChangeEvent.class));
    }


}
