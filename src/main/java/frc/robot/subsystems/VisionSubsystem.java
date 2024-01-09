// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.HashSet;
import java.util.Set;

import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
  public AprilTagData getAprilTag(int aprilIndex) {
    return parser.curAprilTagData[aprilIndex];
  }

  public Set<Integer> getVisibleAprilTags() {
    Set<Integer> tags = new HashSet<Integer>();
    for (AprilTagData dat : parser.curAprilTagData) {
      if (dat.transform != null) {
        tags.add(dat.id);
      }
    }
    return tags;
  }

  // Note: if no note is visible, the bounds will be null
  public NoteData getClosestNote() {
    return parser.curNoteData;
  }

  @Override
  public void periodic() {
    // AprilTag telemetry (x points right, y points up, z points in)
    Set<Integer> aprilTagIds = getVisibleAprilTags();
    String[] tagInfo = new String[aprilTagIds.size()];
    int i = 0;
    for (int id : aprilTagIds) {
      Transform3d data = getAprilTag(id).transform;
      tagInfo[i] = String.format("ID %d: x=%.4f, y=%.4f, z=%.4f, rot=%.4f",
        id, data.getX(), data.getY(), data.getZ(), data.getRotation().getY());
    }
    SmartDashboard.putStringArray("AprilTag Locations", tagInfo);

    // Note telemetry
    NoteData noteDat = getClosestNote();
    boolean noteVisible = noteDat.bounds != null;
    SmartDashboard.putBoolean("Note Is Visible", noteVisible);
    if (noteVisible) {
      SmartDashboard.putNumber("Note X", noteDat.bounds.x + noteDat.bounds.width / 2);
      SmartDashboard.putNumber("Note Y", noteDat.bounds.y + noteDat.bounds.height / 2);
    }
  }
}
