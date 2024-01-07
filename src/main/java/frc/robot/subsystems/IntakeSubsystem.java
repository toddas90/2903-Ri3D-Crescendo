package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import frc.robot.Constants.IntakeConstants;

public class IntakeSubsystem extends SubsystemBase {
  private final CANSparkMax m_intakeMotor = new CANSparkMax(IntakeConstants.kIntakeMotorPort, MotorType.kBrushless); //needs to be changed for brushed motors

  public IntakeSubsystem() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  /**
  * Runs the intake motor
  * @param direction the direction the intake motor will run, true for intake, false for outtake
  */
  public void runIntake(boolean direction) {
    m_intakeMotor.set(direction ? IntakeConstants.kIntakeSpeed : -IntakeConstants.kIntakeSpeed);
  }
}
