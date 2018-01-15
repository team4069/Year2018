package frc.team4069.robot.commands;

// A command to stop the shooter and feeder
public class ShooterStopCommand extends CommandBase {

    // Constructor, used to claim subsystems
    public ShooterStopCommand() {
        // Claim the shooter and the feeder subsystems
        requires(shooter);
        requires(feeder);
    }

    // Stop running the shooter and feeder when initialized
    @Override
    protected void initialize() {
        // Stop it immediately
        shooter.stop();
        // Stop the feeder
        feeder.stop();
    }

    // This command does not need to run for any length of time
    @Override
    protected boolean isFinished() {
        return true;
    }
}
