package frc.team4069.robot.subsystems

import com.ctre.phoenix.motorcontrol.ControlMode
import com.ctre.phoenix.motorcontrol.FeedbackDevice
import frc.team4069.robot.io.IOMapping
import frc.team4069.robot.motors.TalonMotor

object ArmSubsystem : SubsystemBase() {
    val talon = TalonMotor(IOMapping.ARM_CAN_BUS)

    val position: Int
        get() = talon.getSelectedSensorPosition(0)

    init {
        talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0)
        talon.setSelectedSensorPosition(0, 0, 0)
        talon.config_kF(0, 1.05, 0)
        talon.config_kP(0, 1.0, 0)
    }

    fun rotateTo(pos: Position) {
        talon.set(ControlMode.MotionMagic, pos.ticks.toDouble())
    }

    fun reset() {
        talon.stop()
        talon.setSelectedSensorPosition(0, 0, 0)
    }

    enum class Position(val ticks: Int) {
        MAX(1230),
        MIN(150)
    }
}