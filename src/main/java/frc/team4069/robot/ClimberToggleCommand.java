package frc.team4069.robot.commands;

// A command to toggle the climber
public class ClimberToggleCommand extends CommandBase {

    // Constructor, used to claim subsystems
    public ClimberToggleCommand() {
        // Claim the climber subsystem
        requires(climber);
    }

    // Toggle the climber on initialization
    @Override
    protected void initialize() {
        // Check if it is started, and if it is, stop it
        if (climber.isStarted()) {
            climber.stop();
        }
        // If it is stopped, start it
        else {
            climber.start();
        }
    }

    // This command does not need to run for any length of time
    @Override
    protected boolean isFinished() {
        return true;
    }
}
