package org.aaas.stem.first.ftc.hardware;

import com.qualcomm.robotcore.hardware.CompassSensor;
import com.qualcomm.robotcore.hardware.IrSeekerSensor;

public class CompassSensorComponent extends SensorComponent {

    private CompassSensor compass;


    public CompassSensorComponent(HardwareManager hardwareManager, String componentName) {

        super(hardwareManager , componentName);

        if (!isDriverDebugMode() ) {
            this.compass = getHardwareManager().getHardwareMap().compassSensor.get(componentName);
        }
        else {
            getDebugMap().put("direction" ,"0.0");
            addDebugProperty("direction", "type", "Double");
            addDebugProperty("direction", "min", "-360");
            addDebugProperty("direction", "max", "360");
            addDebugProperty("direction", "decimalSpan", "1");

            getDebugMap().put("mode", CompassSensor.CompassMode.MEASUREMENT_MODE.toString());
            addDebugProperty("mode", "type", "String");

            getDebugMap().put("calibrationFailed", "false");
            addDebugProperty("calibrationFailed", "type", "Boolean");
        }


    }

    public double getDirection() {

        if (!isDriverDebugMode() ) {
            return compass.getDirection();
        }
        else {
            return Double.valueOf((String)getDebugMap().get("direction"));

        }

    }

    public boolean calibrationFailed() {
        if (!isDriverDebugMode() ) {
            return compass.calibrationFailed();
        }
        else {
            return Boolean.parseBoolean((String)getDebugMap().get("calibrationFailed"));

        }
    }


    public void setMode(CompassSensor.CompassMode mode) {
        if (!isDriverDebugMode() ) {
            compass.setMode(mode);
        }
        else {
            getDebugMap().put("mode",mode.toString());

        }

    }
}
