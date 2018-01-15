package frc.team4069.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

// The group of commands required for operator control
public class OperatorControlCommandGroup extends CommandGroup {

    // Constructor that runs all necessary commands in parallel
    public OperatorControlCommandGroup() {
        // Add the command for driving
        addParallel(new OperatorDriveCommand());
    }

}
