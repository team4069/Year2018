package frc.team4069.robot.motors;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import java.util.HashSet;
import java.util.Set;

public class TalonSRXMotor extends WPI_TalonSRX {
    public static final int ENCODER_TICKS_PER_ROTATION = 4096;

    private Set<TalonSRXMotor> slaves = new HashSet<>();

    public TalonSRXMotor(int deviceNumber, boolean reversed, int... slaveIds) {
        super(deviceNumber);

        this.setInverted(reversed);

        for(int slaveNumber : slaveIds) {
            TalonSRXMotor slave = new TalonSRXMotor(slaveNumber, reversed);
            slave.follow(this);
            slaves.add(slave);
        }
    }

    public TalonSRXMotor(int deviceNumber) {
        this(deviceNumber, false);
    }

    public TalonSRXMotor(int deviceNumber, int... slaves) {
        this(deviceNumber, false, slaves);
    }

    public boolean isStarted() {
        return getControlMode() != ControlMode.Disabled;
    }

    public void stop() {
        this.neutralOutput();
    }

    public void setConstantSpeed(double speed) {
        if(speed < -1) {
            speed = -1;
        }
        if(speed > 1) {
            speed = 1;
        }

        set(ControlMode.PercentOutput, speed);
    }

    public double getDistanceTraveledRotations() {
        double quadPosition = getSensorCollection().getQuadraturePosition();
        return quadPosition / ENCODER_TICKS_PER_ROTATION;
    }
}
