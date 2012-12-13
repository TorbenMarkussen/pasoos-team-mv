package minidraw.animation;

import minidraw.animation.easings.EasingFunctionStrategy;
import minidraw.framework.Figure;

import java.awt.*;

public class MoveAnimation extends BaseAnimation {
    protected Point beginPoint;
    protected Point endPoint;
    private Point current;

    private EasingFunctionStrategy easingFunction;

    public MoveAnimation(Figure f, Point endPoint, TimeInterval tl, EasingFunctionStrategy easingFunction) {
        super(f, tl);
        this.easingFunction = easingFunction;
        beginPoint = f.displayBox().getLocation();
        this.endPoint = endPoint;
    }

    @Override
    public void begin() {
        current = beginPoint;
    }

    @Override
    public void step() {
        double t = timeInterval.elapsed();
        Point p = easingFunction.calculate(t, beginPoint, endPoint);
        figure.moveBy(p.x - current.x, p.y - current.y);
        current = p;
    }


}
