package frc.lib.EasySpark;

import frc.lib.util.CANSparkMaxUtil.Usage;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

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

    public constant[] customConstants;

    public EasySparkConstants() {
        this.usage = Usage.kAll;

        this.currentLim = 40;
        this.maxVoltage = 1;

        this.gearReduction = 1.0;

        this.invert = false;
        this.idleMode = IdleMode.kCoast;

        this.minPosition = 0;
        this.maxPosition = 0;

        this.posConversion = 1.0;
        this.velConversion = 1.0;

        this.PID = new double[] {0.0, 0.0, 0.0, 0.0};

        this.voltageComp = 12.0;

        this.customConstants = new constant[] {};
    }

    private class constant {
        private String name;
        private Object value;
    }

    public void set(String name, Object value) {
        for (constant constant : this.customConstants) {
            if (constant.name == name) {
                constant.value = value;
                break;
            }
        }
    }

    public Object get(String name) {
        for (constant constant : this.customConstants) {
            if (constant.name == name) {
                return constant.value;
            }
        }

        return null;
    }

}
