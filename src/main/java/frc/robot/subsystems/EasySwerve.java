package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
//import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.config.RobotConfig;
import com.pathplanner.lib.util.PathPlannerLogging;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.units.Measure;
import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;
import frc.lib.EasySpark.EasySpark;
import frc.lib.EasySpark.EasySparkConfig;
import frc.lib.EasySpark.EasySparkConstants;
import frc.lib.configs.Sparkmax.SparkControllerInfo;
import frc.lib.configs.Sparkmax.EasySwerveModuleInfo;
import frc.lib.util.CANSparkMaxUtil.Usage;
import frc.robot.Constants;

public class EasySwerve extends SubsystemBase {
    private final AHRS gyro;
  
    private SwerveDriveOdometry swerveOdometry;
    private EasySwerveModule[] mSwerveMods;
  
    private boolean isX = false;
  
    private static boolean negativePitch = false;
    
    private Field2d field = new Field2d();
    
    public EasySwerve() {
        gyro = new AHRS();
        gyro.reset();
        zeroGyro();

        for(int i = 0; i <= 3; i++){
            this.mSwerveMods[i] = new EasySwerveModule(new EasySwerveModuleInfo(i));
        }

        swerveOdometry = new SwerveDriveOdometry(Constants.Swerve.swerveKinematics, getAngle(), getPositions());
    }



    @Override
    public void periodic() {
        swerveOdometry.update(getAngle(), getPositions());
        report();
    }


    public void drive(Translation2d translation, double rotation, boolean fieldRelative, boolean isOpenLoop) {
        SwerveModuleState[] swerveModuleStates =
            Constants.Swerve.swerveKinematics.toSwerveModuleStates(
                fieldRelative
                    ? ChassisSpeeds.fromFieldRelativeSpeeds(
                        translation.getX(), translation.getY(), rotation, getAngle())
                    : new ChassisSpeeds(translation.getX(), translation.getY(), rotation));

        SwerveDriveKinematics.desaturateWheelSpeeds(swerveModuleStates, Constants.Swerve.maxSpeed);
            for (EasySwerveModule mod : mSwerveMods) {
            if(isX){
                mod.setDesiredState(mod.xState, isOpenLoop);
            } else {
                mod.setDesiredState(swerveModuleStates[mod.moduleNumber], isOpenLoop);
            }
            var modState = swerveModuleStates[mod.moduleNumber];
            SmartDashboard.putNumber("Mod " + mod.moduleNumber + " desired angle: ", modState.angle.getDegrees());
            SmartDashboard.putNumber("Mod " + mod.moduleNumber + " desired velocity: ", modState.speedMetersPerSecond);
        }
    }



    public void setModuleStates(SwerveModuleState[] desiredStates) {
        SwerveDriveKinematics.desaturateWheelSpeeds(desiredStates, Constants.Swerve.maxSpeed);
    
        for (EasySwerveModule mod : mSwerveMods) {
            mod.setDesiredState(desiredStates[mod.moduleNumber], false);
        }
    }
    
    public void driveRobotRelative(ChassisSpeeds robotRelativeSpeeds) {
        ChassisSpeeds targetSpeeds = ChassisSpeeds.discretize(robotRelativeSpeeds, 0.02);
    
        SwerveModuleState[] targetStates = Constants.Swerve.swerveKinematics.toSwerveModuleStates(targetSpeeds);
        setModuleStates(targetStates);
    }

    public void invertGyro(){
        gyro.setAngleAdjustment(180);
        negativePitch = true;
    }

    public void report(){
        for (EasySwerveModule mod : mSwerveMods) {
            SmartDashboard.putNumber(
                "Mod " + mod.moduleNumber + " Cancoder", mod.getCanCoder().getDegrees());
            SmartDashboard.putNumber(
                "Mod " + mod.moduleNumber + " Integrated", mod.getState().angle.getDegrees());
            SmartDashboard.putNumber(
                "Mod " + mod.moduleNumber + " Velocity", mod.getState().speedMetersPerSecond);      
          }  
    }



    public void xPattern() {
        isX = !isX;
    }

    public void xPatternTrue() {
        isX = true;
    }

    public void xPatternFalse() {
        isX = false;
    }



    public Pose2d getPose() {
        return swerveOdometry.getPoseMeters();
    }

    public SwerveModuleState[] getStates() {
        SwerveModuleState[] states = new SwerveModuleState[4];
        for (EasySwerveModule mod : mSwerveMods) {
          states[mod.moduleNumber] = mod.getState();
        }
        return states;
    }
    
    public SwerveModulePosition[] getPositions() {
        SwerveModulePosition[] positions = new SwerveModulePosition[4];
        for (EasySwerveModule mod : mSwerveMods) {
            positions[mod.moduleNumber] = mod.getPostion();
        }
        return positions;
    }
    
    public ChassisSpeeds getSpeeds(){
        return Constants.Swerve.swerveKinematics.toChassisSpeeds(getStates());
    }

    public Rotation2d getAngle() {
        return (Constants.Swerve.invertGyro)
            ? Rotation2d.fromDegrees(360 - gyro.getAngle())
            : Rotation2d.fromDegrees(gyro.getAngle());
    }

    public float getPitch(){
        if (negativePitch){
          return -gyro.getPitch();
        } else {
          return gyro.getPitch();
        }
    }

    public AHRS getGyro(){
        return gyro;
    }
    


    public void resetToAbsolute() {
        for (EasySwerveModule mod : mSwerveMods) {
            mod.resetToAbsolute();
        }
    }

    public void resetOdometry(Pose2d pose) {
        swerveOdometry.resetPosition(getAngle(), getPositions(), pose);
    }

    public void zeroGyro() {
        gyro.zeroYaw();
        gyro.setAngleAdjustment(0);
        negativePitch = false;
    }

}