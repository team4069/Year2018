package frc.team4069.robot.commands.elevator

import edu.wpi.first.wpilibj.command.InstantCommand
import frc.team4069.robot.subsystems.ElevatorSubsystem

// Command to stop the elevator
class StopElevatorCommand : InstantCommand() {

    init {
        requires(ElevatorSubsystem)
    }

    override fun initialize() {
        ElevatorSubsystem.stop()
    }
}