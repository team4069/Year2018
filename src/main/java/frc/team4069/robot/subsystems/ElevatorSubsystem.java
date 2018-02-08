package frc.team4069.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import frc.team4069.robot.io.IOMapping;
import frc.team4069.robot.motors.TalonSRXMotor;

public class ElevatorSubsystem extends SubsystemBase {

    public static final int MAX_POSITION_TICKS = -26901;
    private static ElevatorSubsystem instance;

    private TalonSRXMotor talon;

    public int getPosition() {
        return talon.getSelectedSensorPosition(0);
    }

    private ElevatorSubsystem() {
        talon = new TalonSRXMotor(IOMapping.ELEVATOR_CAN_BUS);

        talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
        talon.config_kF(0, 0.5, 0);
        talon.setSelectedSensorPosition(0, 0, 0);

        talon.configReverseSoftLimitThreshold(MAX_POSITION_TICKS, 0);
        talon.configForwardSoftLimitThreshold(0, 0);
        talon.configReverseSoftLimitEnable(true, 0);
        talon.configForwardSoftLimitEnable(true, 0);
    }

    public void stop() {
        talon.stop();
    }

    public void set(ControlMode mode, double speed) {
        talon.set(mode, speed);
    }

    public void setPosition(Position position) {
        set(ControlMode.MotionMagic, position.getTicks());
    }

    public void reset() {
        talon.stop();
        talon.setSelectedSensorPosition(0, 0, 0);
    }

    public static ElevatorSubsystem getInstance() {
        if (instance == null) {
            instance = new ElevatorSubsystem();
        }

        return instance;
    }

    public enum Position {
        INTAKE(0),
        EXCHANGE(-3000),
        SWITCH(15000),
        SCALE(MAX_POSITION_TICKS);

        private int ticks;

        Position(int ticks) {
            this.ticks = ticks;
        }

        public int getTicks() {
            return ticks;
        }
    }
}
