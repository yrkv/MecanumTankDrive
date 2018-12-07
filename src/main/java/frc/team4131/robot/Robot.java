package frc.team4131.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.XboxController;

public class Robot extends IterativeRobot {
    Talon frontLeft = new Talon(2);
    Talon backLeft = new Talon(3);
    Spark frontRight = new Spark(0);
    Spark backRight = new Spark(1);

    Hand leftHand = Hand.kLeft;
    Hand rightHand = Hand.kRight;
    XboxController controller = new XboxController(0);

    MecanumTankDrive mtd = new MecanumTankDrive(frontLeft, backLeft, frontRight, backRight);

    @Override
    public void robotInit() { }

    @Override
    public void disabledInit() { }

    @Override
    public void autonomousInit() { }

    @Override
    public void teleopInit() { }

    @Override
    public void testInit() { }


    @Override
    public void disabledPeriodic() { }
    
    @Override
    public void autonomousPeriodic() { }

    @Override
    public void teleopPeriodic() {
        double left = controller.getY(leftHand);
        double right = controller.getY(rightHand);

        double side = controller.getTriggerAxis(rightHand) - controller.getTriggerAxis(leftHand);

        mtd.driveMecanumTank(left, right, side);
    }

    @Override
    public void testPeriodic() { }
}
