package frc.robot;

import frc.lib.configs.Sparkmax.SparkControllerInfo;

public class SubsystemConfig {

    public int id;
    public String name;
    public SparkControllerInfo scInfo;
    public Constants.ConstantsBase constants;

    public void setID(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSCInfo(SparkControllerInfo scInfo) {
        this.scInfo = scInfo;
    }

    public void setConstants(Constants.ConstantsBase constants) {
        this.constants = constants;
    }

}
