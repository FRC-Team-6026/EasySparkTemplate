package frc.robot.subsystems;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import com.revrobotics.spark.SparkBase;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkClosedLoopController;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.Items.SparkMax.SparkController;
import frc.lib.configs.Sparkmax.SparkControllerInfo;
import frc.robot.Constants.ConstantsBase;
import frc.robot.SubsystemConfig;

public class SubsystemCore extends SubsystemBase {

    /* TODO - Feedback notes.
     * I agree that the hashmaps feel like too much overhead here.
     * I think the way to implement this might end up being, that we make instances
     * of SubsystemCore to define each subsystem. If we do that, each variable can
     * just be a variable.
     * So our algae intake might be something like
     *   algaeIntake = new Subsystem(configs, [sparkmax arraylist], constants);
     */
    public List<Integer> idList = new ArrayList<>();
    public Map<Integer, String> nameList = new HashMap<>();
    public Map<Integer, SparkControllerInfo> scInfoList = new HashMap<>();
    public Map<Integer, ConstantsBase> constantsList = new HashMap<>();

    public Map<Integer, SparkController> controllerList = new HashMap<>();
    public Map<Integer, RelativeEncoder> encoderList = new HashMap<>();
    public Map<Integer, SparkClosedLoopController> PIDcontrollerList = new HashMap<>();
    
    public SubsystemCore(SubsystemConfig[] configs) {
        for(SubsystemConfig config : configs) {
            this.idList.add(config.id);
            this.nameList.put(config.id, config.name);
            this.scInfoList.put(config.id, config.scInfo);
            this.constantsList.put(config.id, config.constants);
    
            SparkController controller = new SparkController(config.id, config.scInfo);

            this.controllerList.put(config.id, controller);
            this.encoderList.put(config.id, controller.sparkEncode);
            this.PIDcontrollerList.put(config.id, controller.sparkControl);
        }
    }

    public void setDutyCycle(int id, double percent) {
        percent = percent/100;
        PIDcontrollerList.get(id).setReference(percent, SparkBase.ControlType.kDutyCycle);
    }

    public void setVoltage(int id, double voltage) {
        if(voltage < -constantsList.get(id).maxVoltage){
            voltage = -constantsList.get(id).maxVoltage;
        } else if (voltage > constantsList.get(id).maxVoltage){
            voltage = constantsList.get(id).maxVoltage;
        }
        PIDcontrollerList.get(id).setReference(voltage, SparkBase.ControlType.kVoltage);
    }

    
    
} 