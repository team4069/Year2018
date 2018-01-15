package frc.team4069.robot.commands;

import edu.wpi.first.wpilibj.Timer;

// A command to start running the shooter and the feeder
public class ShooterStartCommand extends CommandBase {

    // Constructor, used to claim subsystems
    public ShooterStartCommand() {
        // Claim the shooter and the feeder subsystems
        requires(shooter);
        requires(feeder);
    }

    // Start running the shooter and feeder when initialized
    @Override
    protected void initialize() {
        // Start it immediately
        shooter.start();
        // Delay feeder slightly to allow time for shooter to get to full speed
        Timer.delay(1.5);
        // Start the feeder
        feeder.start();
    }

    // This command does not need to run for any length of time
    @Override
    protected boolean isFinished() {
        return true;
    }
}
