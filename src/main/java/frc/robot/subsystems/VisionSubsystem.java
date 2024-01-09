// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.VisionNetParse.AprilTagData;
import frc.robot.subsystems.VisionNetParse.NoteData;

public class VisionSubsystem extends SubsystemBase {

  private VisionNetParse parser;

  public VisionSubsystem() {
    try {
      // Start receiving vision data from the network
      parser = new VisionNetParse();
      parser.start();
    } catch (Exception e) {
      // Something went wrong; no vision data is available :(
    }
  }

  // Note: if the AprilTag is not visible, the transform will be null
  public AprilTagData getAprilTagPosition(int aprilIndex) {
    return parser.curAprilTagData[aprilIndex];
  }

  // Note: if no note is visible, the bounds will be null
  public NoteData getClosestNote(int aprilIndex) {
    return parser.curNoteData;
  }
}
