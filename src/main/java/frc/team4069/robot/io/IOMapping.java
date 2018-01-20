package frc.team4069.robot.io;

// A class containing static constants that contain the port numbers for connected devices
public class IOMapping {

    // CAN bus port for the climber hook motor
    public static final int CLIMBER_HOOK_CAN_BUS = 6;
    // CAN bus ports for the drive motors
    public static final int LEFT_FRONT_DRIVE_CAN_BUS = 1;
    public static final int RIGHT_FRONT_DRIVE_CAN_BUS = 2;
    public static final int LEFT_REAR_DRIVE_CAN_BUS = 3;
    public static final int RIGHT_REAR_DRIVE_CAN_BUS = 4;

    // The port number of the drive joystick
    static final int DRIVE_JOYSTICK_NUMBER = 0;
    // Axis numbers for steering and speed on the drive joystick
    // The steering axis is the horizontal axis of the left stick
    static final int DRIVE_STEERING_AXIS = 0;
    // Right trigger drives forward
    static final int DRIVE_FORWARD_AXIS = 3;
    // Left trigger drives backward
    static final int DRIVE_BACKWARD_AXIS = 2;
    // The number of the POV (directional pad) used for quick turning
    static final int QUICK_TURN_POV = 0;
}
