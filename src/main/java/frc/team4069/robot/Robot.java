package frc.team4069.robot;


import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Robot extends IterativeRobot {
    private Scheduler scheduler;
    private String gameData;

    @Override
    public void robotInit() {
        super.robotInit();

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

        scheduler.removeAll();

        //TODO: Stub
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