// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.MecanumDriveKinematics;

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
  }

  public static class ClimbConstants {
    public static final int kClimbSolenoidLPort = 0;
    public static final int kClimbSolenoidRPort = 1;
  }

  public static class IntakeConstants {
    public static final int kIntakeMotorPort = 0;
    public static final int kIntakeSpeed = 1;
    public static final int kIntakeServoPort = 0;
    public static final int kIntakeServoOut = 0;
    public static final int kIntakeServoIn = 1;
  }

  public static class ArmConstants {
    public static final int kArmMotorPort = 10;
    public static final int[] kArmEncoderPorts = new int[]{0, 1};
    public static final int kArmSolenoidLPort = 2;
    public static final int kArmSolenoidRPort = 3;

    public static double kP = 0.05; 
    public static double kI = 0; 
    public static double kD = 0; 
    public static double kIz = 0; 
    public static double kFF = 0; 
    public static double kMaxOutput = 1; 
    public static double kMinOutput = -1;
  }

  public static class ShooterConstants {
    public static final int kShooterMotorLeftPort = 3;
    public static final int kShooterMotorRightPort = 4;
    public static final int kShooterSpeed = 1;
    public static final int kShooterSolenoidLPort = 4;
    public static final int kShooterSolenoidRPort = 5;
  }

  public static final class DriveConstants {
    public static final int kFrontLeftMotorPort = 13;
    public static final int kRearLeftMotorPort = 15;
    public static final int kFrontRightMotorPort = 12;
    public static final int kRearRightMotorPort = 14;

    public static final double kTrackWidth = 0.5;
    // Distance between centers of right and left wheels on robot
    public static final double kWheelBase = 0.7;
    // Distance between centers of front and back wheels on robot

    public static final MecanumDriveKinematics kDriveKinematics =
        new MecanumDriveKinematics(
            new Translation2d(kWheelBase / 2, kTrackWidth / 2),
            new Translation2d(kWheelBase / 2, -kTrackWidth / 2),
            new Translation2d(-kWheelBase / 2, kTrackWidth / 2),
            new Translation2d(-kWheelBase / 2, -kTrackWidth / 2));

    // These are example values only - DO NOT USE THESE FOR YOUR OWN ROBOT!
    // These characterization values MUST be determined either experimentally or theoretically
    // for *your* robot's drive.
    // The SysId tool provides a convenient method for obtaining these values for your robot.
    public static final SimpleMotorFeedforward kFeedforward =
        new SimpleMotorFeedforward(1, 0.8, 0.15);

    // Example value only - as above, this must be tuned for your drive!
    public static final double kPFrontLeftVel = 0.5;
    public static final double kPRearLeftVel = 0.5;
    public static final double kPFrontRightVel = 0.5;
    public static final double kPRearRightVel = 0.5;

  }
  
  //enum class for shooter solenoid state
  public enum SolenoidState {
    UP,
    DOWN
  }
}
