package frc.team4069.robot

import edu.wpi.first.wpilibj.IterativeRobot
import edu.wpi.first.wpilibj.command.Scheduler
import frc.team4069.robot.commands.CommandBase
import frc.team4069.robot.commands.OperatorControlCommandGroup
import frc.team4069.robot.commands.arm.StartArmCommand
import frc.team4069.robot.io.Input
import frc.team4069.robot.subsystems.ArmSubsystem
import frc.team4069.robot.subsystems.ElevatorSubsystem
import frc.team4069.robot.subsystems.VacuumSubsystem
import frc.team4069.robot.subsystems.WinchSubsystem
import frc.team4069.robot.vision.VisionData

class Robot : IterativeRobot() {
    private lateinit var scheduler: Scheduler

    // Initialise the various portions of the robot ie vision, command scheduler, IO
    override fun robotInit() {
        Input.init()

        VisionData.configureVision()
        scheduler = Scheduler.getInstance()
        CommandBase.init()
    }

    // Schedule autonomous actions
    override fun autonomousInit() {
        //TODO: Autonomous operations
        scheduler.add(StartArmCommand())
    }

    // Schedule teleop command group
    override fun teleopInit() {
        scheduler.removeAll()
        scheduler.add(OperatorControlCommandGroup())
    }

    // Reset the encoders and motors of our subsystems
    override fun disabledInit() {
        ElevatorSubsystem.reset()
        ArmSubsystem.reset()
        WinchSubsystem.reset()
        VacuumSubsystem.reset()
    }

    // Periodic operations; just run scheduler
    override fun autonomousPeriodic() = scheduler.run()
    override fun teleopPeriodic() = scheduler.run()
}