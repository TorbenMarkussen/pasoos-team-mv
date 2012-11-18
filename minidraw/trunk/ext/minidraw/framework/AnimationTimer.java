package minidraw.framework;

public interface AnimationTimer {
    void setTimeoutReceiver(Runnable r);

    void start();

    void stop();
}
