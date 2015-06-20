package com.qualcomm.ftcrobotcontroller.aaas.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;

public class DCMotorComponent extends MotorComponent {

    private DcMotor dcMotor;


    public DCMotorComponent(HardwareManager hardwareManager, String componentName) {

        super(hardwareManager, componentName);

        if (!isDriverDebugMode() ) {
            this.dcMotor = getHardwareManager().getHardwareMap().dcMotor.get(componentName);
        }
        else {
            getDebugMap().put("Power" , "");
            getDebugMap().put("Direction" , "");
        }

    }


    public void setDirection(DcMotor.Direction direction) {

        if (!isDriverDebugMode() ) {
            this.dcMotor.setDirection(direction);
        }
        else {
            this.getDebugMap().put("Direction", direction.name());
        }

    }

    public void setPower(double power) {
        if (!isDriverDebugMode() ) {
            this.dcMotor.setPower(power);
        }
        else {
            this.getDebugMap().put("Power", Double.toString(power));
        }
    }

}
