package frc.team4069.robot.commands

import frc.team4069.robot.subsystems.ElevatorSubsystem

/**
 * Command to stop the elevator
 */
class StopElevatorCommand : CommandBase() {

    init {
        requires(ElevatorSubsystem)
    }

    override fun initialize() {
        ElevatorSubsystem.stop()
    }

    override fun isFinished(): Boolean = true
}