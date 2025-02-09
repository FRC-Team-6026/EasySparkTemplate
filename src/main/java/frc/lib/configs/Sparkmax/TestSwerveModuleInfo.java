package frc.lib.configs.Sparkmax;

import com.ctre.phoenix6.hardware.CANcoder;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import frc.lib.EasySpark.EasySparkControllerInfo;
import frc.lib.EasySpark.EasySparkConstants;
import frc.lib.EasySpark.EasySparkConfig;
import frc.lib.EasySpark.EasySpark;
import frc.lib.util.CANSparkMaxUtil.Usage;
import frc.robot.Constants;

public class TestSwerveModuleInfo {
    public int moduleNumber;
    public EasySpark drive;
    public EasySpark angle;
    public CANcoder cancoder;
    public double angleOffset;
    public double xPos;

    /**Requires the module to assign cancodes correctly
     * @param moduleNumber
     */

    public TestSwerveModuleInfo(int moduleNumber){
        this.moduleNumber = moduleNumber;

        EasySparkConstants driveConstants = new EasySparkConstants();
        driveConstants.idleMode = IdleMode.kBrake;
        driveConstants.posConversion = Constants.Swerve.wheelCircimference / Constants.Swerve.driveGearRatio;
        driveConstants.velConversion = driveConstants.posConversion / 60;
        driveConstants.PID = new double[] {0.3, 0.0, 0.0, 0.0};

        EasySparkConstants angleConstants = new EasySparkConstants();
        angleConstants.usage = Usage.kPositionOnly;
        angleConstants.currentLim = 20;
        angleConstants.invert = true;
        angleConstants.idleMode = IdleMode.kBrake;
        angleConstants.posConversion = 360.0 / Constants.Swerve.angleGearRatio;
        angleConstants.velConversion = angleConstants.posConversion / 60;
        angleConstants.PID = new double[] {0.01, 0.0, 0.0, 0.0};

        cancoder = new CANcoder(Constants.Setup.moduleCancoders[moduleNumber]);
        angleOffset = Constants.Setup.angleOffsets[moduleNumber];
        xPos = Constants.Setup.xposition[moduleNumber];

        EasySparkConfig driveConfig = new EasySparkConfig(Constants.Setup.driveMotors[moduleNumber], "driveMotor " + moduleNumber, new EasySparkControllerInfo(driveConstants), cancoder);
        EasySparkConfig angleConfig = new EasySparkConfig(Constants.Setup.angleMotors[moduleNumber], "angleMotor " + moduleNumber, new EasySparkControllerInfo(angleConstants), cancoder);

        drive = new EasySpark(driveConfig);
        angle = new EasySpark(angleConfig);
    }
}