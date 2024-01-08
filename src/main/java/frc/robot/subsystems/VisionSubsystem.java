// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.VisionNetParse.VisionData;

public class VisionSubsystem extends SubsystemBase {

  private VisionNetParse parser;

  /** Creates a new ExampleSubsystem. */
  public VisionSubsystem() {
    try {
      parser = new VisionNetParse();
      parser.start();
    } catch (Exception e) {

    }
  }

  public VisionData getVisionData(int aprilIndex) {
    return parser.getCurrData(aprilIndex);
  }
}
