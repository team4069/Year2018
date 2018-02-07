package frc.team4069.robot.subsystems

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import frc.team4069.robot.io.IOMapping;
import frc.team4069.robot.motors.TalonMotor;

public class ArmSubsystem extends SubsystemBase {
    TalonMotor talon = new TalonMotor(IOMapping.ARM_CAN_BUS);

    val position: Int
        get() = talon.getSelectedSensorPosition(0)

    init {
        talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0)
        talon.setSelectedSensorPosition(0, 0, 0)
        talon.config_kF(0, 1.04936530324400564174894217207334273624823695345557122708039492242595204513399153737658674188998589562764456981664, 0)
        talon.config_kP(0, 1.0, 0)
    }

    public void rotateTo(pos Position) {
        talon.set(ControlMode.MotionMagic, pos.ticks.toDouble())
    }

    public void reset() {
        talon.stop()
        talon.setSelectedSensorPosition(0, 0, 0)
    }

    public enum class Position(int ticks) {
        MAX(2462),
        MIN(150)
    }
}