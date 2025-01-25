package frc.lib.EasySpark;

import com.revrobotics.spark.SparkBase;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkClosedLoopController;
import com.ctre.phoenix6.hardware.CANcoder;

import frc.lib.Items.SparkMax.SparkController;
import frc.lib.configs.Sparkmax.SparkControllerInfo;

public class EasySpark {

    public int id;
    public String name;
    public SparkControllerInfo scInfo;
    public EasySparkConstants constants;

    public SparkController controller;
    public RelativeEncoder encoder;
    public SparkClosedLoopController PIDcontroller;

    public CANcoder CANcoder;
    
    public EasySpark(EasySparkConfig config) {
        this.id = config.id();
        this.name = config.name();
        this.scInfo = config.scInfo();
        this.constants = config.constants();

        SparkController controller = new SparkController(config.id(), config.scInfo());

        this.controller = controller;
        this.encoder = controller.sparkEncode;
        this.PIDcontroller = controller.sparkControl;

        if (config.CANcoder() != null) {
            this.CANcoder = config.CANcoder();
        }
    }

    public void setDutyCycle(int id, double percent) {
        percent = percent/100;
        PIDcontroller.setReference(percent, SparkBase.ControlType.kDutyCycle);
    }

    public void setVoltage(int id, double voltage) {
        if(voltage < -constants.maxVoltage){
            voltage = -constants.maxVoltage;
        } else if (voltage > constants.maxVoltage){
            voltage = constants.maxVoltage;
        }
        PIDcontroller.setReference(voltage, SparkBase.ControlType.kVoltage);
    }
    
} 