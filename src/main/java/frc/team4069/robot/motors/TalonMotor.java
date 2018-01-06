package frc.team4069.robot.motors;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;

// The PWM Talon-specific implementation of Motor
public class TalonMotor extends Motor {

    // An instance of the Talon motor API
    private Talon talon;

    // The encoder associated with the Talon
    private Encoder encoder;

    // Create a new instance with provided port numbers for the motor and the encoder,
    // and the number of meters traveled per tick on the encoder
    public TalonMotor(int motorPortNumber, int encoderPortNumber1, int encoderPortNumber2,
            boolean reversed, double metersPerTick) {
        // Call the constructor without the encoder parameters to initialize everything else
        this(motorPortNumber, reversed);

        // Initialize the encoder with the two port numbers
        encoder = new Encoder(encoderPortNumber1, encoderPortNumber2);
        // Set the encoder's reversed property
        encoder.setReverseDirection(reversed);
        // Set the scaling factor for the encoder to compute the number of meters traveled
        encoder.setDistancePerPulse(metersPerTick);
    }

    // Create a new instance without dealing with the encoder
    // Instances created using this constructor externally do not have associated encoders
    // and will do nothing if any of the encoder-related functions are called
    public TalonMotor(int motorPortNumber, boolean reversed) {
        // Call the superclass constructor with the reversed parameter
        super(reversed);

        // Create a Talon using the provided port number
        talon = new Talon(motorPortNumber);
    }

    // Accessor for the currently commanded speed of the Talon
    public double getSpeed() {
        return talon.get();
    }

    // Set the speed of the motor without any safety checks
    public void setSpeedUnsafe(double speed) {
        // Set the speed of the Talon directly
        talon.set(speed);
    }

    // Get the distance traveled so far in rotations
    public double getDistanceTraveledMeters() {
        // Get the distance traveled directly from the Talon unless the encoder has not been set
        // In that case, simply return zero
        return encoder == null ? 0 : encoder.getDistance();
    }

    // Reset the distance traveled
    public void resetDistanceTraveled() {
        // If the encoder has been set
        if (encoder != null) {
            // Reset the encoder count
            encoder.reset();
        }
    }
}
