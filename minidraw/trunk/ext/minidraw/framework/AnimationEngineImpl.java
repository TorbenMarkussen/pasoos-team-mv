package minidraw.framework;

import java.util.ArrayList;
import java.util.List;

public class AnimationEngineImpl implements Runnable, AnimationEngine {
    private AnimationTimer atimer;
    private List<Animation> starting = new ArrayList<Animation>();
    private List<Animation> running = new ArrayList<Animation>();

    public AnimationEngineImpl(AnimationTimer atimer) {
        this.atimer = atimer;
        atimer.setTimeoutReceiver(this);
    }

    @Override
    public synchronized void startAnimation(Animation a) {
        starting.add(a);
        if (starting.size() == 1)
            atimer.start();
    }

    @Override
    public synchronized void run() {

        processStarting();

        processRunning();

        if (running.size() == 0 && starting.size() == 0) {
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
}
