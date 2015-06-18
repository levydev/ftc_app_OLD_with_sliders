

package com.qualcomm.ftcrobotcontroller.aaas.opmodes;

import com.qualcomm.ftcrobotcontroller.aaas.hardware.DCMotorComponent;
import com.qualcomm.ftcrobotcontroller.aaas.hardware.IrSeekerSensorComponent;


import com.qualcomm.robotcore.hardware.DcMotor;




public class IrSeekerOpMode extends AAASOpMode {

	final static double MOTOR_POWER = 0.15; // Higher values will cause the robot to move faster
	final static double HOLD_IR_SIGNAL_STRENGTH = 0.50; // Higher values will cause the robot to follow closer

    final static double STARTING_ARM_POSITION = 0.1;
    final static double STARTING_CLAW_POSITION = 0.25;


	//Servo claw;
	//Servo arm;
    IrSeekerSensorComponent irSeeker;


    private DCMotorComponent motorRight;
    private DCMotorComponent motorLeft;

	/**
	 * Constructor
	 */
	public IrSeekerOpMode() {

	}

	/*
	 * Code to run when the op mode is first enabled goes here
	 * 
	 * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#start()
	 */
	@Override
	public void start() {


        motorRight = new DCMotorComponent(getHardwareManager(),"motor_2");
        motorLeft = new DCMotorComponent(getHardwareManager(),"motor_1");

        motorLeft.setDirection(DcMotor.Direction.REVERSE);

		//arm = hardwareManager.servoFor("servo_1");
		//claw = hardwareManager.servoFor("servo_6");


		/*
		 * We also assume that we have a Hitechnic IR Seeker v2 sensor
		 * with a name of "ir_seeker" configured for our robot.
		 */
		irSeeker = new IrSeekerSensorComponent(getHardwareManager(),"ir_seeker");

	}



	/*
	 * This method will be called repeatedly in a loop
	 * 
	 * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#run()
	 */
	@Override
	public void loop() {
		double angle = 0.0;
		double strength = 0.0;
		double left, right = 0.0;
		
		// keep manipulator out of the way.
		//arm.setPosition(STARTING_ARM_POSITION);
		//claw.setPosition(STARTING_CLAW_POSITION);
	
		/*
		 * Do we detect an IR signal?
		 */
		if (irSeeker.signalDetected())  {
			/*
			 * Signal was detected. Follow it.
			 */
			
			/*
			 * Get angle and strength of the signal.
			 * Note an angle of zero implies straight ahead.
			 * A negative angle implies that the source is to the left.
			 * A positive angle implies that the source is to the right.
			 */
			angle = irSeeker.getAngle();
			strength = irSeeker.getStrength();

            if (angle < -60)  {
                /*
                 * IR source is to the way left.
                 * Point turn to the left.
                 */
                left = -MOTOR_POWER;
                right = MOTOR_POWER;

            } else if (angle < -5) {
                // turn to the left and move forward.
                left = MOTOR_POWER - 0.05;
                right = MOTOR_POWER;
            } else if (angle > 5 && angle < 60) {
                // turn to the right and move forward.
                left = MOTOR_POWER;
                right = MOTOR_POWER - 0.05;
            } else if (angle > 60) {
                // point turn to right.
                left = MOTOR_POWER;
                right = -MOTOR_POWER;
            } else if (strength < HOLD_IR_SIGNAL_STRENGTH) {
				/*
				 * Signal is dead ahead but weak.
				 * Move forward towards signal
				 */
				left = MOTOR_POWER;
				right = MOTOR_POWER;
			} else {
				/*
				 * Signal is dead ahead and strong.
				 * Stop motors.
				 */
				left = 0.0;
				right = 0.0;
			}
		} else {
			/*
			 * Signal was not detected.
			 * Shut off motors
			 */
			left = 0.0;
			right = 0.0;
		}
		
		/*
		 * set the motor power
		 */
		motorRight.setPower(right);
		motorLeft.setPower(left);

		/*
		 * Send telemetry data back to driver station. Note that if we are using
		 * a legacy NXT-compatible motor controller, then the getPower() method
		 * will return a null value. The legacy NXT-compatible motor controllers
		 * are currently write only.
		 */

		telemetry.addData("Text", "*** Robot Data***");
		telemetry.addData("angle", "angle:  " + Double.toString(angle));
		telemetry.addData("strength", "sig strength: " + Double.toString(strength));
		telemetry.addData("left tgt pwr",  "left  pwr: " + Double.toString(left));
		telemetry.addData("right tgt pwr", "right pwr: " + Double.toString(right));
	}

	/*
	 * Code to run when the op mode is first disabled goes here
	 * 
	 * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#stop()
	 */
	@Override
	public void stop() {

	}

}
