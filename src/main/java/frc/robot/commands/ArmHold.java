package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.HangArms;

//public class ArmHold 
    public class ArmHold extends Command {

    public HangArms hangarms;

    /** Creates a new ArmHold. */
    public ArmHold(HangArms hangarms) {
        // Use addRequirements() here to declare subsystem dependencies.
        this.hangarms = hangarms;
        addRequirements(hangarms);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        hangarms.Hold();
    }
    //Calling it when the button to hold the robot in space is pressed

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


