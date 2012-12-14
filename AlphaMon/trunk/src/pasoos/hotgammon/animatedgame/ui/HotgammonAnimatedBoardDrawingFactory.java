package pasoos.hotgammon.animatedgame.ui;

import minidraw.animatedboard.AnimatedBoardDrawingFactory;
import minidraw.animatedboard.MoveAnimationConfiguratorStrategy;
import minidraw.animation.engine.AnimationEngine;
import minidraw.boardgame.FigureFactory;
import minidraw.boardgame.PositioningStrategy;
import minidraw.boardgame.PropAppearanceStrategy;
import pasoos.hotgammon.Location;

public class HotgammonAnimatedBoardDrawingFactory implements AnimatedBoardDrawingFactory<Location> {

    private FigureFactory<Location> figureFactory;
    private final PropAppearanceStrategy propAppearanceStrategy;
    private final AnimationEngine animationEngine;

    public HotgammonAnimatedBoardDrawingFactory(
            FigureFactory<Location> figureFactory,
            PropAppearanceStrategy propAppearanceStrategy,
            AnimationEngine animationEngine) {
        this.figureFactory = figureFactory;
        this.propAppearanceStrategy = propAppearanceStrategy;
        this.animationEngine = animationEngine;
    }

    public FigureFactory<Location> getFigureFactory() {
        return figureFactory;
    }

    public PositioningStrategy<Location> getPositioningStrategy() {
        return new HotgammonPositioningStrategy();
    }

    public PropAppearanceStrategy getAppearanceStrategy() {
        return propAppearanceStrategy;
    }

    public AnimationEngine getAnimationEngine() {
        return animationEngine;
    }

    @Override
    public MoveAnimationConfiguratorStrategy createAnimationConfigurator() {
        return new MoveAnimationConfiguratorStrategyImpl();
    }

}
