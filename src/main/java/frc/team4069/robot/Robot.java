package frc.team4069.robot;


import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
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
        scheduler = Scheduler.getInstance();
    }

    @Override
    public void autonomousInit() {
        super.autonomousInit();
        //TODO: Stub
    }

    @Override
    public void teleopInit() {
        super.teleopInit();
        // Remove all commands from the scheduler so no autonomous tasks continue running
        scheduler.removeAll();
        // Add an operator control command group to the scheduler, which should never exit
        scheduler.add(new OperatorControlCommandGroup());
    }

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