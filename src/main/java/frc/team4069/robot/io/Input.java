package frc.team4069.robot.io;

import edu.wpi.first.wpilibj.Joystick;

// Class that provides accessors for joystick inputs and maps them to commands
public class Input {

    // The main joystick
    private static Joystick joystick;

    // Initializer that handles mapping of the joysticks to commands
    public static void init() {
        // Create the joystick using the port number
        joystick = new Joystick(IOMapping.DRIVE_JOYSTICK_NUMBER);
    }

    // Accessor for the steering axis on the drive joystick
    public static double getSteeringAxis() {
        return joystick.getRawAxis(IOMapping.DRIVE_STEERING_AXIS);
    }

    // Accessor for the drive speed, using the left and right triggers
    public static double getDriveSpeed() {
        // Right trigger controls forward motion, left trigger controls backward motion
        // Therefore we want to negate leftButton's value.
        double rightButton = joystick.getRawAxis(IOMapping.DRIVE_FORWARD_AXIS);
        double leftButton = -joystick.getRawAxis(IOMapping.DRIVE_BACKWARD_AXIS);
        return leftButton + rightButton;
    }

    // Accessor for the directional pad on the joystick
    // Returns an angle in degrees, clockwise from the top of the pad
    // Returns -1 if no input is registered
    public static int getDirectionalPadAngleDegrees() {
        // This functionality is built into the joystick library exactly as described
        return joystick.getPOV(IOMapping.QUICK_TURN_POV);
    }
}
