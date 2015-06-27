

package org.aaas.stem.first.ftc.opmodes;

import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.aaas.stem.first.ftc.hardware.DcMotorComponent;
import org.aaas.stem.first.ftc.hardware.IrSeekerSensorComponent;


/**
 * Follow an IR Beacon
 * <p>
 * How to use: <br>
 * Make sure the Modern Robotics IR beacon is off. <br>
 * Set it to 1200 at 180.  <br>
 * Make sure the side of the beacon with the LED on is facing the robot. <br>
 * Turn on the IR beacon. The robot will now follow the IR beacon. <br>
 * To stop the robot, turn the IR beacon off. <br>
 */
public class IrSeekerOp extends AAASOpMode {

	final static double MOTOR_POWER = 0.15; // Higher values will cause the robot to move faster
	final static double HOLD_IR_SIGNAL_STRENGTH = 0.50; // Higher values will cause the robot to follow closer

    IrSeekerSensorComponent irSeeker;
    private DcMotorComponent motorRight;
    private DcMotorComponent motorLeft;

	public IrSeekerOp() {

	}

    @Override
    public boolean isAutonomous() {
        return true;
    }

	@Override
	public void start() {
        irSeeker = new IrSeekerSensorComponent(getHardwareManager(),"ir_seeker");
        motorRight = new DcMotorComponent(getHardwareManager(),"motor_2");
        motorLeft = new DcMotorComponent(getHardwareManager(),"motor_1");

        motorLeft.setDirection(DcMotor.Direction.REVERSE);

	}


    @Override
    public void loop() {

        // Is an IR signal detected?
        if (irSeeker.signalDetected()) {
            // an IR signal is detected

            // Get the angle and strength of the signal
            double angle = irSeeker.getAngle();
            double strength = irSeeker.getStrength();

            // which direction should we move?
            if (angle < 0) {
                // we need to move to the left
                motorRight.setPower(MOTOR_POWER);
                motorLeft.setPower(-MOTOR_POWER);
            } else if (angle > 0) {
                // we need to move to the right
                motorRight.setPower(-MOTOR_POWER);
                motorLeft.setPower(MOTOR_POWER);
            } else if (strength < HOLD_IR_SIGNAL_STRENGTH) {
                // the IR signal is weak, approach
                motorRight.setPower(MOTOR_POWER);
                motorLeft.setPower(MOTOR_POWER);
            } else {
                // the IR signal is strong, stay here
                motorRight.setPower(0.0);
                motorLeft.setPower(0.0);
            }
        } else {
            // no IR signal is detected
            motorRight.setPower(0.0);
            motorLeft.setPower(0.0);
        }

        DbgLog.msg(irSeeker.toString());


    }

    @Override
    public void stop() {
        // no action needed
    }

}
