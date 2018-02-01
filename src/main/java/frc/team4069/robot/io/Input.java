package frc.team4069.robot.io;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.team4069.robot.commands.elevator.StartElevatorDownCommand;
import frc.team4069.robot.commands.elevator.StartElevatorUpCommand;
import frc.team4069.robot.commands.elevator.StopElevatorCommand;
import frc.team4069.robot.commands.vacuum.StartVacuumCommand;
import frc.team4069.robot.commands.vacuum.StopVacuumCommand;


// Class that provides accessors for joystick inputs and maps them to commands
public class Input {

    // The main joystick
    private static Joystick joystick;

    // Initializer that handles mapping of the joysticks to commands
    public static void init() {
        // Create the joystick using the port number
        joystick = new Joystick(IOMapping.JOYSTICK_NUMBER);

        Button elevatorUp = new JoystickButton(joystick, IOMapping.BUTTON_A);
        elevatorUp.whenPressed(new StartElevatorUpCommand());
        elevatorUp.whenReleased(new StopElevatorCommand());

        Button stopVacuum = new JoystickButton(joystick, IOMapping.BUTTON_B);
        stopVacuum.whenPressed(new StopVacuumCommand());

        Button elevatorDown = new JoystickButton(joystick, IOMapping.BUTTON_X);
        elevatorDown.whenPressed(new StartElevatorDownCommand());
        elevatorDown.whenReleased(new StopElevatorCommand());

        Button startVacuum = new JoystickButton(joystick, IOMapping.BUTTON_Y);
        startVacuum.whenPressed(new StartVacuumCommand());
    }

    // Accessor for the steering axis on the drive joystick
    public static double getSteeringAxis() {
        // Use the horizontal axis on the left stick
        return joystick.getRawAxis(IOMapping.LEFT_STICK_HORIZONTAL_AXIS);
    }

    // Accessor for the elevator axis on the drive joystick
    public static double getElevatorAxis() {
        // Use the vertical axis on the right stick
        return joystick.getRawAxis(IOMapping.RIGHT_STICK_VERTICAL_AXIS);
    }

    // Accessor for the drive speed, using the left and right triggers
    public static double getDriveSpeed() {
        // Right trigger controls forward motion, left trigger controls backward motion
        double forwardMotion = joystick.getRawAxis(IOMapping.RIGHT_TRIGGER_AXIS);
        double backwardMotion = -joystick.getRawAxis(IOMapping.LEFT_TRIGGER_AXIS);
        return backwardMotion + forwardMotion;
    }

    // Accessor for the directional pad on the joystick
    // Returns an angle in degrees, clockwise from the top of the pad
    // Returns -1 if no input is registered
    public static int getDirectionalPadAngleDegrees() {
        // This functionality is built into the joystick library exactly as described
        return joystick.getPOV(IOMapping.POV_NUMBER);
    }
}
