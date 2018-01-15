package frc.team4069.robot.subsystems;

import frc.team4069.robot.io.IOMapping;
import frc.team4069.robot.motors.TalonSRXMotor;

// Subsystem for the motor that flips the hook to engage the climber
public class ClimberHookSubsystem extends SubsystemBase {

    // A singleton instance of the climber hook subsystem
    private static ClimberHookSubsystem instance;

    // Instance of the motor
    private TalonSRXMotor climberHookMotor;

    // Initialize the motor
    private ClimberHookSubsystem() {
        // Use the predefined port number
        climberHookMotor = new TalonSRXMotor(IOMapping.CLIMBER_HOOK_CAN_BUS, false, 10);
    }

    // A public getter for the instance
    public static ClimberHookSubsystem getInstance() {
        // If the instance is null, create a new one
        if (instance == null) {
            instance = new ClimberHookSubsystem();
        }

        return instance;
    }

    // Set the position of the motor
    public void setPosition(double position) {
        climberHookMotor.setPosition(position);
    }
}
