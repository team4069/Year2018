package frc.team4069.robot.subsystems

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
    val talon = TalonMotor(IOMapping.ELEVATOR_CAN_BUS, reversed = true)
    private val limitSwitch = DigitalInput(0)

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
        talon.config_kF(0, 0.5, 10);
    }

    /**
     * Starts the elevator motor, rotating so that the elevator will go in [direction]
     */
    fun start(direction: Direction) = talon.setConstantSpeed(if (direction == Direction.UP) 0.5 else -0.5)

    /**
     * Stops the elevator motor
     */
    fun stop() = talon.stop()

    /**
     * Direction to push the elevator in
     */
    enum class Direction {
        UP,
        DOWN
    }
}