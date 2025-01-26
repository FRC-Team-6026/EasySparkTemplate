package frc.lib.EasySpark;

import frc.lib.configs.Sparkmax.SparkControllerInfo;
import com.ctre.phoenix6.hardware.CANcoder;

/**
 * This is a config object used to create an EasySpark instance.
 * 
 * @param id The ID used by the SparkMax on the CAN bus.
 * @param name The name given for the motor/subsystem.
 * @param scInfo This is the info used to create a SparkMax instance.
 * @param constants The constants object created for the subsystem. See {@linkplain EasySparkControllerInfo} for more info.
 * @param CANcoder A CANcoder object. Set to null by default.
 * 
 * @see EasySpark
 * @see EasySparkConstants
 */
public record EasySparkConfig(int id, String name, SparkControllerInfo scInfo, EasySparkConstants constants, CANcoder CANcoder) {

    /**
     * This is a config object used to create an EasySpark instance.
     * 
     * @param id The ID used by the SparkMax on the CAN bus.
     * @param name The name given for the motor/subsystem.
     * @param scInfo This is the info used to create a SparkMax instance.
     * @param constants The constants object created for the subsystem. See {@linkplain EasySparkControllerInfo} for more info.
     * @param CANcoder A CANcoder object. Set to null by default.
     * 
     * @see EasySpark
     * @see EasySparkConstants
     */
    public EasySparkConfig(int id, String name, SparkControllerInfo scInfo, EasySparkConstants constants) {
        this(id, name, scInfo, constants, null);
    }

}
