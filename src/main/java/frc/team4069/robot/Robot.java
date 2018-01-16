package frc.team4069.robot;


import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.team4069.robot.commands.CommandBase;
import frc.team4069.robot.commands.FlipClimberHookCommand;
import frc.team4069.robot.commands.OperatorControlCommandGroup;
import frc.team4069.robot.io.Input;

public class Robot extends IterativeRobot {

    private Scheduler scheduler;
    private String gameData;

    @Override
    public void robotInit() {
        super.robotInit();

        // Set up the input class
        Input.init();

        // Data for which side of the switches and scales are ours is sent through FMS at the start of the match
        // Follows the pattern LRL, where arr[0] is your switch, arr[1] is the scale, and arr[2] is their switch
        // L meaning left, R meaning right
        gameData = DriverStation.getInstance().getGameSpecificMessage();
        // Get the scheduler
        scheduler = Scheduler.getInstance();
        // Initialize the subsystems
        CommandBase.init();
    }

    @Override
    public void autonomousInit() {
        super.autonomousInit();
        // Add a climber flip command to the scheduler
        scheduler.add(new FlipClimberHookCommand());
    }

    @Override
    public void teleopInit() {
        super.teleopInit();
        // Remove all commands from the scheduler so no autonomous tasks continue running
        scheduler.removeAll();
        // Add an operator control command group to the scheduler, which should never exit
        scheduler.add(new OperatorControlCommandGroup());
    }

    // During all phases, run the command scheduler
    private void universalPeriodic() {
        scheduler.run();
    }

    @Override
    public void autonomousPeriodic() {
        super.autonomousPeriodic();
        universalPeriodic();
    }

    @Override
    public void teleopPeriodic() {
        super.teleopPeriodic();
        universalPeriodic();
    }
}