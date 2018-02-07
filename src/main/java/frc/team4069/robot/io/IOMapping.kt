package frc.team4069.robot.io

// A class containing static constants that contain the port numbers for connected devices
object IOMapping {

    // CAN bus port for the climber hook motor
    val ELEVATOR_CAN_BUS = 16
    val VACUUM_CAN_BUS = 21
    val WINCH_CAN_BUS = 10
    val ARM_CAN_BUS = 24
    // CAN bus ports for the drive motors
    val LEFT_DRIVE_CAN_BUS = 12
    val RIGHT_DRIVE_CAN_BUS = 19

    // The port number of the joystick
    internal val JOYSTICK_NUMBER = 0

    // The number of the POV (directional pad)
    internal val POV_NUMBER = 0

    // Joystick buttons
    internal val BUTTON_A = 1
    internal val BUTTON_B = 2
    internal val BUTTON_X = 3
    internal val BUTTON_Y = 4
    internal val BUMPER_LEFT = 5
    internal val BUMPER_RIGHT = 6
    internal val BUTTON_BACK = 7
    internal val BUTTON_START = 8
}
