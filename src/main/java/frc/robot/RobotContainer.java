package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveSubsystem m_driveSubsystem = new DriveSubsystem();
  private final IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem();
  private final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();
  private final double DEADZONE_THRESH = 0.1;
  
  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final XboxController m_driverController =
      new XboxController(OperatorConstants.kDriverControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Configure default commands
    // Set the default drive command to split-stick arcade drive
    m_driveSubsystem.setDefaultCommand(
        // A split-stick arcade command, with forward/backward controlled by the left
        // hand, and turning controlled by the right.
        new RunCommand(
            () ->
                m_driveSubsystem.drive(
                    deadzone(m_driverController.getLeftY()),
                    -deadzone(m_driverController.getRightX()),
                    -deadzone(m_driverController.getLeftX()),
                    false),
            m_driveSubsystem));

        // Drive at half speed when the right bumper is held
    new JoystickButton(m_driverController, Button.kRightBumper.value)
        .onTrue(new InstantCommand(() -> m_driveSubsystem.setMaxOutput(0.5)))
        .onFalse(new InstantCommand(() -> m_driveSubsystem.setMaxOutput(1)));

    // Configure Y button to intake inwards
    new JoystickButton(m_driverController, Button.kY.value)
        .onTrue(new InstantCommand(() -> m_intakeSubsystem.runIntake(true)))
        .onFalse(new InstantCommand(() -> m_intakeSubsystem.stopIntake()));
    
    // Configure X button to intake outwards (fix later)
    new JoystickButton(m_driverController, Button.kX.value)
        .onTrue(new InstantCommand(() -> m_intakeSubsystem.runIntake(false)))
        .onFalse(new InstantCommand(() -> m_intakeSubsystem.stopIntake()));

    // Configure A button to test shooter
    new JoystickButton(m_driverController, Button.kA.value)
        .onTrue(new InstantCommand(() -> m_shooterSubsystem.setShooterSpeed(1)))
        .onFalse(new InstantCommand(() -> m_shooterSubsystem.stopShooter()));
  }
  
  private double deadzone(double val) {
    return (Math.abs(val) > DEADZONE_THRESH) ? val : 0;
  }

}
