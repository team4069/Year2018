package frc.team4069.robot.commands

import edu.wpi.first.wpilibj.command.InstantCommand
import frc.team4069.robot.subsystems.ElevatorSubsystem

/**
 * Command to start the elevator going upwards
 */
class StartElevatorUpCommand : InstantCommand() {

    init {
        requires(ElevatorSubsystem)
    }

    override fun initialize() {
        ElevatorSubsystem.start(ElevatorSubsystem.Direction.UP)
    }
}