package minidraw.framework;

public class TimeInterval {

    private long start = -1;
    private long duration = -1;
    private long end = -1;

    public static TimeInterval fromNow() {
        TimeInterval tl = new TimeInterval();
        tl.setStart(now());
        return tl;
    }

    private TimeInterval setStart(long start) {
        this.start = start;
        return this;
    }

    public TimeInterval duration(long duration) {
        end = start + duration;
        this.duration = duration;
        return this;
    }

    public double elapsed() {
        double dt = now() - start;
        double t = dt / duration;

        return t < 1 ? t : 1;
    }

    public static long now() {
        return System.currentTimeMillis();
    }

    public boolean isAfterStart(long t) {
        return t >= start;
    }

    public boolean isAfterEnd(long t) {
        return t >= end;
    }

    public static TimeInterval from(long delay) {
        TimeInterval tl = new TimeInterval();
        tl.setStart(now() + delay);
        return tl;
    }
}
