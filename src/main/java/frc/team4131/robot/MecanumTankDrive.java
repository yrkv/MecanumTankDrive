package frc.team4131.robot;


import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.drive.RobotDriveBase;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;

public class MecanumTankDrive extends RobotDriveBase {

    private static int instances = 0;

    private SpeedController m_frontLeftMotor;
    private SpeedController m_rearLeftMotor;
    private SpeedController m_frontRightMotor;
    private SpeedController m_rearRightMotor;

    public MecanumTankDrive(SpeedController frontLeftMotor, SpeedController rearLeftMotor,
                        SpeedController frontRightMotor, SpeedController rearRightMotor) {
        m_frontLeftMotor = frontLeftMotor;
        m_rearLeftMotor = rearLeftMotor;
        m_frontRightMotor = frontRightMotor;
        m_rearRightMotor = rearRightMotor;
        addChild(m_frontLeftMotor);
        addChild(m_rearLeftMotor);
        addChild(m_frontRightMotor);
        addChild(m_rearRightMotor);
        instances++;
        setName("MecanumTankDrive", instances);
    }


    public void driveMecanumTank(double leftSpeed, double rightSpeed, double sidewaysSpeed) {
        leftSpeed = limit(leftSpeed);
        leftSpeed = applyDeadband(leftSpeed, 0.02);

        rightSpeed = limit(rightSpeed);
        rightSpeed = applyDeadband(rightSpeed, 0.02);

        sidewaysSpeed = limit(sidewaysSpeed);
        sidewaysSpeed = applyDeadband(sidewaysSpeed, 0.02);


        double fl = leftSpeed + sidewaysSpeed;
        double bl = leftSpeed - sidewaysSpeed;
        double fr = rightSpeed - sidewaysSpeed;
        double br = rightSpeed + sidewaysSpeed;

        double max = Math.max(Math.max(fl, bl), Math.max(fr, br));


        if (max > 1) {
            fl = fl / max;
            bl = bl / max;
            fr = fr / max;
            br = br / max;
        }

        m_frontLeftMotor.set(fl);
        m_rearLeftMotor.set(bl);
        m_frontRightMotor.set(fr);
        m_rearRightMotor.set(br);

        m_safetyHelper.feed();
    }

    @Override
    public void stopMotor() {
        m_frontLeftMotor.stopMotor();
        m_frontRightMotor.stopMotor();
        m_rearLeftMotor.stopMotor();
        m_rearRightMotor.stopMotor();
        m_safetyHelper.feed();
    }

    @Override
    public String getDescription() {
        return "MecanumTankDrive";
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        builder.setSmartDashboardType("MecanumTankDrive");
        builder.addDoubleProperty("Front Left Motor Speed", m_frontLeftMotor::get,
                m_frontLeftMotor::set);
        builder.addDoubleProperty("Front Right Motor Speed", m_frontRightMotor::get,
                m_frontRightMotor::set);
        builder.addDoubleProperty("Rear Left Motor Speed", m_rearLeftMotor::get,
                m_rearLeftMotor::set);
        builder.addDoubleProperty("Rear Right Motor Speed", m_rearRightMotor::get,
                m_rearRightMotor::set);
    }
}
