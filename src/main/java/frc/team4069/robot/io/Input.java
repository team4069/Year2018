package frc.team4069.robot.io;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.team4069.robot.commands.elevator.ElevatorIntakeCommandGroup;
import frc.team4069.robot.commands.elevator.SetElevatorPositionCommand;
import frc.team4069.robot.commands.vacuum.ToggleVacuumCommand;
import frc.team4069.robot.subsystems.ElevatorSubsystem.Position;


// Class that provides accessors for joystick inputs and maps them to commands
public class Input {

    // The main joystick
    private static Joystick joystick;

    // Initializer that handles mapping of the joysticks to commands
    public static void init() {
        // Create the joystick using the port number
        joystick = new Joystick(IOMapping.JOYSTICK_NUMBER);

        // Map the elevator controls for scale, switch, and exchange
        Button elevatorToSwitch = new JoystickButton(joystick, IOMapping.BUTTON_B);
        elevatorToSwitch.whenPressed(new SetElevatorPositionCommand(Position.SWITCH));
        Button elevatorToExchange = new JoystickButton(joystick, IOMapping.BUTTON_X);
        elevatorToExchange.whenPressed(new SetElevatorPositionCommand(Position.EXCHANGE));
        Button elevatorToScale = new JoystickButton(joystick, IOMapping.BUTTON_Y);
        elevatorToScale.whenPressed(new SetElevatorPositionCommand(Position.SCALE));
        // Run a special command group for elevator intake
        Button elevatorToIntake = new JoystickButton(joystick, IOMapping.BUTTON_A);
        elevatorToIntake.whenPressed(new ElevatorIntakeCommandGroup());

        // Stop the vacuum when the start button is pressed
        Button toggleVacuum = new JoystickButton(joystick, IOMapping.BUTTON_START);
        toggleVacuum.whenPressed(new ToggleVacuumCommand());
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
