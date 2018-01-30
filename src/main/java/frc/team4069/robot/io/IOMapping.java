package frc.team4069.robot.io;

// A class containing static constants that contain the port numbers for connected devices
public class IOMapping {

    // CAN bus port for the climber hook motor
    public static final int ELEVATOR_CAN_BUS = 6;
    public static final int VACUUM_CAN_BUS = 4;
    // CAN bus ports for the drive motors
    public static final int LEFT_DRIVE_CAN_BUS = 1;
    public static final int RIGHT_DRIVE_CAN_BUS = 2;

    // The port number of the drive joystick
    static final int DRIVE_JOYSTICK_NUMBER = 0;
    static final int CONTROL_JOYSTICK_NUMBER = 1;
    // Axis numbers for steering and speed on the drive joystick
    // The steering axis is the horizontal axis of the left stick
    static final int DRIVE_STEERING_AXIS = 0;
    // Right trigger drives forward
    static final int DRIVE_FORWARD_AXIS = 3;
    // Left trigger drives backward
    static final int DRIVE_BACKWARD_AXIS = 2;
    // The number of the POV (directional pad) used for quick turning
    static final int QUICK_TURN_POV = 0;

    // Joystick buttons
    static final int BUTTON_A = 1;
    static final int BUTTON_B = 2;
    static final int BUTTON_X = 3;
    static final int BUTTON_Y = 4;
    static final int BUTTON_RB = 5;
}
