package frc.lib.configs.Sparkmax;

import com.ctre.phoenix6.hardware.CANcoder;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import frc.lib.EasySpark.EasySparkConstants;
import frc.lib.Items.SparkMax.SparkController;
import frc.lib.util.CANSparkMaxUtil.Usage;
import frc.robot.Constants;

public class SwerveModuleInfo {
    public int moduleNumber;
    public SparkController drive;
    public SparkController angle;
    public CANcoder cancoder;
    public double angleOffset;
    public double xPos;

    /**Requires the module to assign cancodes correctly
     * @param moduleNumber
     */

    public SwerveModuleInfo(int moduleNumber){
        this.moduleNumber = moduleNumber;

        // drive = new SparkController(Constants.Setup.driveMotors[moduleNumber], new SparkControllerInfo().drive());
        // angle = new SparkController(Constants.Setup.angleMotors[moduleNumber], new SparkControllerInfo().angle());

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

        drive = new SparkController(Constants.Setup.driveMotors[moduleNumber], new SparkControllerInfo(driveConstants));
        angle = new SparkController(Constants.Setup.angleMotors[moduleNumber], new SparkControllerInfo(angleConstants));

        cancoder = new CANcoder(Constants.Setup.moduleCancoders[moduleNumber]);
        angleOffset = Constants.Setup.angleOffsets[moduleNumber];
        xPos = Constants.Setup.xposition[moduleNumber];
    }
}