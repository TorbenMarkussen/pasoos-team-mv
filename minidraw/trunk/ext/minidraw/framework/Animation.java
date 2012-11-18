package minidraw.framework;

public interface Animation {

    boolean isCompleted();

    void begin();

    void run();

    void end();

    Figure getFigure();

    void abort();
}
