package frc.team4069.robot.motors

import com.ctre.phoenix.motorcontrol.ControlMode
import com.ctre.phoenix.motorcontrol.IMotorControllerEnhanced
import com.ctre.phoenix.motorcontrol.can.TalonSRX

/**
 * TalonSRX wrapper class with higher level APIs
 */
class TalonMotor private constructor(private val talon: TalonSRX, private val reversed: Boolean)
    : IMotorControllerEnhanced by talon {

    private val slaves = mutableListOf<TalonMotor>()

    /**
     * Set a constant speed from -1 to 1 on the talon
     */
    fun setConstantSpeed(spd: Double) {
        if (spd !in -1..1) {
            throw IllegalArgumentException("$spd not in range -1..1")
        }

        this.set(ControlMode.PercentOutput, if (this.reversed) -spd else spd)
    }

    /**
     * Stop the talon
     */
    fun stop() = neutralOutput()

    fun isStarted(): Boolean = talon.controlMode != ControlMode.Disabled

    /**
     * Get the distance traveled in rotations since it was last reset
     */
    val distanceTraveledRotations: Double
        get() {
            val quadPosition = talon.sensorCollection.quadraturePosition.toDouble()
            return quadPosition / ENCODER_TICKS_PER_ROTATION
        }

    constructor(deviceId: Int, reversed: Boolean = false, vararg slaves: Int) : this(TalonSRX(deviceId), reversed) {
//        talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10)
        talon.config_kP(0, 1.0, 10)

        for (slaveId in slaves) {
            val slave = TalonMotor(slaveId, reversed)
            slave.follow(this)
            this.slaves.add(slave)
        }
    }

    /**
     * Put constants here (static)
     */
    companion object {
        const val ENCODER_TICKS_PER_ROTATION = 4096
    }
}