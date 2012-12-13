package minidraw.standard;

import minidraw.animation.AnimationTimer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnimationTimerImpl implements AnimationTimer, ActionListener {

    private Timer timer;
    private Runnable receiver;

    public AnimationTimerImpl() {
        timer = new Timer(10, this);
    }

    @Override
    public void setTimeoutReceiver(Runnable r) {
        receiver = r;
    }

    @Override
    public void start() {
        timer.start();
    }

    @Override
    public void stop() {
        timer.stop();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        receiver.run();
    }
}
