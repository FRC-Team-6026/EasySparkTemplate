package frc.lib.EasySpark;

import frc.lib.util.CANSparkMaxUtil.Usage;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import java.util.Map;
import java.util.HashMap;

/**
 * Creates an object containing all the default spark constants.
 * Using {@code this.customsConstants} will let you access a HashMap.
 *
 * <table>
 *   <tr><th>Variable</th><th>Type</th><th>Default Value</th></tr>
 *   <tr><td>usage</td><td>Usage</td><td>kAll</td></tr>
 *   <tr><td>currentLim</td><td>int</td><td>40</td></tr>
 *   <tr><td>maxVoltage</td><td>double</td><td>1.0</td></tr>
 *   <tr><td>voltageComp</td><td>double</td><td>12.0</td></tr>
 *   <tr><td>gearReduction</td><td>double</td><td>1.0</td></tr>
 *   <tr><td>invert</td><td>boolean</td><td>false</td></tr>
 *   <tr><td>idleMode</td><td>IdleMode</td><td>kCoast</td></tr>
 *   <tr><td>minPosition</td><td>double</td><td>0</td></tr>
 *   <tr><td>maxPosition</td><td>double</td><td>0</td></tr>
 *   <tr><td>posConversion</td><td>double</td><td>1.0</td></tr>
 *   <tr><td>velConversion</td><td>double</td><td>1.0</td></tr>
 *   <tr><td>PID</td><td>double[]</td><td>{0.0, 0.0, 0.0, 0.0}</td></tr>
 * </table>
 * 
 * @see EasySpark
 * @see EasySparkConfig
 * @see EasySparkControllerInfo
 */
public class EasySparkConstants {

    public Usage usage;
    public int currentLim;
    public double maxVoltage;
    public double gearReduction;
    public boolean invert;
    public IdleMode idleMode;
    public double minPosition;
    public double maxPosition;
    public double posConversion;
    public double velConversion;
    public double[] PID;
    public double voltageComp;

    public Map<String, Object> customConstants;

    /**
     * Creates an object containing all the default spark constants.
     * Using {@code this.customsConstants} will let you access a HashMap.
     *
     * <table>
     *   <tr><th>Variable</th><th>Type</th><th>Default Value</th></tr>
     *   <tr><td>usage</td><td>Usage</td><td>kAll</td></tr>
     *   <tr><td>currentLim</td><td>int</td><td>40</td></tr>
     *   <tr><td>maxVoltage</td><td>double</td><td>1.0</td></tr>
     *   <tr><td>voltageComp</td><td>double</td><td>12.0</td></tr>
     *   <tr><td>gearReduction</td><td>double</td><td>1.0</td></tr>
     *   <tr><td>invert</td><td>boolean</td><td>false</td></tr>
     *   <tr><td>idleMode</td><td>IdleMode</td><td>kCoast</td></tr>
     *   <tr><td>minPosition</td><td>double</td><td>0</td></tr>
     *   <tr><td>maxPosition</td><td>double</td><td>0</td></tr>
     *   <tr><td>posConversion</td><td>double</td><td>1.0</td></tr>
     *   <tr><td>velConversion</td><td>double</td><td>1.0</td></tr>
     *   <tr><td>PID</td><td>double[]</td><td>{0.0, 0.0, 0.0, 0.0}</td></tr>
     * </table>
     * 
     * @see EasySpark
     * @see EasySparkConfig
     * @see EasySparkControllerInfo
     */
    public EasySparkConstants() {
        this.usage = Usage.kAll;

        this.currentLim = 40;
        this.maxVoltage = 1.0;
        this.voltageComp = 12.0;

        this.gearReduction = 1.0;

        this.invert = false;
        this.idleMode = IdleMode.kCoast;

        this.minPosition = 0;
        this.maxPosition = 0;

        this.posConversion = 1.0;
        this.velConversion = 1.0;

        this.PID = new double[] {0.0, 0.0, 0.0, 0.0};

        this.customConstants = new HashMap<String, Object>();
    }

}
