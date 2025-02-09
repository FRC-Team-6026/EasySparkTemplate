package frc.lib.EasySpark;

import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import frc.lib.configs.Sparkmax.SparkControllerInfo;
import frc.lib.util.CANSparkMaxUtil.Usage;

public class EasySparkControllerInfo {

    public Usage canbusUse;
    public int currentLim;
    public boolean invert;
    public IdleMode idleMode;
    public double posConversion;
    public double velConversion;
    public double[] pidList;
    public double voltageComp;

    public SparkControllerInfo scInfo;
    public EasySparkConstants constants;

    /**
     * Takes an EasySparkConstants and creates a new EasySparkControllerInfo Object.
     * 
     * @param constants The {@linkplain EasySparkConstants} that will be used for the spark controller.
     * 
     * @see EasySpark
     * @see EasySparkConfig
     */
    public EasySparkControllerInfo(EasySparkConstants constants) {
        SparkControllerInfo scInfo = new SparkControllerInfo(constants);

        scInfo.canbusUse = constants.usage;
        scInfo.currentLim = constants.currentLim;
        scInfo.invert = constants.invert;
        scInfo.idleMode = constants.idleMode;
        scInfo.posConversion = constants.posConversion;
        scInfo.velConversion = constants.velConversion;
        scInfo.pidList = constants.PID;
        scInfo.voltageComp = constants.voltageComp;

        this.scInfo = scInfo;
        this.constants = constants;
    }
    
    /**
     * Takes in a SparkControllerInfo Object and injects information into it
     * automatically from the inputted EasySparkConstants.
     * 
     * @param scInfo The SparkControllerInfo Object that you want to change.
     * @param constants The {@linkplain EasySparkConstants} that will be used for the spark controller.
     * 
     * @see EasySpark
     * @see EasySparkConfig
     */
    public EasySparkControllerInfo(SparkControllerInfo scInfo, EasySparkConstants constants) {
        scInfo.canbusUse = constants.usage;
        scInfo.currentLim = constants.currentLim;
        scInfo.invert = constants.invert;
        scInfo.idleMode = constants.idleMode;
        scInfo.posConversion = constants.posConversion;
        scInfo.velConversion = constants.velConversion;
        scInfo.pidList = constants.PID;
        scInfo.voltageComp = constants.voltageComp;

        this.scInfo = scInfo;
        this.constants = constants;
    }
}