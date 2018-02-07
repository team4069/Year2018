package frc.team4069.robot.commands.autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import frc.team4069.robot.commands.CommandBase;

// Drive from the starting position to the switch in autonomous mode
class DriveToSwitch extends CommandBase {

    // The robot's starting position for autonomous mode
    private StartingPosition startingPosition = StartingPosition.RIGHT;

    // Constructor, used to claim subsystems
    DriveToSwitch() {
        // Claim exclusive use of the drive base
        requires(driveBase);
    }

    // Called to initialize the drive base
    protected void initialize() {
        // It should start out idle
        driveBase.stop();
    }

    // Called frequently while this command is being run
    protected void execute() {
        // Calculate the turn using the game data
        double turn = getTurnFromGameData();
        // Drive at three quarters of full speed using the calculated turn value
        driveBase.driveContinuousSpeed(turn, 0.75);
    }

    // Read the game data and get the direction to drive
    private double getTurnFromGameData() {
        // Get the game data as a string
        String gameData = DriverStation.getInstance().getGameSpecificMessage();
        // Check if the first character is left or right
        boolean isRight = gameData.charAt(0) == 'R';
        // Assuming the switch is on the left, switch on the starting position and get the relevant
        // turning angle
        double turn = 0;
        switch (startingPosition) {
            case LEFT:
                turn = -0.1;
            case CENTER:
                turn = 0.1;
            case RIGHT:
                turn = 0.3;
        }
        // If it is on the right, subtract 0.2 so that center is -0.1
        if (isRight) {
            turn -= 0.2;
        }
        // Return the modified turn value
        return turn;
    }

    // Called to check if the command has completed
    protected boolean isFinished() {
        // Check if the drive base has gone 5 meters
        return driveBase.getDistanceTraveledMeters() >= 5;
    }

    // An enum containing the possible starting positions of the robot
    private enum StartingPosition {
        LEFT,
        CENTER,
        RIGHT
    }
}
