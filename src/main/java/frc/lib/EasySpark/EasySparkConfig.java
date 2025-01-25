package frc.lib.EasySpark;

import frc.lib.configs.Sparkmax.SparkControllerInfo;
import com.ctre.phoenix6.hardware.CANcoder;

/* TODO - Feedback notes.
 * A lot of things you're doing in this code (and even some things we're doing in the current code)
 * remind me of C++ "structs", which are just collections of variables. I didn't think java had
 * structs,  but something called "records" were added to java a while back. I think we could use
 * these to simplify our code a bit.
 * From what I've read records are just special classes that autogenerate some code, like a 
 * constructor and getter methods.
 */

public record EasySparkConfig(int id, String name, SparkControllerInfo scInfo, EasySparkConstants constants, CANcoder CANcoder) {

    // public int id;
    // public String name;
    // public SparkControllerInfo scInfo;
    // public ConstantsBase constants;

    public EasySparkConfig(int id, String name, SparkControllerInfo scInfo, EasySparkConstants constants) {
        this(id, name, scInfo, constants, null);
    }

    // public EasyConfig(int id, String name, SparkControllerInfo scInfo, ConstantsBase constants) {
    //     this.id = id;
    //     this.name = name;
    //     this.scInfo = scInfo;
    //     this.constants = constants;
    // }

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
