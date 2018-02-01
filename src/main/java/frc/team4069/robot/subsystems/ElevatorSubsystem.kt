package frc.team4069.robot.subsystems

import com.ctre.phoenix.motorcontrol.ControlMode
import com.ctre.phoenix.motorcontrol.NeutralMode
import edu.wpi.first.wpilibj.DigitalInput
import edu.wpi.first.wpilibj.InterruptHandlerFunction
import frc.team4069.robot.io.IOMapping
import frc.team4069.robot.motors.TalonMotor


/**
 * Subsystem for controlling the cube elevator
 */
object ElevatorSubsystem : SubsystemBase() {
    // Motor to control
    val talon = TalonMotor(IOMapping.ELEVATOR_CAN_BUS)
    private val limitSwitch = DigitalInput(0)

    val position: Double
        get() = talon.getSelectedSensorPosition(0).toDouble()

    init {
        // Set up a rising edge interrupt for limitSwitch, stop the talon and zero the sensor when it's tripped
        limitSwitch.requestInterrupts(object : InterruptHandlerFunction<Any>() {
            override fun interruptFired(interruptAssertedMask: Int, param: Any?) {
                talon.stop()
                talon.setSelectedSensorPosition(0, 0, 10)
            }
        })

        // Enable interrupts for the limit switch
        limitSwitch.enableInterrupts()

        // Stop the elevator from coasting when the talon is stopped (probably)
        talon.setNeutralMode(NeutralMode.Brake)

        // Set the feed-forward gain
        talon.config_kF(0, 0.5, 10)
        talon.setSelectedSensorPosition(0, 0, 10)
    }

    /**
     * Starts the elevator motor, rotating so that the elevator will go in [direction]
     */
    fun start(direction: Direction) = talon.setConstantSpeed(if (direction == Direction.UP) 0.5 else -0.5)

    /**
     * Stops the elevator motor
     */
    fun stop() = talon.stop()

    fun set(mode: ControlMode, demand: Double) {
        var demand = demand
        if(mode == ControlMode.PercentOutput) {
            if (talon.getSelectedSensorPosition(0) - MAXIMUM_TICKS <= 500
                    || Math.abs(talon.getSelectedSensorPosition(0)) <= 500) { // TODO: Fine tune these values, check the second boolean
                demand *= 0.5
            }
        }


        talon.set(mode, demand)
    }

    /**
     * Direction to push the elevator in
     */
    enum class Direction {
        UP,
        DOWN
    }

    //TODO: Fill in tick values
    enum class Position(val ticks: Int) {
        MINIMUM(-1),
        GRAB_CUBE(-1),
        EXCHANGE(-1),
        SWITCH_HEIGHT(-1),
        SCALE_HEIGHT(MAXIMUM_TICKS)
    }

    const val MAXIMUM_TICKS = -28586
}