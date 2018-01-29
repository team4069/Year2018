package frc.team4069.robot.commands

import frc.team4069.robot.subsystems.ElevatorSubsystem

/**
 * Command to start the elevator going upwards
 */
class StartElevatorUpCommand : CommandBase() {

    init {
        requires(ElevatorSubsystem)
    }

    override fun initialize() {
        ElevatorSubsystem.start(ElevatorSubsystem.Direction.UP)
    }

    override fun isFinished(): Boolean = true
}