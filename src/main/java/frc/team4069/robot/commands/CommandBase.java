package frc.team4069.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4069.robot.subsystems.ClimberHookSubsystem;
import frc.team4069.robot.subsystems.DriveBaseSubsystem;

// A generic command class that contains references to all of the subsystems and initializes them
public abstract class CommandBase extends Command {

    // Instances of each of the subsystems
    static DriveBaseSubsystem driveBase;
    static ClimberHookSubsystem climberHook;

    // An function that handles initialization of subsystems
    public static void init() {
        // Get the singleton instance of each of the subsystems
        driveBase = DriveBaseSubsystem.getInstance();
        climberHook = ClimberHookSubsystem.getInstance();
    }
}
