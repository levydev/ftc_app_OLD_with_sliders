package org.aaas.stem.first.ftc.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;

public class DcMotorComponent extends MotorComponent {

    private DcMotor dcMotor;


    public DcMotorComponent(HardwareManager hardwareManager, String componentName) {

        super(hardwareManager, componentName);

        if (!isDriverDebugMode() ) {
            this.dcMotor = getHardwareManager().getHardwareMap().dcMotor.get(componentName);
        }
        else {
            getDebugMap().put("power" , "");
            getDebugMap().put("direction" , DcMotor.Direction.FORWARD.toString());
        }

    }


    public void setDirection(DcMotor.Direction direction) {

        if (!isDriverDebugMode() ) {
            this.dcMotor.setDirection(direction);
        }
        else {
            this.getDebugMap().put("direction", direction.name());
        }

    }

    public void setPower(double power) {
        if (!isDriverDebugMode() ) {
            this.dcMotor.setPower(power);
        }
        else {
            this.getDebugMap().put("power", Double.toString(power));
        }
    }

}
