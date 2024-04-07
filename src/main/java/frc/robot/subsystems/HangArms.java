// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class HangArms extends SubsystemBase {
    /** Creates a new HangArms. */

    private CANSparkMax leftMotor = new CANSparkMax(9, MotorType.kBrushless);
    private CANSparkMax rightMotor = new CANSparkMax(10, MotorType.kBrushless);

    public HangArms() {
    }

    public void Forward() {
        leftMotor.set(1);
        rightMotor.set(1);
    }

    public void Reverse() {
        leftMotor.set(-1);
        rightMotor.set(-1);
    }

    public void Stop() {
        leftMotor.set(0);
        rightMotor.set(0);
    }

    public void setSpeed(double speedLeft, double speedRight) {
        leftMotor.set(speedLeft);
        rightMotor.set(speedRight);
        
    }
     public void Hold() {
        leftMotor.set(6/RobotController.getBatteryVoltage());
        rightMotor.set(6/RobotController.getBatteryVoltage());
    }
    //Setting power limits to each of the motors

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}
