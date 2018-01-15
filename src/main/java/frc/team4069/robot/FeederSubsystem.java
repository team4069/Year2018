package frc.team4069.robot.subsystems;

import frc.team4069.robot.io.IOMapping;
import frc.team4069.robot.motors.TalonMotor;

// A relatively simple control subsystem for the feeder
public class FeederSubsystem extends SubsystemBase {

    // A singleton instance of the feeder subsystem
    private static FeederSubsystem instance;

    // The speed of the feeder when running
    private final double speed = 0.8;

    // The single feeder motor
    private TalonMotor feederMotor;

    // Initialize the feeder motor
    private FeederSubsystem() {
        // Initialize the motor with the predefined port number
        feederMotor = new TalonMotor(IOMapping.FEEDER_PWM, true);
    }

    // A public getter for the instance
    public static FeederSubsystem getInstance() {
        // If the instance is null, create a new one
        if (instance == null) {
            instance = new FeederSubsystem();
        }

        return instance;
    }

    // Start running the feeder at full speed
    public void start() {
        // Set the motor speed to the predefined constant
        feederMotor.setConstantSpeed(speed);
    }

    // Stop the feeder
    public void stop() {
        // Stop the motor immediately
        feederMotor.stop();
    }
}
