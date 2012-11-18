package minidraw.framework;

import java.util.ArrayList;
import java.util.List;

public class AnimationEngine implements Runnable {
    private AnimationTimer atimer;
    private List<Animation> running = new ArrayList<Animation>();
    private List<Animation> starting = new ArrayList<Animation>();

    public AnimationEngine(AnimationTimer atimer) {
        this.atimer = atimer;
        atimer.setTimeoutReceiver(this);
    }

    public synchronized void addAnimation(Animation a) {
        starting.add(a);
        if (starting.size() == 1)
            atimer.start();
    }

    @Override
    public synchronized void run() {

        List<Animation> completed = new ArrayList<Animation>();

        for (Animation a : starting) {
            runBegin(a);
            running.add(a);
        }
        starting.clear();

        for (Animation a : running) {
            a.run();
            if (a.isCompleted()) {
                completed.add(a);
                System.out.println("ae.end");
                a.end();
            }
        }

        for (Animation a : completed) {
            running.remove(a);
        }

        if (running.size() == 0 && starting.size() == 0) {
            atimer.stop();
        }

    }

    private void runBegin(Animation a) {
        try {
            a.begin();
        } catch (Exception e) {
            //silent catch exception
        }

    }

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
