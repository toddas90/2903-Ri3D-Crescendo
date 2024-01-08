package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.Constants.ClimbConstants;
import frc.robot.Constants.SolenoidState;

public class ClimbSubsystem extends SubsystemBase {
  private final Solenoid m_climbSolenoid = new Solenoid(PneumaticsModuleType.CTREPCM,ClimbConstants.kClimbSolenoidPort);

  public ClimbSubsystem() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public boolean getClimbSolenoid() {
    return m_climbSolenoid.get();
  }

  public Command setClimbSolenoid(){
    return new RunCommand(() -> m_climbSolenoid.set(!getClimbSolenoid()), this);
  }

}
