package frc.team4069.robot.io

// A class containing static constants that contain the port numbers for connected devices
object IOMapping {

    // CAN bus port for the climber hook motor
    const val ELEVATOR_CAN_BUS = 16
    const val VACUUM_CAN_BUS = 21
    const val WINCH_CAN_BUS = 10
    const val ARM_CAN_BUS = 24
    // CAN bus ports for the drive motors
    const val LEFT_DRIVE_CAN_BUS = 12
    const val RIGHT_DRIVE_CAN_BUS = 19

    const val ARM_LIMIT_SWITCH_DIO = 0

    // The port number of the joystick
    internal const val JOYSTICK_NUMBER = 0

    // The number of the POV (directional pad)
    internal const val POV_NUMBER = 0

    // Joystick buttons
    internal const val BUTTON_A = 1
    internal const val BUTTON_B = 2
    internal const val BUTTON_X = 3
    internal const val BUTTON_Y = 4
    internal const val BUMPER_LEFT = 5
    internal const val BUMPER_RIGHT = 6
    internal const val BUTTON_BACK = 7
    internal const val BUTTON_START = 8
}
