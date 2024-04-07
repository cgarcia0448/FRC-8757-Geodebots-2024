// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.HangArms;

public class ArmForward extends Command {

    public HangArms hangarms;

    /** Creates a new ArmForward. */
    public ArmForward(HangArms hangarms) {
        // Use addRequirements() here to declare subsystem dependencies.
        this.hangarms = hangarms;
        addRequirements(hangarms);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        hangarms.Forward();
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        hangarms.Stop();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
