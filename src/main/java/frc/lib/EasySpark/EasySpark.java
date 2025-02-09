package frc.lib.EasySpark;

import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkClosedLoopController;

import edu.wpi.first.wpilibj.DutyCycleEncoder;

import com.ctre.phoenix6.hardware.CANcoder;

import frc.lib.Items.SparkMax.SparkController;
import frc.lib.configs.Sparkmax.SparkControllerInfo;

public class EasySpark {

    public int id;
    public String name;
    public SparkMax spark;
    public SparkControllerInfo scInfo;
    public EasySparkConstants constants;

    public SparkController controller;
    public RelativeEncoder encoder;
    public SparkClosedLoopController PIDcontroller;

    public CANcoder CANcoder;
    public DutyCycleEncoder DutyCycleEncoder;
    
    /**
     * This creates an instance of EasySpark. It contains the SparkMax configuration mess
     * all in one neat-and-tidy package for immediate and easy use.
     * 
     * @param id Idk why you would need this but the ID is here.
     * @param name Same as above.
     * @param scInfo Gives the SparkControllerInfo of the SparkMax.
     * @param constants Gives the constants of the motor/subsystem.
     * 
     * @param controller Returns the SparkController object of the SparkMax.
     * @param spark Is a SparkMax Object.
     * @param encoder Returns the RelativeEncoder of said SparkMax.
     * @param PIDcontroller Returns the PIDController of the SparkMax.
     * 
     * @param CANcoder If there is a CANcoder, this will return the CANcoder.
     * @param DutyCycleEncoder If there is an Absolute Encoder, this will return it.
     * 
     * @see EasySparkConfig
     * @see EasySparkConstants
     * @see EasySparkControllerInfo
     */
    public EasySpark(EasySparkConfig config) {
        this.id = config.id();
        this.name = config.name();
        this.scInfo = config.escInfo().scInfo;
        this.constants = config.escInfo().constants;

        SparkController controller = new SparkController(this.id, this.scInfo);

        this.controller = controller;
        this.spark = controller.spark;
        this.encoder = controller.sparkEncode;
        this.PIDcontroller = controller.sparkControl;

        if (config.CANcoder() != null) {
            this.CANcoder = config.CANcoder();
        }
        if (config.DutyCycleEncoder() != null) {
            this.DutyCycleEncoder = config.DutyCycleEncoder();
        }
    }

    public double getPos() {
        if (this.DutyCycleEncoder != null) {
            return this.DutyCycleEncoder.get();
        } else if (this.CANcoder != null) {
            return this.CANcoder.getAbsolutePosition().getValueAsDouble();
        } else {
            return this.encoder.getPosition();
        }
    }

    public double getVel() {
        if (this.CANcoder != null) {
            return this.CANcoder.getVelocity().getValueAsDouble();
        } else {
            return this.encoder.getVelocity();
        }
    }


    





    public void setDutyCycle(double percent) {
        percent = percent/100;
        PIDcontroller.setReference(percent, SparkBase.ControlType.kDutyCycle);
    }

    public void setVoltage(double voltage) {
        if(voltage < -constants.maxVoltage){
            voltage = -constants.maxVoltage;
        } else if (voltage > constants.maxVoltage){
            voltage = constants.maxVoltage;
        }
        PIDcontroller.setReference(voltage, SparkBase.ControlType.kVoltage);
    }

    public void setPosition(double position) {
        PIDcontroller.setReference(position, SparkMax.ControlType.kPosition);
    }
    
} 