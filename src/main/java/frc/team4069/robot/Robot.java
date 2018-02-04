package frc.team4069.robot;


import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.team4069.robot.commands.CommandBase;
import frc.team4069.robot.commands.OperatorControlCommandGroup;
import frc.team4069.robot.io.Input;
import frc.team4069.robot.subsystems.ElevatorSubsystem;

public class Robot extends IterativeRobot {

    private Scheduler scheduler;

    @Override
    public void robotInit() {
        super.robotInit();

        // Set up the input class
        Input.init();

        // Configure the vision threads
//        VisionData.configureVision();

        // Get the scheduler
        scheduler = Scheduler.getInstance();
        // Initialize the subsystems
        CommandBase.init();
    }

    @Override
    public void autonomousInit() {
        super.autonomousInit();
        //TODO: Autonomous operations
    }

    @Override
    public void teleopInit() {
        super.teleopInit();
        // Remove all commands from the scheduler so no autonomous tasks continue running
        scheduler.removeAll();
        // Add an operator control command group to the scheduler, which should never exit
        scheduler.add(new OperatorControlCommandGroup());
    }

    @Override
    public void disabledInit() {
        // Reset the state of the elevator subsystem so that it doesn't take off when next we enable
        ElevatorSubsystem.INSTANCE.reset();
    }

    // During all phases, run the command scheduler
    private void universalPeriodic() {
        scheduler.run();
    }

    @Override
    public void autonomousPeriodic() {
        universalPeriodic();
    }

    @Override
    public void teleopPeriodic() {
        super.teleopPeriodic();
        universalPeriodic();
    }
}
