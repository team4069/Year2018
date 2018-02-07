package frc.team4069.robot.subsystems

import com.ctre.phoenix.motorcontrol.ControlMode
import frc.team4069.robot.io.IOMapping
import frc.team4069.robot.motors.TalonMotor
import frc.team4069.robot.util.LowPassFilter

/**
 * A class representing the drive base on the robot
 */
object DriveBaseSubsystem : SubsystemBase() {

    // Initialise the drive motors with 1 master and 2 slave talons
    private val leftDrive = TalonMotor(IOMapping.LEFT_DRIVE_CAN_BUS, false, 11, 13)
    private val rightDrive = TalonMotor(IOMapping.RIGHT_DRIVE_CAN_BUS, false, 18, 20)

    // Initialise low pass filters for both sides
    private val leftDriveLpf = LowPassFilter()
    private val rightDriveLpf = LowPassFilter()

    /**
     * Get the total distance traveled in metres since the last reset
     */
    val distanceTraveledMetres: Double
        get() {
            val leftWheelRotationsTraveled = Math.abs(leftDrive.distanceTraveledRotations)
            val rightWheelRotationsTraveled = Math.abs(rightDrive.distanceTraveledRotations)
            val averageRotationsTraveled = (leftWheelRotationsTraveled + rightWheelRotationsTraveled) / 2
            return averageRotationsTraveled * METRES_PER_ROTATION
        }

    /**
     * Stop the motors immediately
     */
    fun stop() {
        leftDrive.stop()
        rightDrive.stop()
    }

    /**
     * Generate cheesy drive wheel speeds specifically for quick turn
     * Uses [turn] as the turning coefficient, and 0 as the speed
     */
    fun quickTurn(turn: Double) {
        val wheelSpeeds = generalisedCheesyDrive(turn, 0.0)

        leftDrive.set(ControlMode.PercentOutput, wheelSpeeds.leftWheelSpeed)
        rightDrive.set(ControlMode.PercentOutput, wheelSpeeds.rightWheelSpeed)
    }

    /**
     * Generate cheesy drive wheel speeds for arbitrary [turn] and [speed]
     */
    fun drive(turn: Double, speed: Double) {
        // Quirk of the drive base, invert the turn if we're going backwards
        val turn = if (speed < 0) -turn else turn

        // Scale turn down for better controlability, operator preference
        val wheelSpeeds = generalisedCheesyDrive(turn * 0.4, speed)

        leftDrive.set(ControlMode.PercentOutput, wheelSpeeds.leftWheelSpeed)
        rightDrive.set(ControlMode.PercentOutput, wheelSpeeds.rightWheelSpeed)
    }

    /**
     * Function that calculates wheel speeds given [turn] and [speed]
     *
     * @return Class wrapper for left and rug
     */
    private fun generalisedCheesyDrive(turn: Double, speed: Double): WheelSpeeds {
        // If there's no speed, then we're turning on the spot
        if (speed == 0.0) {
            return if (turn == 0.0) {
                WheelSpeeds(0.0, 0.0)
            } else {
                WheelSpeeds(turn, -turn)
            }
        }
        // Apply a polynomial function to the speed and multiply it by the turning coefficient
        // This adds a non-linearity so that turning is quicker at lower speeds
        // This number will be half of the difference in speed between the two wheels
        // Get the sign of the speed and use the absolute value because the polynomial may give
        // imaginary numbers for negative speed values
        val speedSign = if(speed > 0.0) 1 else -1
        val wheelSpeedDifference = speedPolynomial(Math.abs(speed)) * turn * speedSign

        return WheelSpeeds(
                leftDriveLpf.calculate(speed + wheelSpeedDifference),
                rightDriveLpf.calculate(speed - wheelSpeedDifference)
        )
    }

    private fun speedPolynomial(speed: Double) = Math.sqrt(speed) * 2

    private data class WheelSpeeds(val leftWheelSpeed: Double, val rightWheelSpeed: Double)

    const val ROBOT_TRACK_WIDTH_METRES = 0.6
    const val METRES_PER_ROTATION = 0.2
}