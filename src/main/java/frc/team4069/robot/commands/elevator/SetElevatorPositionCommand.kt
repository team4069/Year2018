package frc.team4069.robot.commands.elevator

import edu.wpi.first.wpilibj.command.InstantCommand
import frc.team4069.robot.subsystems.ElevatorSubsystem

// A command to instruct the elevator to move to a predefined vertical position
class SetElevatorPositionCommand(private val position: ElevatorSubsystem.Position) : InstantCommand() {

    // Require the elevator subsystem
    init {
        requires(ElevatorSubsystem)
    }

    // Set the elevator's position to whatever was passed to this
    override fun initialize() {
        ElevatorSubsystem.setPosition(position)
    }
}