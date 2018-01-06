package frc.team4069.robot;


import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Robot extends IterativeRobot {
    private Scheduler scheduler;

    @Override
    public void robotInit() {
        super.robotInit();

        //TODO: Stub
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