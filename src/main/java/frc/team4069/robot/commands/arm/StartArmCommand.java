package frc.team4069.robot.commands.arm;

import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.team4069.robot.commands.CommandBase;
import frc.team4069.robot.subsystems.ArmSubsystem;

public class StartArmCommand extends CommandBase {

    public StartArmCommand() {
        requires (armSubsystem);
    }

    protected void initialize() {
//        ArmSubsystem.talon.set(ControlMode.PercentOutput, 1.0)
        ArmSubsystem.talon.set(ControlMode.MotionMagic, ArmSubsystem.Position.MAX.ticks.toDouble());
    }

    @Override
    protected boolean isFinished(){
        return true;
    }
}
