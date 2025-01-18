package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Constants;
import frc.robot.SubsystemConfig;

public class Test extends SubsystemCore{

    public Test(SubsystemConfig config) {
        super(config);
    }

    @Override
    public void periodic(){
        // SmartDashboard.putNumber("Prototype Motor 1 Velocity", protoEncoder1.getVelocity());
        // SmartDashboard.putNumber("Prototype Motor 2 Velocity", protoEncoder2.getVelocity());
    }
}
