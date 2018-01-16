package frc.team4069.robot.motors;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

// The CANTalon-specific implementation of Motor
public class TalonSRXMotor extends Motor {

    // The number of encoder ticks per motor rotation
    private final int encoderTicksPerRotation = 4096;

    // An instance of the TalonSRX motor API
    private TalonSRX talon;

    // Create a new instance with a provided port number
    public TalonSRXMotor(TalonSRX talonSRX, boolean reversed) {
        // Call the superclass constructor using the reversed flag
        super(reversed);
        // Set the global reference to the Talon
        talon = talonSRX;
        // Set the encoder
        talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
        // Configure the proportional value
        talon.config_kP(0, 1.0, 0);
        /* lets grab the 360 degree position of the MagEncoder's absolute position */
        int absolutePosition = talon.getSelectedSensorPosition(0)
                & 0xFFF; /* mask out the bottom12 bits, we don't care about the wrap arounds */
        /* use the low level API to set the quad encoder signal */
        talon.setSelectedSensorPosition(absolutePosition, 0, 0);
    }

    // Set the speed of the motor without any checks or processing
    public void setSpeedUnsafe(double speed) {
        // Set the speed of the CANTalon directly
        talon.set(ControlMode.PercentOutput, speed);
    }

    // Set the position of the motor in rotations, using PID for positional control
    public void rotateToPositionRotations(double position) {
        // Multiply the position by the number of encoder ticks per rotation
        double positionEncoderTicks = position * (double) encoderTicksPerRotation;
        // Set the control mode and the calculated position
        talon.set(ControlMode.Position, positionEncoderTicks);
    }

    // Get the distance traveled so far in rotations
    public double getDistanceTraveledRotations() {
        // Get the value from the sensor collection
        int quadPosition = talon.getSensorCollection().getQuadraturePosition();
        // Divide it by the number of ticks per rotation to get the number of rotations
        return (double) quadPosition / (double) encoderTicksPerRotation;
    }

    // Reset the distance traveled
    public void resetDistanceTraveled() {
        // Reset the encoder count to zero
        talon.setSelectedSensorPosition(0, 0, 0);
    }
}
