package frc.team4069.robot.commands

import frc.team4069.robot.subsystems.ElevatorSubsystem

/**
 * Command to start the elevator going downwards
 */
class StartElevatorDownCommand : CommandBase() {

    init {
        requires(ElevatorSubsystem)
    }

    override fun initialize() {
        ElevatorSubsystem.start(ElevatorSubsystem.Direction.DOWN)
    }

    override fun isFinished(): Boolean = true
}