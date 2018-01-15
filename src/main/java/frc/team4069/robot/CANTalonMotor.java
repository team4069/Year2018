package frc.team4069.robot.motors;

import com.ctre.CANTalon;

// The CANTalon-specific implementation of Motor
public class CANTalonMotor extends Motor {

    // An instance of the CANTalon motor API
    private CANTalon canTalon;

    // Create a new instance with a provided port number
    public CANTalonMotor(int portNumber, boolean reversed) {
        // Call the superclass constructor using the reversed flag
        super(reversed);

        // Create a CANTalon using the provided port number
        canTalon = new CANTalon(portNumber);
        // Reverse its encoder accordingly
        canTalon.reverseSensor(reversed);
    }

    // Accessor for the currently commanded speed of the CANTalon
    public double getSpeed() {
        return canTalon.get();
    }

    // Set the speed of the motor without any checks or processing
    public void setSpeedUnsafe(double speed) {
        // Set the speed of the CANTalon directly
        canTalon.set(speed);
    }

    // Get the distance traveled so far in meters
    public double getDistanceTraveledMeters() {
        // Do nothing for now; just return 0
        return 0;
    }

    // Reset the distance traveled
    public void resetDistanceTraveled() {
        // Reset the encoder count to zero
        canTalon.setEncPosition(0);
    }
}
