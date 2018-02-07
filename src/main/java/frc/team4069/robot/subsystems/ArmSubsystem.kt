package frc.team4069.robot.subsystems

import com.ctre.phoenix.motorcontrol.ControlMode
import edu.wpi.first.wpilibj.DigitalInput
import frc.team4069.robot.io.IOMapping
import frc.team4069.robot.motors.TalonMotor

object ArmSubsystem : SubsystemBase() {
    private val talon = TalonMotor(IOMapping.ARM_CAN_BUS)
    private val limitSwitch = DigitalInput(IOMapping.ARM_LIMIT_SWITCH_DIO)


    fun reset() {
        talon.stop()
        talon.setSelectedSensorPosition(0, 0, 0)
    }

    fun start() = talon.set(ControlMode.PercentOutput, 0.75)
    fun stop() = talon.stop()

    val isSwitchSet: Boolean get() = !limitSwitch.get()
}