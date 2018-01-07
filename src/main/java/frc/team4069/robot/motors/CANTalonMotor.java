package frc.team4069.robot.motors;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Encoder;

// The CANTalon-specific implementation of Motor
public class CANTalonMotor extends Motor {

    // An instance of the CANTalon motor API
    private TalonSRX canTalon;
    private Encoder encoder;

    // Create a new instance with a provided port number
    public CANTalonMotor(int portNumber, int encoderPort1, int encoderPort2, boolean reversed) {
        // Call the superclass constructor using the reversed flag
        super(reversed);

        // Create a CANTalon using the provided port number
        canTalon = new TalonSRX(portNumber);
        // Reverse its encoder accordingly
        // XXX: I don't know if this is comparable
//        canTalon.setInverted(true);

        encoder = new Encoder(encoderPort1, encoderPort2);
    }

    // Accessor for the currently commanded speed of the CANTalon
    public double getSpeed() {
        return canTalon.getOutputCurrent();
    }

    // Set the speed of the motor without any checks or processing
    public void setSpeedUnsafe(double speed) {
        // Set the speed of the CANTalon directly
        canTalon.set(ControlMode.Current, speed);
    }

    // Get the distance traveled so far in meters
    public double getDistanceTraveledMeters() {
        // Do nothing for now; just return 0
        return encoder.getDistance();
    }

    // Reset the distance traveled
    public void resetDistanceTraveled() {
        // Reset the encoder count to zero
        encoder.reset();
    }
}
