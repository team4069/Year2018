package frc.team4069.robot.commands.elevator

import edu.wpi.first.wpilibj.command.InstantCommand
import frc.team4069.robot.subsystems.ElevatorSubsystem

// A command to instruct the elevator motor to run at a specific speed
class SetElevatorSpeedCommand(private val speed: Double) : InstantCommand() {

    // Require the elevator subsystem
    init {
        requires(ElevatorSubsystem)
    }

    // Set the elevator's speed to whatever was passed to this
    override fun initialize() {
        ElevatorSubsystem.setSpeed(speed)
    }
}