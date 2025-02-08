package frc.lib.EasySpark;

import frc.lib.configs.Sparkmax.SparkControllerInfo;
import com.ctre.phoenix6.hardware.CANcoder;
import edu.wpi.first.wpilibj.DutyCycleEncoder;

/**
 * This is a config object used to create an EasySpark instance.
 * 
 * @param id The ID used by the SparkMax on the CAN bus.
 * @param name The name given for the motor/subsystem.
 * @param scInfo This is the info used to create a SparkMax instance.
 * @param constants The constants object created for the subsystem. See {@linkplain EasySparkControllerInfo} for more info.
 * @param CANcoder A CANcoder object. Set to null by default.
 * @param DutyCycleEncoder An Absolute Encoder. Set to null by defualt.
 * 
 * @see EasySpark
 * @see EasySparkConstants
 */
public record EasySparkConfig(int id, String name, SparkControllerInfo scInfo, CANcoder CANcoder, DutyCycleEncoder DutyCycleEncoder) {

    /**
     * This is a config object used to create an EasySpark instance.
     * 
     * @param id The ID used by the SparkMax on the CAN bus.
     * @param name The name given for the motor/subsystem.
     * @param scInfo This is the info used to create a SparkMax instance.
     * @param constants The constants object created for the subsystem. See {@linkplain EasySparkControllerInfo} for more info.
     * @param CANcoder A CANcoder object. Set to null by default.
     * @param DutyCycleEncoder An Absolute Encoder. Set to null by defualt.
     * 
     * @see EasySpark
     * @see EasySparkConstants
     */
    public EasySparkConfig(int id, String name, SparkControllerInfo scInfo) {
        this(id, name, scInfo, null, null);
    }

    /**
     * This is a config object used to create an EasySpark instance.
     * 
     * @param id The ID used by the SparkMax on the CAN bus.
     * @param name The name given for the motor/subsystem.
     * @param scInfo This is the info used to create a SparkMax instance.
     * @param constants The constants object created for the subsystem. See {@linkplain EasySparkControllerInfo} for more info.
     * @param CANcoder A CANcoder object. Set to null by default.
     * @param DutyCycleEncoder An Absolute Encoder. Set to null by defualt.
     * 
     * @see EasySpark
     * @see EasySparkConstants
     */
    public EasySparkConfig(int id, String name, SparkControllerInfo scInfo, CANcoder CANcoder) {
        this(id, name, scInfo, CANcoder, null);
    }

    /**
     * This is a config object used to create an EasySpark instance.
     * 
     * @param id The ID used by the SparkMax on the CAN bus.
     * @param name The name given for the motor/subsystem.
     * @param scInfo This is the info used to create a SparkMax instance.
     * @param constants The constants object created for the subsystem. See {@linkplain EasySparkControllerInfo} for more info.
     * @param CANcoder A CANcoder object. Set to null by default.
     * @param DutyCycleEncoder An Absolute Encoder. Set to null by defualt.
     * 
     * @see EasySpark
     * @see EasySparkConstants
     */
    public EasySparkConfig(int id, String name, SparkControllerInfo scInfo, DutyCycleEncoder DutyCycleEncoder) {
        this(id, name, scInfo, null, DutyCycleEncoder);
    }

}
