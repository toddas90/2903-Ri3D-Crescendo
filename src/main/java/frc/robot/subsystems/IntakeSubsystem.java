package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.IntakeConstants;

public class IntakeSubsystem extends SubsystemBase {
  //private final CANSparkMax m_intakeMotor = new CANSparkMax(IntakeConstants.kIntakeMotorPort, MotorType.kBrushless); //needs to be changed for brushed motors
  private final DigitalInput m_limitSwitch = new DigitalInput(IntakeConstants.kIntakeLimitSwitchPort);
  

  public IntakeSubsystem() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putBoolean("Intake Switch", getIntakeSwitch());
    //SmartDashboard.putNumber("Intake Speed", m_intakeMotor.getEncoder().getVelocity());
  }

  /**
  * Runs the intake motor
  * @param direction the direction the intake motor will run, true for intake, false for outtake
  */
  public void runIntake(boolean direction) {
    //m_intakeMotor.set(direction ? IntakeConstants.kIntakeSpeed : -IntakeConstants.kIntakeSpeed);
  }

  public void stopIntake() {
    //m_intakeMotor.set(0);
  }

  /**
   * gets the state of the limit switch
   * @return the state of the limit switch
   */
  public boolean getIntakeSwitch() {
    return m_limitSwitch.get();
  }
}
