package frc.team4069.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4069.robot.subsystems.ArmSubsystem;
import frc.team4069.robot.subsystems.DriveBaseSubsystem;
import frc.team4069.robot.subsystems.ElevatorSubsystem;
import frc.team4069.robot.subsystems.VacuumSubsystem;
import frc.team4069.robot.subsystems.WinchSubsystem;

// A generic command class that contains references to all of the subsystems and initializes them
public abstract class CommandBase extends Command {

    // Instances of each of the subsystems
    static DriveBaseSubsystem driveBase;
    static ElevatorSubsystem elevator;
    static ArmSubsystem arm;
    static VacuumSubsystem vacuum;
    static WinchSubsystem winch;

    // An function that handles initialization of subsystems
    public static void init() {
        // Get the singleton instance of each of the subsystems
        driveBase = DriveBaseSubsystem.INSTANCE;
        elevator = ElevatorSubsystem.INSTANCE;
        arm = ArmSubsystem.INSTANCE;
        vacuum = VacuumSubsystem.INSTANCE;
        winch = WinchSubsystem.INSTANCE;
    }
}
