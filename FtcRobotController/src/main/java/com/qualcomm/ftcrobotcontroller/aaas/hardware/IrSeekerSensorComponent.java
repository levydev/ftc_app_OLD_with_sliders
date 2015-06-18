package com.qualcomm.ftcrobotcontroller.aaas.hardware;

import com.qualcomm.robotcore.hardware.IrSeekerSensor;

public class IrSeekerSensorComponent extends HardwareComponent {

    private IrSeekerSensor irSeeker;


    public IrSeekerSensorComponent(HardwareManager hardwareManager, String componentName) {

        super(hardwareManager);

        if (!isDriverDebugMode() ) {
            this.irSeeker = getHardwareManager().getHardwareMap().irSeekerSensor.get(componentName);
        }
        else {
            getHardwareManager().addSensor(this);
        }


    }

     public boolean signalDetected() {
        if (!isDriverDebugMode() ) {
            return irSeeker.signalDetected();
        }
        else {
            return Boolean.parseBoolean((String)getDebugMap().get("signalDetected"));

        }
    }


    public double getAngle() {

        if (!isDriverDebugMode() ) {
            return irSeeker.getAngle();
        }
        else {
            return Double.valueOf((String)getDebugMap().get("angle"));

        }

    }

    public double getStrength() {

        if (!isDriverDebugMode() ) {
            return irSeeker.getStrength();
        }
        else {
            return Double.valueOf((String)getDebugMap().get("strength"));

        }

    }


}
