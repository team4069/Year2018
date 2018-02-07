package frc.team4069.robot.commands

import edu.wpi.first.wpilibj.command.Command
import frc.team4069.robot.io.Input
import frc.team4069.robot.subsystems.DriveBaseSubsystem

class OperatorDriveCommand : Command() {

    private var quickTurnDistanceMetres = 0.0

    init {
        requires(DriveBaseSubsystem)
    }

    override fun initialize() {
        // Drive base should start out idle
        DriveBaseSubsystem.stop()
    }

    override fun execute() {
        // Get values from the controller.
        val turningCoefficient = Input.steeringAxis
        val speed = Input.driveSpeed

        // If we aren't currently quick turning
        if (quickTurnDistanceMetres == 0.0) {
            var directionalPadAngle = Input.directionalPadAngleDegrees
            // If we're using the directional pad, and not pressing up
            if (directionalPadAngle != -1 && directionalPadAngle != 0) {
                if (directionalPadAngle > 180) {
                    // Subtract 360 to have the robot rotate in the opposite direction
                    directionalPadAngle -= 360
                }

                // Get the arc that the wheels should travel
                quickTurnDistanceMetres = (directionalPadAngle.toDouble() / 360) * DriveBaseSubsystem.ROBOT_TRACK_WIDTH_METRES * Math.PI
                // Get the sign of the angle, then quick turn
                val turnDirection = Math.signum(quickTurnDistanceMetres)

                DriveBaseSubsystem.quickTurn(turnDirection)
            }
        } else { // Quick turn is running
            if (DriveBaseSubsystem.distanceTraveledMetres >= Math.abs(quickTurnDistanceMetres)) {
                DriveBaseSubsystem.stop()
                quickTurnDistanceMetres = 0.0
            }
        }

        // Driver is using regular controls
        if (Math.abs(turningCoefficient) >= 0.2
                || Math.abs(speed) >= 0.2
                || quickTurnDistanceMetres == 0.0) {
            DriveBaseSubsystem.drive(turningCoefficient, speed)
        }
    }

    // This command will never finish
    override fun isFinished() = false
}