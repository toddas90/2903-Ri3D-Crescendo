package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import frc.robot.Constants.ShooterConstants;

public class ShooterSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  public ShooterSubsystem() {}

  private final CANSparkMax m_shootertest = new CANSparkMax(0, MotorType.kBrushless);

  public void setShooterSpeed(double speed) {
    m_shootertest.set(speed);
  }

  public void stopShooter() {
    m_shootertest.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
