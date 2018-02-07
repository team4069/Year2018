package frc.team4069.robot.io

import edu.wpi.first.wpilibj.GenericHID
import edu.wpi.first.wpilibj.XboxController
import edu.wpi.first.wpilibj.buttons.JoystickButton
import frc.team4069.robot.commands.elevator.ElevatorIntakeCommandGroup
import frc.team4069.robot.commands.elevator.SetElevatorPositionCommand
import frc.team4069.robot.commands.vacuum.ToggleVacuumCommand
import frc.team4069.robot.commands.winch.StartWinchCommand
import frc.team4069.robot.commands.winch.StopWinchCommand
import frc.team4069.robot.subsystems.ElevatorSubsystem

object Input {
    private lateinit var joystick: XboxController

    fun init() {
        joystick = XboxController(IOMapping.JOYSTICK_NUMBER)

        // Elevator preset commands
        val elevatorToSwitch = JoystickButton(joystick, IOMapping.BUTTON_B)
        elevatorToSwitch.whenPressed(SetElevatorPositionCommand(ElevatorSubsystem.Position.SWITCH))
        val elevatorToExchange = JoystickButton(joystick, IOMapping.BUTTON_X)
        elevatorToExchange.whenPressed(SetElevatorPositionCommand(ElevatorSubsystem.Position.EXCHANGE))
        val elevatorToScale = JoystickButton(joystick, IOMapping.BUTTON_Y)
        elevatorToScale.whenPressed(SetElevatorPositionCommand(ElevatorSubsystem.Position.SCALE))
        val elevatorToIntake = JoystickButton(joystick, IOMapping.BUTTON_A)
        elevatorToIntake.whenPressed(ElevatorIntakeCommandGroup())

        val winchForward = JoystickButton(joystick, IOMapping.BUMPER_RIGHT)
        winchForward.whenPressed(StartWinchCommand(reversed = false))
        winchForward.whenReleased(StopWinchCommand())

        val winchBackward = JoystickButton(joystick, IOMapping.BUMPER_LEFT)
        winchBackward.whenPressed(StartWinchCommand(reversed = true))
        winchBackward.whenReleased(StopWinchCommand())

        val toggleVacuum = JoystickButton(joystick, IOMapping.BUTTON_START)
        toggleVacuum.whenPressed(ToggleVacuumCommand())
    }

    /**
     * Accessor for the steering axis with a ± 0.2 deadband
     */
    val steeringAxis: Double
        get() {
            val axis = joystick.getX(GenericHID.Hand.kLeft)
            val abs = Math.abs(axis)
            return if (abs <= 0.2 && abs > 0) {
                0.0
            } else {
                axis
            }
        }

    /**
     * Accessor for the elevator axis with a ± 0.2 deadband
     */
    val elevatorAxis: Double
        get() {
            val axis = joystick.getY(GenericHID.Hand.kRight)
            val abs = Math.abs(axis)
            return if(abs <= 0.2 && abs > 0) {
                0.0
            }else {
                axis
            }
        }

    /**
     * Accessor for the drive speed using the triggers
     */
    val driveSpeed: Double
        get() {
            val forwardMotion = joystick.getTriggerAxis(GenericHID.Hand.kRight)
            val backwardMotion = -joystick.getTriggerAxis(GenericHID.Hand.kLeft)
            return forwardMotion + backwardMotion
        }

    val directionalPadAngleDegrees: Int
        get() = joystick.getPOV(IOMapping.POV_NUMBER)
}