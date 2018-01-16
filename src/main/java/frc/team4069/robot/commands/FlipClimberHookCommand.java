package frc.team4069.robot.commands;

// The command to rotate the climber hook and grab onto the bar
public class FlipClimberHookCommand extends CommandBase {

    // Constructor, used to claim subsystems
    public FlipClimberHookCommand() {
        // Claim exclusive use of the drive base
        requires(climberHook);
    }

    // Initializer in which to set the motor speed
    protected void initialize() {
        // Rotate the motor to one half of a rotation
        climberHook.flipHook();
    }

    // Called to check whether this command has completed
    protected boolean isFinished() {
        // Always return true because this command takes no time
        return true;
    }
}
