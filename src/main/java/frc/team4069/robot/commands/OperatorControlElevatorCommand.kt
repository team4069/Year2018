package frc.team4069.robot.commands

import com.ctre.phoenix.motorcontrol.ControlMode
import edu.wpi.first.wpilibj.command.Command
import frc.team4069.robot.io.Input
import frc.team4069.robot.subsystems.ElevatorSubsystem

class OperatorControlElevatorCommand : Command() {

    // Require the elevator subsystem
    init {
        requires(ElevatorSubsystem)
    }

    var set = true

    // Executed periodically while this command is running
    override fun execute() {
        // If the elevator stick is being used
        val elevatorAxis = Input.getElevatorAxis() * 0.8
        if (elevatorAxis != 0.0) {
            // Set the speed of the elevator using the axis directly
            ElevatorSubsystem.setSpeed(elevatorAxis)
            set = false
        }else {
            if(!set) {
                ElevatorSubsystem.set(ControlMode.MotionMagic, (ElevatorSubsystem.position - 500).toDouble())
                set = true
            }
        }
    }

    // This command should never complete
    override fun isFinished(): Boolean = false
}