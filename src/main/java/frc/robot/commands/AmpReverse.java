// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.AmpScorer;

public class AmpReverse extends Command {
  /** Creates a new ArmReverse. */

  public AmpScorer ampScorer;

  public AmpReverse(AmpScorer ampScorer) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.ampScorer = ampScorer;
    addRequirements(ampScorer);
  }

//   Called when the command is initially scheduled.
  @Override
  public void initialize() {
    ampScorer.Reverse();
    //System.out.println("Reverse");
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
