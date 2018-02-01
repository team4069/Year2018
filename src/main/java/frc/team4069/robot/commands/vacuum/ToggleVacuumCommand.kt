package frc.team4069.robot.commands.vacuum

import edu.wpi.first.wpilibj.command.ConditionalCommand
import frc.team4069.robot.subsystems.VacuumSubsystem

class ToggleVacuumCommand : ConditionalCommand(StopVacuumCommand(), StartVacuumCommand()) {

    init {
        requires(VacuumSubsystem)
    }

    override fun condition(): Boolean = VacuumSubsystem.isStarted()

}