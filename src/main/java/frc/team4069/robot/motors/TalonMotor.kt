package frc.team4069.robot.motors

import edu.wpi.first.wpilibj.Encoder
import edu.wpi.first.wpilibj.SpeedController
import edu.wpi.first.wpilibj.Talon

/**
 * Wrapper for Talon motor controllers
 *
 * Can be used throughout the code in place of [edu.wpi.first.wpilibj.Talon]
 */
class TalonMotor private constructor(private val talon: Talon,
                                     reversed: Boolean, metresPerTick: Double) : Motor(reversed), SpeedController by talon {
    // Encoder doesn't have to be provided, so it's made nullable
    var encoder: Encoder? = null

    constructor(port: Int,
                reversed: Boolean, metresPerTick: Double) : this(Talon(port),
            reversed, metresPerTick)

    constructor(port: Int, encoderPort1: Int, encoderPort2: Int,
                reversed: Boolean, metresPerTick: Double) : this(Talon(port), reversed, metresPerTick) {
        encoder = Encoder(encoderPort1, encoderPort2)
    }

    init {
        encoder?.setReverseDirection(reversed)
        encoder?.distancePerPulse = metresPerTick
    }

    override fun getSpeed(): Double {
        return talon.get()
    }

    override fun setSpeedUnsafe(speed: Double) {
        talon.set(speed)
    }

    override fun getDistanceTraveledMeters(): Double {
        return encoder?.distance ?: 0.0
    }

    override fun resetDistanceTraveled() {
        encoder?.reset()
    }

    /**
     * Sets constant speed on the provided motor
     * Analogous to [setConstantSpeed], does not set before checking the provided speed is valid
     */
    override fun set(speed: Double) {
        setConstantSpeed(speed)
    }
}
