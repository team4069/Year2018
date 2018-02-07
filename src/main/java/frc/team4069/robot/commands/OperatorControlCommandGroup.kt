package frc.team4069.robot.commands

import edu.wpi.first.wpilibj.command.CommandGroup

// The group of commands required for operator control
class OperatorControlCommandGroup : CommandGroup() {
    // Constructor that runs all necessary commands in parallel
    init {
        // Add the command for driving
        addParallel(OperatorDriveCommand())
        addParallel(OperatorControlElevatorCommand())
    }

}
