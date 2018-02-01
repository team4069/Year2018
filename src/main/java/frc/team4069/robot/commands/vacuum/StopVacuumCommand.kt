package frc.team4069.robot.commands.vacuum

import edu.wpi.first.wpilibj.command.InstantCommand
import frc.team4069.robot.subsystems.VacuumSubsystem

class StopVacuumCommand : InstantCommand() {
    init {
        requires(VacuumSubsystem)
    }

    override fun initialize() = VacuumSubsystem.stop()
}