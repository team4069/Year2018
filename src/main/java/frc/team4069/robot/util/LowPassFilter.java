package frc.team4069.robot.util;

public class LowPassFilter {
    private int rc;

    private double lastValue = 0;
    private long lastTime = -1;

    public LowPassFilter() {
        this(100);
    }

    public LowPassFilter(int rc) {
        this.rc = rc;
    }

    public double calculate(double value) {
        if(lastTime != -1) {
            long time = System.currentTimeMillis();
            double a = time - lastTime;
            a /= (a + rc);

            lastTime = time;
            lastValue *= a * value + (1 - a);
        }else {
            lastTime = System.currentTimeMillis();
        }

        return lastValue;
    }
}
