package frc.lib.configs.Sparkmax;

import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import frc.lib.util.CANSparkMaxUtil.Usage;
import frc.lib.EasySpark.EasySparkControllerInfo;
import frc.lib.EasySpark.EasySparkConstants;

public class SparkControllerInfo {
    public Usage canbusUse;
    public int currentLim;
    public boolean invert;
    public IdleMode idleMode;
    public double posConversion;
    public double velConversion;
    public double[] pidList;
    public double voltageComp;

    public SparkControllerInfo scInfo;

    // public SparkControllerInfo drive(){
    //     canbusUse = Usages.driveUsage;
    //     currentLim = Electical.driveCurrentLim;
    //     invert = Setup.driveInvert;
    //     idleMode = IdleModes.driveIdle;
    //     posConversion = ConversionFactors.driveConversionPositionFactor;
    //     velConversion = ConversionFactors.driveConversionVelocityFactor;
    //     pidList = PID.drivePID;
    //     voltageComp = Electical.voltageComp;
    //     return this;
    // }

    // public SparkControllerInfo angle(){
    //     canbusUse = Usages.angleUsage;
    //     currentLim = Electical.angleCurrentLim;
    //     invert = Setup.angleInvert;
    //     idleMode = IdleModes.angleIdle;
    //     posConversion = ConversionFactors.angleConversionPositionFactor;
    //     velConversion = ConversionFactors.angleConversionVelocityFactor;
    //     pidList = PID.anglePID;
    //     voltageComp = Electical.voltageComp;
    //     return this;
    // }

    public SparkControllerInfo(EasySparkConstants constants) {
        this.scInfo = new EasySparkControllerInfo(this, constants).scInfo;
    }
}
