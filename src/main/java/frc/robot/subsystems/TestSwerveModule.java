package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.ClosedLoopSlot;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.units.Measure;
import edu.wpi.first.units.measure.Voltage;
import frc.robot.Constants;
import frc.lib.math.OnboardModuleState;
import com.easyspark.frc.EasySpark;
import frc.lib.configs.Sparkmax.TestSwerveModuleInfo;

public class TestSwerveModule {
    public int moduleNumber;
    private Rotation2d lastAngle;
    private Rotation2d angleOffset;

    private EasySpark drive;
    private EasySpark angle;

    public final SwerveModuleState xState;

    private final SimpleMotorFeedforward feedforward =
        new SimpleMotorFeedforward(
            Constants.Swerve.driveMotorsSVA[0], Constants.Swerve.driveMotorsSVA[1], Constants.Swerve.driveMotorsSVA[2]);

    public TestSwerveModule(TestSwerveModuleInfo Info) {
        this.moduleNumber = Info.moduleNumber;
        this.angleOffset = Rotation2d.fromDegrees(Info.angleOffset);

        this.drive = Info.drive;
        this.angle = Info.angle;

        xState = new SwerveModuleState(0, Rotation2d.fromDegrees(Info.xPos));

        lastAngle = getState().angle;
    }



    void resetToAbsolute() {
        double absolutePosition = getCanCoder().getDegrees() - angleOffset.getDegrees();
        angle.encoder.setPosition(absolutePosition);
    }


    
    public void setDesiredState(SwerveModuleState desiredState, boolean isOpenLoop) {
        desiredState = OnboardModuleState.optimize(desiredState, getState().angle);
        setAngle(desiredState);
        setSpeed(desiredState, isOpenLoop);
    }

    private void setSpeed(SwerveModuleState desiredState, boolean isOpenLoop) {
        if (isOpenLoop) {
            double percentOutput = desiredState.speedMetersPerSecond / Constants.Swerve.maxSpeed;
            drive.spark.set(percentOutput);
        } else {
            drive.PIDcontroller.setReference(
                desiredState.speedMetersPerSecond,
                SparkMax.ControlType.kVelocity,
                ClosedLoopSlot.kSlot0,
                feedforward.calculate(desiredState.speedMetersPerSecond));
        }
    }

    private void setAngle(SwerveModuleState desiredState) {
        // Prevent rotating module if speed is less then 1%. Prevents jittering.
        Rotation2d currentAngle =
            (Math.abs(desiredState.speedMetersPerSecond) <= (Constants.Swerve.maxSpeed * 0.01))? 
            lastAngle : desiredState.angle;
    
        angle.setPosition(currentAngle.getDegrees());
        lastAngle = currentAngle;
    }



    private Rotation2d getAngle() {
        return Rotation2d.fromDegrees(angle.encoder.getPosition());
    }
    
    public Rotation2d getCanCoder() {
        return Rotation2d.fromRotations(angle.getPos());
    }
    
    public SwerveModuleState getState() {
        return new SwerveModuleState(drive.getVel(), getAngle());
    }
    
    public SwerveModulePosition getPostion() {
        return new SwerveModulePosition(drive.getPos(), getAngle());
    }

}