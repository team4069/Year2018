package frc.team4069.robot.subsystems;

import frc.team4069.robot.io.IOMapping;
import frc.team4069.robot.motors.CANTalonMotor;

// A control subsystem for the turret
public class TurretSubsystem extends SubsystemBase {

    // A singleton instance of the turret subsystem
    private static TurretSubsystem instance;

    // The speed of the turret when running
    private final double speed = 0.5;

    // The single turret motor
    private CANTalonMotor turretMotor;

    // Initialize the turret motor
    private TurretSubsystem() {
        // Initialize the motor with the predefined port number
        turretMotor = new CANTalonMotor(IOMapping.TURRET_CAN_BUS, false);
    }

    // A public getter for the instance
    public static TurretSubsystem getInstance() {
        // If the instance is null, create a new one
        if (instance == null) {
            instance = new TurretSubsystem();
        }

        return instance;
    }

    // Start running the turret at full speed in the specified direction
    public void run(double direction) {
        // Set the motor speed to the direction, multiplied by the predefined speed constant
        turretMotor.setConstantSpeed(direction * speed);
    }
}
