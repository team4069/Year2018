package frc.team4069.robot.motors;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

// The CANTalon-specific implementation of Motor
public class TalonSRXMotor extends Motor {

    // The number of encoder ticks per motor rotation
    private final int encoderTicksPerRotation = 4096;

    // The number of motor rotations per meter of wheel travel
    private double motorRotations;

    // An instance of the TalonSRX motor API
    private TalonSRX talon;

    // Create a new instance with a provided port number
    public TalonSRXMotor(int portNumber, boolean reversed, double motorRotationsPerMeter) {
        // Call the superclass constructor using the reversed flag
        super(reversed);
        // Create a CANTalon using the provided port number
        talon = new TalonSRX(portNumber);
        // Set the number of motor rotations per meter
        motorRotations = motorRotationsPerMeter;
    }

    // Accessor for the currently commanded speed of the CANTalon
    public double getSpeed() {
        return talon.getOutputCurrent();
    }

    // Set the speed of the motor without any checks or processing
    public void setSpeedUnsafe(double speed) {
        // Set the speed of the CANTalon directly
        talon.set(ControlMode.Current, speed);
    }

    // Set the position of the motor using PID for positional control
    public void setPosition(double position) {
        talon.set(ControlMode.Position, position);
    }

    // Get the distance traveled so far in meters
    public double getDistanceTraveledMeters() {
        // Get the value from the sensor collection
        int quadPosition = talon.getSensorCollection().getQuadraturePosition();
        // Divide it by the number of ticks per rotation to get the number of rotations
        double numRotations = (double) quadPosition / (double) encoderTicksPerRotation;
        return 0;
    }

    // Reset the distance traveled
    public void resetDistanceTraveled() {
        // Reset the encoder count to zero
        talon.setSelectedSensorPosition(0, 0, 0);
    }
}
