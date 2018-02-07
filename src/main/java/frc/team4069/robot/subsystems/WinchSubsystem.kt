package frc.team4069.robot.subsystems

import frc.team4069.robot.motors.TalonMotor

object WinchSubsystem : SubsystemBase() {
    private val talon = TalonMotor(10, slaves = *intArrayOf(17))

    fun reset() {
        talon.stop()
    }

    fun start(reversed: Boolean = false) = talon.setConstantSpeed(if(reversed) -0.75 else 0.75)
    fun stop() = talon.stop()
}