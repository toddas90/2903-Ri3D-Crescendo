package frc.robot.subsystems;

import com.revrobotics.CANSparkBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import com.revrobotics.SparkRelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;
import frc.robot.Constants.ArmConstants;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class ArmSubsystem extends SubsystemBase {
     private final CANSparkMax m_armMotor = new CANSparkMax(ArmConstants.kArmMotorPort, MotorType.kBrushed); //needs to be changed for brushless motors
     
     private SparkPIDController m_pidController = m_armMotor.getPIDController();
     private final RelativeEncoder m_encoder = m_armMotor.getEncoder(SparkRelativeEncoder.Type.kQuadrature, 4096);
     private final DoubleSolenoid m_armSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM,ArmConstants.kArmSolenoidLPort, ArmConstants.kArmSolenoidRPort);

     private double targetDegrees = 0;

     public ArmSubsystem() {
          // set PID coefficients
          m_pidController.setP(ArmConstants.kP);
          m_pidController.setI(ArmConstants.kI);
          m_pidController.setD(ArmConstants.kD);
          m_pidController.setIZone(ArmConstants.kIz);
          m_pidController.setFF(ArmConstants.kFF);
          m_pidController.setOutputRange(ArmConstants.kMinOutput, ArmConstants.kMaxOutput);
          // make encoder use degrees instead of rotations
          m_encoder.setPositionConversionFactor(360.0);
          // Put PID values in SmartDashboard for tuning
          SmartDashboard.putNumber("P Gain", ArmConstants.kP);
          SmartDashboard.putNumber("I Gain", ArmConstants.kI);
          SmartDashboard.putNumber("D Gain", ArmConstants.kD);
          SmartDashboard.putNumber("I Zone", ArmConstants.kIz);
          SmartDashboard.putNumber("Feed Forward", ArmConstants.kFF);
          SmartDashboard.putNumber("Max Output", ArmConstants.kMaxOutput);
          SmartDashboard.putNumber("Min Output", ArmConstants.kMinOutput);
          SmartDashboard.putNumber("Arm Target Angle", 0);
          
          m_armSolenoid.set(Value.kForward);
     }

     private void UpdatePIDCoefficients() {
          // read PID coefficients from SmartDashboard
          double p = SmartDashboard.getNumber("P Gain", 0);
          double i = SmartDashboard.getNumber("I Gain", 0);
          double d = SmartDashboard.getNumber("D Gain", 0);
          double iz = SmartDashboard.getNumber("I Zone", 0);
          double ff = SmartDashboard.getNumber("Feed Forward", 0);
          double max = SmartDashboard.getNumber("Max Output", 0);
          double min = SmartDashboard.getNumber("Min Output", 0);

          // if PID coefficients on SmartDashboard have changed, write new values to controller
          if((p != ArmConstants.kP)) { m_pidController.setP(p); ArmConstants.kP = p; }
          if((i != ArmConstants.kI)) { m_pidController.setI(i); ArmConstants.kI = i; }
          if((d != ArmConstants.kD)) { m_pidController.setD(d); ArmConstants.kD = d; }
          if((iz != ArmConstants.kIz)) { m_pidController.setIZone(iz); ArmConstants.kIz = iz; }
          if((ff != ArmConstants.kFF)) { m_pidController.setFF(ff); ArmConstants.kFF = ff; }
          if((max != ArmConstants.kMaxOutput) || (min != ArmConstants.kMinOutput)) { 
               m_pidController.setOutputRange(min, max); 
               ArmConstants.kMinOutput = min; ArmConstants.kMaxOutput = max; 
          }

     }

     public double getDegrees() {
          return m_encoder.getPosition();
     }

     public void zeroEncoder() {
          m_encoder.setPosition(0);
     }

     public void goToDegrees(double degrees) {
          targetDegrees = degrees;
          m_pidController.setReference(degrees, CANSparkBase.ControlType.kPosition);
          SmartDashboard.putNumber("Arm Target Angle", degrees);
     }

     public enum ArmPosition {
          Initial,
          Pickup,
          Amp,
          Speaker
     }

     public Command setArmPosition(ArmPosition pos) {
          return new InstantCommand(() -> {
               int armAngle;
               switch (pos) {
               case Initial:
                    armAngle = 0;
                    break;
               case Pickup:
                    armAngle = 0;
                    break;
               case Amp:
                    armAngle = 0;
                    break;
               case Speaker:
                    armAngle = 0;
                    break;
               default:
                    // Don't do anything
                    return;
               }
               goToDegrees(armAngle);
          }, this);
     }

     @Override
     public void periodic() {
          SmartDashboard.putNumber("Arm Current Angle", getDegrees());

          double newTargetDegrees = SmartDashboard.getNumber("Arm Target Angle", 0);
          if (targetDegrees != newTargetDegrees) { goToDegrees(newTargetDegrees); }
          
          UpdatePIDCoefficients();
     }
}
