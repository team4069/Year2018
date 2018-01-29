package frc.team4069.robot.io;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.team4069.robot.commands.StartElevatorDownCommand;
import frc.team4069.robot.commands.StartElevatorUpCommand;
import frc.team4069.robot.commands.StopElevatorCommand;

// Class that provides accessors for joystick inputs and maps them to commands
public class Input {

    // The main controlJoystick
    private static Joystick driveJoystick;
    private static Joystick controlJoystick;

    // Initializer that handles mapping of the joysticks to commands
    public static void init() {
        // Create the joystick using the port number
        driveJoystick = new Joystick(IOMapping.DRIVE_JOYSTICK_NUMBER);
        controlJoystick = new Joystick(IOMapping.CONTROL_JOYSTICK_NUMBER);
        Button b = new JoystickButton(controlJoystick, 1);
        b.whenPressed(new StartElevatorUpCommand());
        b.whenReleased(new StopElevatorCommand());

        Button bb = new JoystickButton(controlJoystick, 3);
        bb.whenPressed(new StartElevatorDownCommand());
        bb.whenReleased(new StopElevatorCommand());
    }

    // Accessor for the steering axis on the drive joystick
    public static double getSteeringAxis() {
        return driveJoystick.getRawAxis(IOMapping.DRIVE_STEERING_AXIS);
    }

    // Accessor for the drive speed, using the left and right triggers
    public static double getDriveSpeed() {
        // Right trigger controls forward motion, left trigger controls backward motion
        // Therefore we want to negate leftButton's value.
        double rightButton = driveJoystick.getRawAxis(IOMapping.DRIVE_FORWARD_AXIS);
        double leftButton = -driveJoystick.getRawAxis(IOMapping.DRIVE_BACKWARD_AXIS);
        return leftButton + rightButton;
    }

    // Accessor for the directional pad on the joystick
    // Returns an angle in degrees, clockwise from the top of the pad
    // Returns -1 if no input is registered
    public static int getDirectionalPadAngleDegrees() {
        // This functionality is built into the controlJoystick library exactly as described
        return driveJoystick.getPOV(IOMapping.QUICK_TURN_POV);
    }
}
