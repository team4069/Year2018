package frc.team4069.robot.subsystems;

import frc.team4069.robot.io.IOMapping;
import frc.team4069.robot.motors.TalonMotor;

// A control subsystem for the climber
public class ClimberSubsystem extends SubsystemBase {

    // A singleton instance of the climber subsystem
    private static ClimberSubsystem instance;

    // The speed of the climber when running
    private final double speed = 1;

    // The single climber motor
    private TalonMotor climberMotor;

    // Initialize the climber motor
    private ClimberSubsystem() {
        // Initialize the motor with the predefined port number, and reverse its direction
        climberMotor = new TalonMotor(IOMapping.CLIMBER_PWM, true);
    }

    // A public getter for the instance
    public static ClimberSubsystem getInstance() {
        // If the instance is null, create a new one
        if (instance == null) {
            instance = new ClimberSubsystem();
        }

        return instance;
    }

    // Start running the climber at full speed
    public void start() {
        // Set the motor speed to the predefined constant
        climberMotor.setConstantSpeed(speed);
    }

    // Stop the climber
    public void stop() {
        // Stop the motor immediately
        climberMotor.stop();
    }

    // Used to check if the climber is currently running
    public boolean isStarted() {
        // Check if the current speed is equal to the running speed
        return climberMotor.getSpeed() != 0;
    }
}
