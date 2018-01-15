package frc.team4069.robot.commands;

import frc.team4069.robot.io.Input;

// The command that allows the operator to control the turret
class OperatorTurretCommand extends CommandBase {

    // Constructor, used to claim subsystems
    OperatorTurretCommand() {
        // Claim exclusive use of the drive base
        requires(turret);
    }

    // Called frequently while this command is being run
    protected void execute() {
        // Get the direction of the turret from the input class
        int turretDirection = Input.getTurretDirection();
        // Run the turret subsystem in the specified direction
        turret.run(turretDirection);
    }

    // Called to check whether this command has completed
    protected boolean isFinished() {
        // Always return false because this command should never finish
        return false;
    }
}
