// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.AmpReverse;
import frc.robot.commands.ArmForward;
import frc.robot.commands.ArmHold;
import frc.robot.commands.ArmReverse;
import frc.robot.commands.AmpForward;
// import frc.robot.commands.ArmReverse;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.TeleopSwerveCmd;
import frc.robot.subsystems.CANLauncher;
import frc.robot.subsystems.ExampleSubsystem;
// import frc.robot.subsystems.HangArms;
import frc.robot.subsystems.SwerveBase;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.LauncherConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.LaunchNote;
import frc.robot.commands.PrepareLaunch;
import frc.robot.subsystems.CANLauncher;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems are defined here.
  // private final CANLauncher m_launcher = new CANLauncher();
  private final CANLauncher m_launcher = new CANLauncher();

  private final CommandXboxController m_operatorController = new CommandXboxController(1);
  public static final XboxController logitech = new XboxController(0);
  private static final SwerveBase swervebase = new SwerveBase();

  // private static final HangArms hangarms = new HangArms();


  // The robot's subsystems and commands are defined here...
  private final ExampleCommand exampleCommand = new ExampleCommand(swervebase);

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();

    swervebase.setDefaultCommand(
      new TeleopSwerveCmd(swervebase, 
      () -> -logitech.getLeftY(), 
      () -> -logitech.getLeftX(),
      () -> logitech.getRightX(),
      () -> logitech.getRightTriggerAxis(),
      () -> true,
      () -> logitech.getLeftBumper(),
      () -> logitech.getLeftBumper(),
      () -> logitech.getStartButton()));
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    m_driverController.rightBumper().whileTrue(exampleCommand);

    // Set the default command for the drivetrain to drive using the joysticks
    /*Create an inline sequence to run when the operator presses and holds the A (green) button. Run the PrepareLaunch
     * command for 1 seconds and then run the LaunchNote command */
    m_operatorController
        .rightBumper()
        .whileTrue(
            new PrepareLaunch(m_launcher)
                .withTimeout(LauncherConstants.kLauncherDelay)
                .andThen(new LaunchNote(m_launcher))
                .handleInterrupt(() -> m_launcher.stop()));

    // Set up a binding to run the intake command while the operator is pressing and holding the
    // left Bumper
    m_operatorController.leftBumper().whileTrue(m_launcher.getIntakeCommand());
    m_driverController.rightTrigger(0.3).onTrue(new InstantCommand(swervebase::resetHeading, swervebase));
    
    m_operatorController.y().onTrue(new AmpForward(Robot.as));
    m_operatorController.a().onTrue(new AmpReverse(Robot.as));
    m_operatorController.x().whileTrue(new ArmHold(Robot.ha)); // Press X to hold the robot in place (Must be in Brake Mode)
    //m_operatorController.b().whileTrue(new ArmReverse(Robot.ha));

    Command hangArmSpeed = Commands.run(() -> {
   double speedLeft = m_operatorController.getLeftY(); 
   double speedRight = m_operatorController.getRightY(); 
   Robot.ha.setSpeed(MathUtil.applyDeadband(speedLeft, .1), MathUtil.applyDeadband(speedRight,.1)); //Can change value of second argument to lessen the recoil of spring in joystick
    });
    hangArmSpeed.addRequirements(Robot.ha);
    Robot.ha.setDefaultCommand(hangArmSpeed);
    // Setting up individual speed for each arm for each joystick (Left and Right)
  }

    //m_operatorController.x().whileTrue(ha.kForward);

  // }



  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
   public Command getAutonomousCommand() {
    // return new SimpleAuto(m_launcher, swervebase);
    // An example command will be run in autonomous
    
    ChassisSpeeds speeds = ChassisSpeeds.fromFieldRelativeSpeeds(-5, 0, 0, new Rotation2d());

    return new SequentialCommandGroup(
      // new InstantCommand(() -> {m_launcher.setLaunchWheel(-1);}, m_launcher),
      new PrepareLaunch(m_launcher).withTimeout(3),
      new LaunchNote(m_launcher).withTimeout(5),
      new RunCommand(() -> swervebase.drive(-2, 0, 0, false))
    );
  }
}
