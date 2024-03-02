// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.commands.PathPlannerAuto;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.ShooterWheels;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Pivot;
import frc.robot.commands.DefaultCommands.IntakeDefault;
import frc.robot.commands.DefaultCommands.ShooterDefault;
import frc.robot.commands.DefaultCommands.FeederDefault;
import frc.robot.commands.DefaultCommands.PivotDefault;
import frc.robot.commands.DefaultCommands.TeleopSwerve;
import frc.robot.subsystems.Swerve;

public class RobotContainer {
  /* Controllers */
  private final XboxController driver = new XboxController(0);
  private final XboxController operator = new XboxController(1);

  /* Drive Controls */
  private final int translationAxis = XboxController.Axis.kLeftY.value;
  private final int strafeAxis = XboxController.Axis.kLeftX.value;
  private final int rotationAxis = XboxController.Axis.kRightX.value;

  /* Driver Buttons */
  private final JoystickButton zeroGyro =
  new JoystickButton(driver, XboxController.Button.kBack.value);
  private final JoystickButton robotCentricBumper =
  new JoystickButton(driver, XboxController.Button.kStart.value);
  private final JoystickButton resetOdometry = 
  new JoystickButton(driver, XboxController.Button.kA.value);
  private final JoystickButton xSwerve = 
  new JoystickButton(driver, XboxController.Button.kLeftBumper.value);
  private boolean robotCentric = false;

  /* Operator Buttons */
  private final JoystickButton startIntake =
  new JoystickButton(operator, XboxController.Button.kLeftBumper.value);
  private final JoystickButton shootNote =
  new JoystickButton(operator, XboxController.Button.kRightBumper.value);
  private final JoystickButton stopButton =
  new JoystickButton(operator, XboxController.Button.kY.value);

  private final JoystickButton angleButton1 =
  new JoystickButton(operator, XboxController.Button.kX.value);
  private final JoystickButton angleButton2 =
  new JoystickButton(operator, XboxController.Button.kB.value);
  private final JoystickButton angleButton3 =
  new JoystickButton(operator, XboxController.Button.kA.value);

  /* Subsystems */
  private DigitalInput leftSwitch;
  private DigitalInput rightSwitch;
  private final Swerve swerve = new Swerve();
  private final Intake intake = new Intake(); 
  private final ShooterWheels shooter = new ShooterWheels();
  private final Feeder feeder = new Feeder();
  private final Pivot pivot = new Pivot();


  /* Robot Variables */
  
  private final SendableChooser<Command> autoChooser;

  public enum ShooterState{
    Off,
    Intake,
    ReadyToShoot,
    Shoot
  }
  public ShooterState state;

  public double targetAngle = 1;
  
  public RobotContainer() {
    // Initialize Autonomous Commands
    NamedCommands.registerCommand("AutoReadyToShoot", new InstantCommand(() -> changeShooterState(ShooterState.ReadyToShoot)));
    NamedCommands.registerCommand("AutoShoot", new InstantCommand(() -> changeShooterState(ShooterState.Shoot)));

    leftSwitch = new DigitalInput(0);
    rightSwitch = new DigitalInput(1);

    Trigger switchesPressed = new Trigger(leftSwitch::get).or(rightSwitch::get);

    switchesPressed.onTrue(new InstantCommand(() -> {
      changeShooterState(ShooterState.Off);
      SmartDashboard.putBoolean("leftSwitch", leftSwitch.get());
      SmartDashboard.putBoolean("rightSwitch", rightSwitch.get());
    }));
    switchesPressed.onFalse(new WaitCommand(0.5).andThen(new InstantCommand(() -> {
      changeShooterState(ShooterState.Off);
      SmartDashboard.putBoolean("leftSwitch", leftSwitch.get());
      SmartDashboard.putBoolean("rightSwitch", rightSwitch.get());
    })));

    swerve.setDefaultCommand(
      new TeleopSwerve(
        swerve,
        () -> -driver.getRawAxis(translationAxis),
        () -> -driver.getRawAxis(strafeAxis),
        () -> -Math.pow(driver.getRawAxis(rotationAxis),3),
        () -> robotCentric));

    intake.setDefaultCommand(
      /* Old, smart code
      new IntakeDefault(
        intake, 
        () -> (state == ShooterState.Intake),
        () -> Constants.Swerve.maxSpeed * Math.sqrt((driver.getRawAxis(translationAxis) * driver.getRawAxis(translationAxis)) + (driver.getRawAxis(strafeAxis) * driver.getRawAxis(strafeAxis)))
      )
      */
      //New, dumb code that works
      new IntakeDefault(
        intake, 
        () -> (state == ShooterState.Intake),
        () -> Constants.Intake.minTanVel
      )
    );

    shooter.setDefaultCommand(
      new ShooterDefault(
        shooter,
        () -> (state == ShooterState.ReadyToShoot || state == ShooterState.Shoot),
        () -> Constants.Electical.shooterHardcodedVoltage
      )
    );

    feeder.setDefaultCommand(
      new FeederDefault(
        feeder,
        () -> (state == ShooterState.Intake || state == ShooterState.Shoot),
        () -> Constants.Electical.feederHarcodedVoltage
      )
    );

    pivot.setDefaultCommand(
      new PivotDefault(
        pivot,
        () -> angleButton1.getAsBoolean(),
        () -> angleButton2.getAsBoolean(),
        () -> angleButton3.getAsBoolean(),
        () -> targetAngle
      )
    );

    configureBindings();

    autoChooser = AutoBuilder.buildAutoChooser(); // Default auto will be `Commands.none()`
    SmartDashboard.putData("Auto Mode", autoChooser);
  }

  private void configureBindings() {
    SmartDashboard.putData("Blue Pos1, Standard", new PathPlannerAuto("Blue Pos1, Standard"));
    /* Driver Buttons */
    zeroGyro.onTrue(new InstantCommand(() -> swerve.zeroGyro()));
    robotCentricBumper.onTrue(new InstantCommand(() -> {
      robotCentric = !robotCentric;
      SmartDashboard.putBoolean("Is Robot Centric", robotCentric);
    }));

    resetOdometry.onTrue(new InstantCommand(() -> swerve.resetToAbsolute()));
    xSwerve.onTrue(new InstantCommand(() -> swerve.xPattern()));

    /* Operator Buttons */
    startIntake.onTrue(new InstantCommand(() -> changeShooterState(ShooterState.Intake)));
    shootNote.onTrue(new InstantCommand(() -> changeShooterState(ShooterState.ReadyToShoot)).andThen(
      new WaitCommand(0.2).andThen(
      new InstantCommand(() -> changeShooterState(ShooterState.Shoot)))));
    stopButton.onTrue(new InstantCommand(() -> changeShooterState(ShooterState.Off)));

    //angleButton1.onTrue(new InstantCommand(() -> setAngle(0)));
    angleButton2.onTrue(new InstantCommand(() -> setAngle(-5)));
    angleButton3.onTrue(new InstantCommand(() -> setAngle(5)));
  }

  public Command getAutonomousCommand() {
    return autoChooser.getSelected();
  }

  public void teleopInit(){
    swerve.xPatternFalse();
    swerve.resetToAbsolute();
  }

  public void autoInit(){
    swerve.resetToAbsolute();
  }

  public void changeShooterState(ShooterState changeto) {
    state = changeto;
    SmartDashboard.putString("ShooterState", state.name());
  }

  public void setAngle(double angle){
    targetAngle = angle;
  }
}
