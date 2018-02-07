package frc.team4069.robot.subsystems

import edu.wpi.first.wpilibj.command.Subsystem

// A superclass for all subsystems used in the robot
abstract class SubsystemBase : Subsystem() {


    /**
     * Override the default command and leave it empty, because the initial command is always
     * configured in the main robot class, as opposed to on a subsystem-by-subsystem basis
     */
    override fun initDefaultCommand() {}
}
