package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.ClimbConstants;

public class ClimbSubsystem extends SubsystemBase {
  private final DoubleSolenoid m_climbSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM,ClimbConstants.kClimbSolenoidLPort, ClimbConstants.kClimbSolenoidRPort);

  public ClimbSubsystem() {
    m_climbSolenoid.set(Value.kReverse);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putBoolean("Climb Solenoid", getClimbSolenoid());
  }

  public boolean getClimbSolenoid() {
    return m_climbSolenoid.get() == Value.kForward;
  }

  public void toggleSolenoid() {
    m_climbSolenoid.toggle();
  }

  public Command setClimbSolenoid(){
    return new InstantCommand(() -> toggleSolenoid(), this);
  }

}
