package frc.team4069.robot.io;

// A class containing static constants that contain the port numbers for connected devices
public class IOMapping {

    // CAN bus port for the climber hook motor
    public static final int CLIMBER_HOOK_CAN_BUS = 1;
    // PWM ports for the left and right drive motors
    public static final int LEFT_DRIVE_PWM = 8;
    public static final int RIGHT_DRIVE_PWM = 9;
    // Left encoder Digital I/O ports.
    public static final int LEFT_DRIVE_ENCODER_1 = 0;
    public static final int LEFT_DRIVE_ENCODER_2 = 1;
    // Right encoder Digital I/O ports.
    public static final int RIGHT_DRIVE_ENCODER_1 = 2;
    public static final int RIGHT_DRIVE_ENCODER_2 = 3;
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
