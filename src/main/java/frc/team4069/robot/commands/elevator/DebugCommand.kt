package frc.team4069.robot.commands.elevator

import edu.wpi.first.wpilibj.command.InstantCommand
import frc.team4069.robot.subsystems.ElevatorSubsystem

class DebugCommand : InstantCommand() {
    init {
        requires(ElevatorSubsystem)
    }

    override fun initialize() = println(ElevatorSubsystem.position)
}