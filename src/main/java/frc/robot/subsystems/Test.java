package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.lib.util.CANSparkMaxUtil.Usage;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import frc.lib.EasySpark.*;
import frc.lib.configs.Sparkmax.SparkControllerInfo;

public class Test extends SubsystemBase {
    
    private EasySpark test;
    public EasySparkConstants constants;

    public Test() {
        EasySparkConstants constants = new EasySparkConstants();

        // For if we want custom variables in the constants
        // I swear this is the only resonable way

        // constants.customConstants.put("elevatorReduction", 9.0/1.0);

        constants.usage = Usage.kPositionOnly;
        constants.maxVoltage = 5.5;
        constants.gearReduction = 9.0/1.0;
        constants.invert = true;
        constants.idleMode = IdleMode.kBrake;
        constants.maxPosition = 90;
        constants.PID = new double[] {0.02, 0.0, 0.0, 0.0};

        EasySparkConfig config = new EasySparkConfig(17, "Test", new SparkControllerInfo(constants), constants);
        
        this.test = new EasySpark(config);
    }

    @Override
    public void periodic(){
        SmartDashboard.putNumber("Test Motor Velocity", test.encoder.getVelocity());
    }

}
