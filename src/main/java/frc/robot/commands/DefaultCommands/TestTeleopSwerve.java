package frc.robot.commands.DefaultCommands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.TestSwerve;
import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public class TestTeleopSwerve extends Command {
    private TestSwerve s_Swerve;
    private DoubleSupplier translationSup;
    private DoubleSupplier strafeSup;
    private DoubleSupplier rotationSup;
    private BooleanSupplier robotCentricSup;

    private SlewRateLimiter translationLimiter = new SlewRateLimiter(2.0);
    private SlewRateLimiter strafeLimiter = new SlewRateLimiter(2.0);
    private SlewRateLimiter rotationLimiter = new SlewRateLimiter(2.0);

    public TestTeleopSwerve(
        TestSwerve s_Swerve,
        DoubleSupplier translationSup,
        DoubleSupplier strafeSup,
        DoubleSupplier rotationSup,
        BooleanSupplier robotCentricSup
    ) {
        this.s_Swerve = s_Swerve;
        addRequirements(s_Swerve);

        this.translationSup = translationSup;
        this.strafeSup = strafeSup;
        this.rotationSup = rotationSup;
        this.robotCentricSup = robotCentricSup;
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        /* Get Values, Deadband*/
        double translationVal = MathUtil.applyDeadband(translationSup.getAsDouble(), Constants.Swerve.stickDeadband);
        double strafeVal = MathUtil.applyDeadband(strafeSup.getAsDouble(), Constants.Swerve.stickDeadband);
        double rotationVal = MathUtil.applyDeadband(rotationSup.getAsDouble(), Constants.Swerve.stickDeadband);

        //cubes inputs to give finer control at low end
        translationVal = translationVal * translationVal * translationVal;
        strafeVal = strafeVal * strafeVal * strafeVal;
        rotationVal = rotationVal * rotationVal * rotationVal;

        //limit change per input to avoid slamming the motors
        translationVal = translationLimiter.calculate(translationVal);
        strafeVal = strafeLimiter.calculate(strafeVal);
        rotationVal = rotationLimiter.calculate(rotationVal);

        /* Drive */
        s_Swerve.drive(
            new Translation2d(translationVal, strafeVal).times(Constants.Swerve.maxSpeed),
            rotationVal * Constants.Swerve.maxAngularVelocity,
            !robotCentricSup.getAsBoolean(),
            false
        );
    }
}