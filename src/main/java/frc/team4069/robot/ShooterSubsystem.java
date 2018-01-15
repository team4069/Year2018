package frc.team4069.robot.subsystems;

import frc.team4069.robot.io.IOMapping;
import frc.team4069.robot.motors.CANTalonMotor;

// A relatively simple control subsystem for the shooter
public class ShooterSubsystem extends SubsystemBase {

    // A singleton instance of the shooter subsystem
    private static ShooterSubsystem instance;

    // The speed of the shooter when running
    private final double speed = 0.7;

    // The single shooter motor
    private CANTalonMotor shooterMotor;

    // Initialize the shooter motor
    private ShooterSubsystem() {
        // Initialize the motor with the predefined port number
        shooterMotor = new CANTalonMotor(IOMapping.SHOOTER_CAN_BUS, false);
    }

    // A public getter for the instance
    public static ShooterSubsystem getInstance() {
        // If the instance is null, create a new one
        if (instance == null) {
            instance = new ShooterSubsystem();
        }

        return instance;
    }

    // Start running the shooter at full speed
    public void start() {
        // Set the motor speed to the predefined constant
        shooterMotor.setConstantSpeed(speed);
    }

    // Stop the shooter
    public void stop() {
        // Stop the motor immediately
        shooterMotor.stop();
    }
}
