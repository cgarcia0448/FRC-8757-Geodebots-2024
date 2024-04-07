// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.util.Units;
/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
    public static final int kOperatorControllerPort = 1;
    
  }

  public static class LauncherConstants {
    // PWM ports/CAN IDs for motor controllers
    public static final int kFeederID = 4;
    public static final int kLauncherID = 3;

    // Current limit for launcher and feed wheels
    public static final int kLauncherCurrentLimit = 100;
    public static final int kFeedCurrentLimit = 100;

    // Speeds for wheels when intaking and launching. Intake speeds are negative to run the wheels
    // in reverse
    public static final double kLauncherSpeed = -1;
    public static final double kLaunchFeederSpeed = -1;
    public static final double kIntakeLauncherSpeed = 1;
    public static final double kIntakeFeederSpeed = 1;
    public static final double kLauncherDelay = 1;
  }


  public static final class SwerveModuleConstants {

                // https://yagsl.gitbook.io/yagsl/configuring-yagsl/standard-conversion-factors

                public static final double rotationkP = 0.5;
                public static final double driveEncoderPositionConversionFactor = 0.047286787200699704;
                public static final double rotationEncoderPositionConversionFactor = 16.8 * 0.01745;

                public static final SimpleMotorFeedforward driveFF = new SimpleMotorFeedforward(0.2, 2.5, 0.0);
                public static final double drivekP = 0.0;

  }

    public static final class SwerveBaseConstants {

                /* Drivetrain Constants */
                public static final double trackWidth = Units.inchesToMeters(22.66);
                public static final double wheelBase = Units.inchesToMeters(24.5);

                // Max module speed, in m/s
                // Drive base radius in meters. Distance from robot center to furthest module.
                public static final double driveBaseRadius = Units.inchesToMeters(17.06);

                // nominal (real) divided by fudge factor
                public static final double wheelDiameter = Units.inchesToMeters(4.0 / 1.0);
                public static final double wheelCircumference = wheelDiameter * Math.PI;

                public static final double driveGearRatio = (6.75 / 1.0); // 6.75:1
                public static final double angleGearRatio = ((150.0 / 7.0) / 1.0); // 150/7:1

                public static final SwerveDriveKinematics kinematics = new SwerveDriveKinematics(
                                new Translation2d(trackWidth / 2.0, wheelBase / 2.0), // front left
                                new Translation2d(trackWidth / 2.0, -wheelBase / 2.0), // front right
                                new Translation2d(-trackWidth / 2.0, wheelBase / 2.0), // rear left
                                new Translation2d(-trackWidth / 2.0, -wheelBase / 2.0) // rear right
                );

                /* Swerve Profiling Values */

                public static final double maxSpeed = 4.5; // meters per second

                public static final double maxAngularVelocity = 11.5;

                public static final int frontLeftRotationMotorId = 2;
                public static final int frontLeftDriveMotorId = 1;

                public static final int frontRightRotationMotorId = 7;
                public static final int frontRightDriveMotorId = 8;

                public static final int rearLeftRotationMotorId = 6;
                public static final int rearLeftDriveMotorId = 5;

                public static final int rearRightRotationMotorId = 11;
                public static final int rearRightDriveMotorId = 12;

                public static final int frontLeftRotationEncoderId =21;
                public static final int frontRightRotationEncoderId = 22;
                public static final int rearRightRotationEncoderId = 23;
                public static final int rearLeftRotationEncoderId = 24;

                public static final double frontLeftAngleOffset = Units.rotationsToRadians(0.915); // 20
                public static final double frontRightAngleOffset = Units.rotationsToRadians(0.343); // 30
                public static final double rearRightAngleOffset = Units.rotationsToRadians(0.039); // 40
                public static final double rearLeftAngleOffset = Units.rotationsToRadians(0.36); // 50

                // used
                public static final double translationkP = 3;
                public static final double rotationkP = 3;

                // Max linear velocity (M/S)
                public static final double maxVelocityMps = 3.0;

                // Max linear acceleration (M/S^2)
                public static final double maxAccelerationMpsSq = 3.0;

                // Max angular velocity (Rad/S)
                public static final double maxAngularVelocityRps = 2 * Math.PI;

                // Max angular acceleration (Rad/S^2)
                public static final double maxAngularAccelerationRpsSq = 4 * Math.PI;

                // max teleop speeds used in TeleopSwerve
                public static final double kTeleDriveMaxSpeedMetersPerSecond = 7.5 / 4.0;
                public static final double kTeleDriveMaxAngularSpeedRadiansPerSecond = 3.5;

        }
}
