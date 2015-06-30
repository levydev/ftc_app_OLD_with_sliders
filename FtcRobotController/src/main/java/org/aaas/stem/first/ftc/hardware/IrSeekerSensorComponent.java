package org.aaas.stem.first.ftc.hardware;

import com.qualcomm.robotcore.hardware.IrSeekerSensor;

public class IrSeekerSensorComponent extends SensorComponent {

    private IrSeekerSensor irSeeker;




    public IrSeekerSensorComponent(HardwareManager hardwareManager, String componentName) {

        super(hardwareManager , componentName);

        if (!isDriverDebugMode() ) {
            this.irSeeker = getHardwareManager().getHardwareMap().irSeekerSensor.get(componentName);
        }
        else {
            getDebugMap().put("signalDetected", "true");
            addDebugProperty("signalDetected", "type", "Boolean");

            getDebugMap().put("angle", "0.0");
            addDebugProperty("angle", "type", "Double");
            addDebugProperty("angle", "min", "-90");
            addDebugProperty("angle", "max", "90");
            addDebugProperty("angle", "decimalSpan", "1");

            getDebugMap().put("strength", "0.0");
            addDebugProperty("strength", "type", "Double");
            addDebugProperty("strength", "min", "0");
            addDebugProperty("strength", "max", "1");
            addDebugProperty("strength", "decimalSpan", "20");

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
