package frc.team4069.robot.subsystems;

import com.ctre.phoenix.drive.DriveMode;
import com.ctre.phoenix.drive.MecanumDrive;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.team4069.robot.io.IOMapping;
import frc.team4069.robot.motors.TalonSRXMotor;

// A class that manages all hardware components of the drive base and provides utility functions
// for instructing it to drive and turn in a variety of ways
public class DriveBaseSubsystem extends SubsystemBase {

    // The lateral distance between the robot's wheels in meters
    public static final double ROBOT_TRACK_WIDTH_METERS = 0.6;

    // A singleton instance of the drive base subsystem
    private static DriveBaseSubsystem instance;

    // The number of meters each wheel travels per encoder rotation
    private final double METERS_PER_ROTATION = 0.05;

    // References to the individual motors
    private TalonSRXMotor leftFrontMotor;
    private TalonSRXMotor leftRearMotor;
    private TalonSRXMotor rightFrontMotor;
    private TalonSRXMotor rightRearMotor;

    // Class that provides Mecanum drive functionality
    private MecanumDrive mecanumDrive;

    // Initialize the drive motors
    private DriveBaseSubsystem() {
        // Initialize TalonSRX instances with predefined port numbers
        TalonSRX leftFrontTalon = new TalonSRX(IOMapping.LEFT_FRONT_DRIVE_PWM);
        TalonSRX leftRearTalon = new TalonSRX(IOMapping.LEFT_REAR_DRIVE_PWM);
        TalonSRX rightFrontTalon = new TalonSRX(IOMapping.RIGHT_FRONT_DRIVE_PWM);
        TalonSRX rightRearTalon = new TalonSRX(IOMapping.RIGHT_REAR_DRIVE_PWM);

        // Initialize the global motors with the TalonSRX instances
        leftFrontMotor = new TalonSRXMotor(leftFrontTalon, false);
        leftRearMotor = new TalonSRXMotor(leftRearTalon, false);
        rightFrontMotor = new TalonSRXMotor(rightFrontTalon, false);
        rightRearMotor = new TalonSRXMotor(rightRearTalon, false);

        // Create a Mecanum drive instance with the TalonSRX instances
        mecanumDrive = new MecanumDrive(
                leftFrontTalon,
                leftRearTalon,
                rightFrontTalon,
                rightRearTalon
        );
    }

    // A public getter for the instance
    public static DriveBaseSubsystem getInstance() {
        // If the instance is null, create a new one
        if (instance == null) {
            instance = new DriveBaseSubsystem();
        }

        return instance;
    }

    // Get the average absolute distance traveled by each of the wheels, in meters
    public double getDistanceTraveledMeters() {
        // Get the distance traveled in rotations from each wheel
        double leftFrontDistance = leftFrontMotor.getDistanceTraveledRotations();
        double leftRearDistance = leftRearMotor.getDistanceTraveledRotations();
        double rightFrontDistance = rightFrontMotor.getDistanceTraveledRotations();
        double rightRearDistance = rightRearMotor.getDistanceTraveledRotations();
        // Calculate the average of the 4 values
        double averageDistance = (leftFrontDistance + leftRearDistance
                + rightFrontDistance + rightRearDistance) / 4.0;
        // Convert the value to meters and return it
        return averageDistance * METERS_PER_ROTATION;
    }

    // Start driving with given parameters
    public void drive(double ySpeed, double xSpeed, double zRotation) {
        // Call the Mecanum drive class directly
        mecanumDrive.set(DriveMode.Voltage, ySpeed, zRotation, xSpeed);
    }

    // Stop moving immediately
    public void stop() {
        // Set the drive speeds to zero
        drive(0, 0, 0);
    }
}
