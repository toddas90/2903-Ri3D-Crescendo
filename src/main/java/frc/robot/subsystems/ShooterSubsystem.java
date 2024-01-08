package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import frc.robot.Constants.ShooterConstants;
import frc.robot.Constants.SolenoidState;

public class ShooterSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  public ShooterSubsystem() {}

  private final CANSparkMax m_shootertest = new CANSparkMax(0, MotorType.kBrushless);
  private final CANSparkMax m_shooterLeft = new CANSparkMax(ShooterConstants.kShooterMotorLeftPort, MotorType.kBrushless);
  private final CANSparkMax m_shooterRight = new CANSparkMax(ShooterConstants.kShooterMotorRightPort, MotorType.kBrushless);

  private final Solenoid m_shooterSolenoid = new Solenoid(PneumaticsModuleType.CTREPCM, ShooterConstants.kShooterSolenoidPort);

  public void setTestShooterSpeed(double speed) {
    m_shootertest.set(speed);
  }

  public void stopTestShooter() {
    m_shootertest.set(0);
  }

  public void setShooterSpeed(double speed) {
    m_shooterLeft.set(speed);
    m_shooterRight.set(speed);
  }

  public void stopShooter() {
    m_shooterLeft.set(0);
    m_shooterRight.set(0);
  }

  public void setSolenoid(SolenoidState state) {
    if (state == SolenoidState.UP) {
      m_shooterSolenoid.set(true);
    } else {
      m_shooterSolenoid.set(false);
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Shooter Speed", m_shooterLeft.getEncoder().getVelocity());
    SmartDashboard.putBoolean("Shooter Solenoid", m_shooterSolenoid.get());

    SmartDashboard.putNumber("Test Shooter Speed", m_shootertest.getEncoder().getVelocity());
  }

  public Command runShooterWheels(){
    return new RunCommand(() -> setShooterSpeed(ShooterConstants.kShooterSpeed), this);
  }

  public Command setShooterSolenoid(SolenoidState state){
    return new RunCommand(() -> setSolenoid(state), this);
  }
}
