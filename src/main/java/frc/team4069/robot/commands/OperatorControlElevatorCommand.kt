package frc.team4069.robot.commands

import com.ctre.phoenix.motorcontrol.ControlMode
import frc.team4069.robot.io.Input
import frc.team4069.robot.subsystems.ElevatorSubsystem

class OperatorControlElevatorCommand : CommandBase() {
    init {
        requires(ElevatorSubsystem)
    }

    var isInUse = false

    override fun execute() {
        val value = Input.getElevatorAxis()
        isInUse = value != 0.0
        if(isInUse) {
            ElevatorSubsystem.set(ControlMode.PercentOutput, value)
        }else {
            ElevatorSubsystem.set(ControlMode.MotionMagic, ElevatorSubsystem.position + 500)
        }
    }

    override fun isFinished(): Boolean = false
}