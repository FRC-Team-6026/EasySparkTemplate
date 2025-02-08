package frc.lib.EasySpark;

import frc.lib.configs.Sparkmax.SparkControllerInfo;

public class EasySparkControllerInfo {

    public SparkControllerInfo scInfo;
    public EasySparkConstants constants;
    
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
