package frc.robot;

import frc.lib.configs.Sparkmax.SparkControllerInfo;

public class SubsystemConfig {

    public int id;
    public String name;
    public SparkControllerInfo scInfo;

    public SubsystemConfig setID(int id) {
        this.id = id;
        return this;
    }

    public SubsystemConfig setName(String name) {
        this.name = name;
        return this;
    }

    public SubsystemConfig setSCInfo(SparkControllerInfo scInfo) {
        this.scInfo = scInfo;
        return this;
    }

}
