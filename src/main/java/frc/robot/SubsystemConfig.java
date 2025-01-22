package frc.robot;

import frc.lib.configs.Sparkmax.SparkControllerInfo;
import frc.robot.Constants.ConstantsBase;

public class SubsystemConfig {

    public int id;
    public String name;
    public SparkControllerInfo scInfo;
    public ConstantsBase constants;

    public SubsystemConfig(int id, String name, SparkControllerInfo scInfo, ConstantsBase constants) {
        this.id = id;
        this.name = name;
        this.scInfo = scInfo;
        this.constants = constants;
    }

    // private SubsystemConfig setID(int id) {
    //     this.id = id;
    //     return this;
    // }

    // private SubsystemConfig setName(String name) {
    //     this.name = name;
    //     return this;
    // }

    // private SubsystemConfig setSCInfo(SparkControllerInfo scInfo) {
    //     this.scInfo = scInfo;
    //     return this;
    // }

}
