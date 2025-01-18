package frc.robot.subsystems;

import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.SparkBase;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkClosedLoopController;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.Items.SparkMax.SparkController;
import frc.lib.configs.Sparkmax.SparkControllerInfo;

import frc.robot.Constants;
import frc.robot.SubsystemConfig;

public class SubsystemCore extends SubsystemBase {

    private int id;
    private String name;
    private SparkControllerInfo scInfo;
    private Constants.ConstantsBase constants;

    private SparkController controller;
    private RelativeEncoder encoder;
    private SparkClosedLoopController PIDcontroller;
    
    public SubsystemCore(SubsystemConfig config) {
        this.id = config.id;
        this.name = config.name;
        this.scInfo = config.scInfo;
        this.constants = config.constants;

        this.controller = new SparkController(this.id, this.scInfo);
        this.encoder = controller.sparkEncode;
        this.PIDcontroller = controller.sparkControl;
    }

    public void setDutyCylce(double percent) {
        percent = percent/100;
        PIDcontroller.setReference(percent, SparkBase.ControlType.kDutyCycle);
    }

    
} 