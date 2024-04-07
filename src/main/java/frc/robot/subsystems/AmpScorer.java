// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class AmpScorer extends SubsystemBase {
    /** Creates a new HangArms. */

    private DoubleSolenoid arm;
    private final Compressor arm_compressor = new Compressor(PneumaticsModuleType.CTREPCM);

    public AmpScorer() {
        arm = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 1);
    }

    public void Forward() {
        arm.set(Value.kForward);
    }

    public void Reverse() {
        arm.set(Value.kReverse);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}
