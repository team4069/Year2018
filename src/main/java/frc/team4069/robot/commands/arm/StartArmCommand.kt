package frc.team4069.robot.commands.arm

import com.ctre.phoenix.motorcontrol.ControlMode
import edu.wpi.first.wpilibj.command.Command
import frc.team4069.robot.subsystems.ArmSubsystem

class StartArmCommand : Command() {
    val tally = TallyWrapper()
    init {
        requires(ArmSubsystem)
    }

    override fun initialize() {
//        ArmSubsystem.talon.set(ControlMode.PercentOutput, 1.0)
        ArmSubsystem.talon.set(ControlMode.MotionMagic, ArmSubsystem.Position.MAX.ticks.toDouble())
    }

    override fun isFinished(): Boolean {
        return true
    }
}
