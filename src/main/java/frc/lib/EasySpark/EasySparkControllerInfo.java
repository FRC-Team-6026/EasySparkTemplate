package frc.lib.EasySpark;

import frc.lib.configs.Sparkmax.SparkControllerInfo;

public class EasySparkControllerInfo {

    public SparkControllerInfo scInfo;
    
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
    }

}
