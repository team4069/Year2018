package frc.team4069.robot.commands.arm

import edu.wpi.first.wpilibj.command.Command
import frc.team4069.robot.subsystems.ArmSubsystem


class RaiseArmCommand : Command() {
    init {
        requires(ArmSubsystem)
    }

    override fun initialize() {
        // Start the motor at a constant speed, no PID or motion magic
        ArmSubsystem.start()
    }

    override fun end() {
        ArmSubsystem.stop()
    }

    override fun isFinished(): Boolean {
        // When the limit switch is activated, we'll stop the command and leave the arm where it is
        return ArmSubsystem.isSwitchSet
    }
}
