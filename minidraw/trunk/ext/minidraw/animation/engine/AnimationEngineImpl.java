package minidraw.animation.engine;

import minidraw.animation.AnimationTimer;
import minidraw.animation.Animation;
import minidraw.standard.AnimationTimerImpl;

import java.util.ArrayList;
import java.util.List;

public class AnimationEngineImpl implements Runnable, AnimationEngine {
    private AnimationTimer atimer;
    private List<Animation> starting = new ArrayList<Animation>();
    private List<Animation> running = new ArrayList<Animation>();

    private AnimationEngineImpl(Builder builder) {
        this.atimer = builder.getAnimationTimer();
        atimer.setTimeoutReceiver(this);
    }

    @Override
    public synchronized void startAnimation(Animation a) {
        System.out.println("adding animation" + starting.size());
        starting.add(a);
        if (starting.size() == 1)
            System.out.println("start timer");
        atimer.start();
    }

    @Override
    public synchronized void run() {

        processStarting();

        processRunning();

        if (running.size() == 0 && starting.size() == 0) {
            System.out.println("stop timer");
            atimer.stop();
        }

    }

    private void processRunning() {
        List<Animation> completed = new ArrayList<Animation>();
        for (Animation a : running) {
            executeStep(a);
            if (a.isCompleted()) {
                completed.add(a);
                executeEnd(a);
            }
        }
        running.removeAll(completed);
    }

    private void processStarting() {
        List<Animation> temp = new ArrayList<Animation>();
        for (Animation a : starting) {
            if (a.isStartable()) {
                executeBegin(a);
                running.add(a);
                temp.add(a);
            }
        }
        starting.removeAll(temp);
    }

    private void executeEnd(Animation a) {
        try {
            a.end();
        } catch (Exception e) {
            //silent catch exception
        }
    }

    private void executeStep(Animation a) {
        try {
            a.step();
        } catch (Exception e) {

        }
    }

    private void executeBegin(Animation a) {
        try {
            a.begin();
        } catch (Exception e) {
            //silent catch exception
        }

    }

    @Override
    public void stopAll() {
        for (Animation a : starting) {
            a.abort();
        }
        starting.clear();
        for (Animation a : running) {
            a.abort();
        }
        running.clear();
    }

    public static class Builder {

        private AnimationTimer animationTimer;

        public Builder() {
            animationTimer = new AnimationTimerImpl();
        }

        public AnimationTimer getAnimationTimer() {
            return animationTimer;
        }

        public Builder setAnimationTimer(AnimationTimer animationTimer) {
            this.animationTimer = animationTimer;
            return this;
        }

        public AnimationEngine build() {
            return new AnimationEngineImpl(this);
        }
    }
}
