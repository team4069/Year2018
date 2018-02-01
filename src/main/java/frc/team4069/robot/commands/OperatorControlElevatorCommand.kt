package frc.team4069.robot.commands

import edu.wpi.first.wpilibj.command.Command
import frc.team4069.robot.io.Input
import frc.team4069.robot.subsystems.ElevatorSubsystem

class OperatorControlElevatorCommand : Command() {

    // Require the elevator subsystem
    init {
        requires(ElevatorSubsystem)
    }

    // Executed periodically while this command is running
    override fun execute() {
        // If the elevator stick is being used
        val elevatorAxis = Input.getElevatorAxis()
        if (elevatorAxis != 0.0) {
            // Set the speed of the elevator using the axis directly
            ElevatorSubsystem.setSpeed(elevatorAxis)
        }
    }

    // This command should never complete
    override fun isFinished(): Boolean = false
}