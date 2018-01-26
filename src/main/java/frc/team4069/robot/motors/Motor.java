package frc.team4069.robot.motors;

// A simple motor wrapper that defines common APIs for motors
// Implementation-specific internal APIs are implemented by subclasses
// This class should contain higher-level APIs that work regardless of the specific type of motor
public abstract class Motor {

    // Flag that says whether or not the motor should be reversed
    private boolean reversed;

    // Constructor that sets parameters common to all motors
    Motor(boolean reversed) {
        // Set the global reversed parameter
        this.reversed = reversed;
    }

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

    // Set the position of the motor in rotations using PID for positional control
    public abstract void rotateToPositionRotations(double position);

    // Set the speed of the motor without any checks or processing
    // Called in the setConstantSpeed function and must be overridden by subclasses
    abstract void setSpeedUnsafe(double speed);

    // Stops the motor immediately
    public final void stop() {
        // Set the speed of the motor to zero
        setConstantSpeed(0);
    }

    // Get the distance traveled so far in meters
    public abstract double getDistanceTraveledRotations();

    // Reset the distance traveled
    public abstract void resetDistanceTraveled();
}
