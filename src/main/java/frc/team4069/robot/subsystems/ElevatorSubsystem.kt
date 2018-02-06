package frc.team4069.robot.subsystems

import com.ctre.phoenix.motorcontrol.ControlMode
import com.ctre.phoenix.motorcontrol.FeedbackDevice
import edu.wpi.first.wpilibj.DigitalInput
import edu.wpi.first.wpilibj.InterruptHandlerFunction
import frc.team4069.robot.io.IOMapping
import frc.team4069.robot.motors.TalonMotor
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import java.util.concurrent.atomic.AtomicBoolean

// Subsystem for controlling the cube elevator
object ElevatorSubsystem : SubsystemBase() {

    // The maximum number of ticks that the elevator motor can safely reach
    const val MAX_POSITION_TICKS = -26901

    // The number of ticks around the edges of the elevator's range in which it starts to slow down
    private const val slowDownRange = 2500

    val position: Int
        get() = talon.getSelectedSensorPosition(0)

    // Motor to control
    private val talon = TalonMotor(IOMapping.ELEVATOR_CAN_BUS)
    // Limit switch on the elevator
    private val limitSwitch = DigitalInput(0)
    val locked = AtomicBoolean(false)

    init {
        // Stop the elevator from coasting when the talon is stopped (probably)
        talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0)

        // Set the feed-forward gain
        talon.config_kF(0, 0.5, 10)
        talon.setSelectedSensorPosition(0, 0, 0)

        // Configure an interrupt in the limit switch to stop and zero the sensor
        limitSwitch.requestInterrupts(object : InterruptHandlerFunction<Any>() {
            override fun interruptFired(interruptAssertedMask: Int, param: Any?) {
                talon.stop()
                talon.setSelectedSensorPosition(0, 0, 0)
                locked.set(true)
                launch {
                    delay(600)
                    locked.set(false)
                }
            }
        })
//        limitSwitch.enableInterrupts()
    }

    // Stops the elevator motor
    fun stop() = talon.stop()

    fun set(mode: ControlMode, speed: Double) {
        if(locked.get()) {
            return
        }
        when (mode) {
            ControlMode.PercentOutput -> {
                //            val elevatorPosition = talon.getSelectedSensorPosition(0)
//            val nearEdge = elevatorPosition > (maxElevatorPosition - slowDownRange)
//                    || elevatorPosition < slowDownRange
                // If the elevator is near the edge, use only half of the provided speed
//            val percentOutput = if (nearEdge) speed / 2 else speed
                // Set the speed of the motor
                talon.set(ControlMode.PercentOutput, speed) //TODO: Do this properly
            }
            ControlMode.MotionMagic -> {
                talon.set(mode, if (speed < maxElevatorPosition) speed else maxElevatorPosition.toDouble())
            }
            else -> talon.set(mode, speed)
        }
    }

    // Set the speed of the elevator
    fun setSpeed(speed: Double) {
        set(ControlMode.PercentOutput, speed)
    }

    // Set the position of the elevator using one of the presets
    fun setPosition(position: Position) {
        // Use motion magic with the number corresponding to the position
        talon.set(ControlMode.MotionMagic, position.ticks.toDouble())
    }

    fun reset() {
        talon.stop()
        talon.setSelectedSensorPosition(0, 0, 0)
    }

    // Enum that holds tick values for the various positions that the elevator must go to
    enum class Position(val ticks: Int) {
        INTAKE(0),
        EXCHANGE(3000),
        SWITCH(15000),
        SCALE(MAX_POSITION_TICKS)
    }
}
