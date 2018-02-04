package frc.team4069.robot.subsystems

import com.ctre.phoenix.motorcontrol.ControlMode
import com.ctre.phoenix.motorcontrol.NeutralMode
import frc.team4069.robot.io.IOMapping
import frc.team4069.robot.motors.TalonMotor

// Subsystem for controlling the cube elevator
object ElevatorSubsystem : SubsystemBase() {

    // The maximum number of ticks that the elevator motor can safely reach
    private const val maxElevatorPosition = 28586

    // The number of ticks around the edges of the elevator's range in which it starts to slow down
    private const val slowDownRange = 2500

    val position: Int
        get() = talon.getSelectedSensorPosition(0)

    // Motor to control
    private val talon = TalonMotor(IOMapping.ELEVATOR_CAN_BUS)

    init {
        // Stop the elevator from coasting when the talon is stopped (probably)
        talon.setNeutralMode(NeutralMode.Brake)

        // Set the feed-forward gain
        talon.config_kF(0, 0.5, 10)
        talon.setSelectedSensorPosition(0, 0, 0)
    }

    // Stops the elevator motor
    fun stop() = talon.stop()

    fun set(mode: ControlMode, speed: Double) {
        if (mode == ControlMode.PercentOutput) {
//            val elevatorPosition = talon.getSelectedSensorPosition(0)
//            val nearEdge = elevatorPosition > (maxElevatorPosition - slowDownRange)
//                    || elevatorPosition < slowDownRange
            // If the elevator is near the edge, use only half of the provided speed
//            val percentOutput = if (nearEdge) speed / 2 else speed
            // Set the speed of the motor
            talon.set(ControlMode.PercentOutput, speed)
        } else {
            talon.set(mode, speed)
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
        SCALE(maxElevatorPosition)
    }
}
