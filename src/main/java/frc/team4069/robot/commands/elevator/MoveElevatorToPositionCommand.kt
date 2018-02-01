package frc.team4069.robot.commands.elevator

import com.ctre.phoenix.motorcontrol.ControlMode
import edu.wpi.first.wpilibj.command.InstantCommand
import frc.team4069.robot.subsystems.ElevatorSubsystem

class MoveElevatorToPositionCommand(val position: ElevatorSubsystem.Position) : InstantCommand() {
    init {
        requires(ElevatorSubsystem)
    }

    override fun initialize() {
        ElevatorSubsystem.set(ControlMode.MotionMagic, position.ticks.toDouble())
    }
}