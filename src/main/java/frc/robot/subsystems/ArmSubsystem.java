package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import frc.robot.Constants.ArmConstants;
import frc.robot.Constants.IntakeConstants;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;


public class ArmSubsystem {
     private final CANSparkMax m_armMotor = new CANSparkMax(ArmConstants.kArmMotorPort, MotorType.kBrushed); //needs to be changed for brushless motors
     private final Encoder m_encoder = new Encoder(ArmConstants.kArmEncoderPorts[0], ArmConstants.kArmEncoderPorts[1]);
     private final Solenoid m_armSolenoid = new Solenoid(PneumaticsModuleType.CTREPCM, ArmConstants.kArmSolenoidPort);
}
