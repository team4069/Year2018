package frc.team4069.robot.subsystems

import frc.team4069.robot.io.IOMapping
import frc.team4069.robot.motors.TalonMotor

object VacuumSubsystem : SubsystemBase() {
    private val talon = TalonMotor(IOMapping.VACUUM_CAN_BUS, reversed = true)

    /**
     * Starts the vacuum motor
     */
    fun beginSucc() = talon.setConstantSpeed(1.0)

    /**
     * Stops the vacuum motor
     */
    fun endSucc() = talon.stop()
}