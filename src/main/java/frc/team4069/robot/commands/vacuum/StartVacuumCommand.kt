package frc.team4069.robot.commands.vacuum

import edu.wpi.first.wpilibj.command.Command
import frc.team4069.robot.subsystems.VacuumSubsystem

class StartVacuumCommand : Command() {
    init {
        requires(VacuumSubsystem)
    }

    override fun initialize() = VacuumSubsystem.beginSucc()

    override fun isFinished(): Boolean = true
}
