package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import frc.robot.Constants.ArmConstants;
import edu.wpi.first.wpilibj.Encoder;


public class ArmSubsystem {
     private final CANSparkMax m_armMotor = new CANSparkMax(ArmConstants.kArmMotorPort, MotorType.kBrushed); //needs to be changed for brushless motors
     private final Encoder m_encoder = new Encoder(ArmConstants.kArmEncoderPorts[0], ArmConstants.kArmEncoderPorts[1]);
}
