package frc.robot;

import frc.lib.configs.Sparkmax.SparkControllerInfo;
import frc.robot.Constants.ConstantsBase;

/* TODO - Feedback notes.
 * A lot of things you're doing in this code (and even some things we're doing in the current code)
 * remind me of C++ "structs", which are just collections of variables. I didn't think java had
 * structs,  but something called "records" were added to java a while back. I think we could use
 * these to simplify our code a bit.
 * From what I've read records are just special classes that autogenerate some code, like a 
 * constructor and getter methods.
 */

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
