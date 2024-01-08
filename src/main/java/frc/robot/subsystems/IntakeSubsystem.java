package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;

import frc.robot.Constants.IntakeConstants;

public class IntakeSubsystem extends SubsystemBase {
  private final CANSparkMax m_intakeMotor = new CANSparkMax(IntakeConstants.kIntakeMotorPort, MotorType.kBrushed); //needs to be changed for brushed motors
  private final Solenoid m_solenoid = new Solenoid(PneumaticsModuleType.CTREPCM, IntakeConstants.kIntakeSolenoidPort);
  private final DigitalInput m_limitSwitch = new DigitalInput(IntakeConstants.kIntakeLimitSwitchPort);
  

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

  /**
   * gets the state of the limit switch
   * @return the state of the limit switch
   */
  public boolean getIntakeSwitch() {
    return m_limitSwitch.get();
  }
}
