package org.aaas.stem.first.ftc.hardware;

import com.qualcomm.robotcore.hardware.IrSeekerSensor;
import com.qualcomm.robotcore.hardware.LightSensor;

public class LightSensorComponent extends SensorComponent {

    private LightSensor lightSensor;


    public LightSensorComponent(HardwareManager hardwareManager, String componentName) {

        super(hardwareManager , componentName);

        if (!isDriverDebugMode() ) {
            lightSensor = getHardwareManager().getHardwareMap().lightSensor.get(componentName);
        }
        else {
            getDebugMap().put("ledEnabled" , "false");
            getDebugMap().put("lightLevel" , "0.0");
            getDebugMap().put("status" , "");

        }


    }


    public double getLightLevel() {

        if (!isDriverDebugMode() ) {
            return lightSensor.getLightLevel();
        }
        else {
            return Double.valueOf((String)getDebugMap().get("lightLevel"));

        }

    }

    public String status() {

        if (!isDriverDebugMode() ) {
            return lightSensor.status();
        }
        else {
            return (String)getDebugMap().get("status");

        }

    }

    public void enableLed(boolean aBool) {

        if (!isDriverDebugMode() ) {
             lightSensor.enableLed(aBool);
        }
        else {
             getDebugMap().put("ledEnabled" , Boolean.toString(aBool));
        }

    }


}
