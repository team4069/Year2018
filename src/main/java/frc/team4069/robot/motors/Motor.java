package frc.team4069.robot.motors;

import java.util.Date;

// A simple motor wrapper that defines common APIs for motors
// Implementation-specific internal APIs are implemented by subclasses
// This class should contain higher-level APIs that work regardless of the specific type of motor
abstract class Motor {

    // An instance of the motor state enum
    private MotorState state = MotorState.CONSTANT_SPEED;

    // Flag that says whether or not the motor should be reversed
    private boolean reversed;

    // Beginning and ending speeds for ramping up or down
    private double rampStartSpeed, rampEndSpeed;
    // Time taken to interpolate between the starting and ending speeds
    private long rampTimeMilliseconds;
    // Unix time in milliseconds at which ramping was started
    private long rampStartTimeMilliseconds;

    // Constructor that sets parameters common to all motors
    Motor(boolean reversed) {
        // Set the global reversed parameter
        this.reversed = reversed;
    }

    // Update the motor controls (for states such as ramp up and ramp down)
    public final void update() {
        // Switch on the motor's current state
        switch (state) {
            // Constant speed or stopped
            case CONSTANT_SPEED:
                // Do nothing
                break;
            // Ramping up or down
            case RAMP:
                // Linear interpolate between the starting and ending speeds by the time spent
                // Subtract the current time from the starting time to get the time elapsed since
                // ramping was started
                Date currentDate = new Date();
                long currentTime = currentDate.getTime();
                long timeElapsed = currentTime - rampStartTimeMilliseconds;
                // Divide the elapsed time by the total time for ramping to get the proportion
                // of the ramping time that has passed
                double proportionPassed = (double) timeElapsed / (double) rampTimeMilliseconds;
                // Multiply the proportion passed by the delta between the start speed and end speed
                double speedDelta = rampEndSpeed - rampStartSpeed;
                double speedDeltaScaledByTime = proportionPassed * speedDelta;
                // Add the scaled speed delta to the starting speed to get the interpolated speed
                double interpolatedSpeed = rampStartSpeed + speedDeltaScaledByTime;
                // Set the robot's current speed to the ramped speed
                setSpeedWithReverse(interpolatedSpeed);
        }
    }

    // Get the current speed of the motor
    public abstract double getSpeed();

    // Set the speed of the motor, as a number from -1 to 1
    public final void setConstantSpeed(double speed) {
        // Reset the distance traveled, as this is a new operation
        resetDistanceTraveled();

        // Create a mutable speed value that will be used to set the motor
        double boundedSpeed = speed;
        // Limit the motor's speed to the range of -1 to 1
        if (speed > 1) {
            boundedSpeed = 1;
        } else if (speed < -1) {
            boundedSpeed = -1;
        }
        // Otherwise, set the speed of the motor to the calculated value
        setSpeedWithReverse(boundedSpeed);
    }

    // Set the speed, reversing it if the motor is reversed
    private void setSpeedWithReverse(double speed) {
        // If the motor should be reversed, set the speed to its negative
        double speedWithReverse = reversed ? -speed : speed;
        setSpeedUnsafe(speedWithReverse);
    }

    // Set the speed of the motor without any checks or processing
    // Called in the setConstantSpeed function and must be overridden by subclasses
    abstract void setSpeedUnsafe(double speed);

    // Stops the motor immediately
    public final void stop() {
        // Set the speed of the motor to zero
        setConstantSpeed(0);
    }

    // Get the distance traveled so far in meters
    public abstract double getDistanceTraveledMeters();

    // Reset the distance traveled
    public abstract void resetDistanceTraveled();

    // An enum that contains the possible states of the motor
    private enum MotorState {
        // Rotating at a constant speed or stopped
        CONSTANT_SPEED,
        // Ramp up or down gradually
        RAMP
    }
}
