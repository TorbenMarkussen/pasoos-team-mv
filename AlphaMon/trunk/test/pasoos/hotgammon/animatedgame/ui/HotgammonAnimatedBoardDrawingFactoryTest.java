package pasoos.hotgammon.animatedgame.ui;


import minidraw.animatedboard.AnimatedBoardDrawingFactory;
import minidraw.animation.engine.AnimationEngine;
import minidraw.boardgame.FigureFactory;
import minidraw.boardgame.PropAppearanceStrategy;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class HotgammonAnimatedBoardDrawingFactoryTest {

    private AnimatedBoardDrawingFactory factory;
    FigureFactory figureFactory;
    PropAppearanceStrategy propAppearanceStrategy;
    AnimationEngine animationEngine;

    @Before
    public void setup() throws IllegalAccessException, InstantiationException {
        figureFactory = mock(FigureFactory.class);
        propAppearanceStrategy = mock(PropAppearanceStrategy.class);
        animationEngine = mock(AnimationEngine.class);
        factory = new HotgammonAnimatedBoardDrawingFactory(figureFactory, propAppearanceStrategy, animationEngine);
    }

    @Test
    public void should_return_hotgammon_animated_board_drawing_factory() {
        assertTrue(factory.getFigureFactory() == figureFactory);
        assertTrue(factory.getPositioningStrategy() instanceof HotgammonPositioningStrategy);
        assertTrue(factory.getAppearanceStrategy() == propAppearanceStrategy);
        assertTrue(factory.getAnimationEngine() == animationEngine);
    }
}
