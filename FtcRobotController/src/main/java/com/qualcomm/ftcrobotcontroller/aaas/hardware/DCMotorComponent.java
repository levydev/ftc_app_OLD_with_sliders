package com.qualcomm.ftcrobotcontroller.aaas.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;

public class DCMotorComponent extends HardwareComponent {

    private DcMotor dcMotor;


    public DCMotorComponent(HardwareManager hardwareManager, String componentName) {

        super(hardwareManager);

        if (!isDriverDebugMode() ) {
            this.dcMotor = getHardwareManager().getHardwareMap().dcMotor.get(componentName);
        }
        else {
            hardwareManager.addMotor(this);
        }


    }

    protected boolean isDriverDebugMode() {
        return getHardwareManager().isDriverDebugMode();

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
