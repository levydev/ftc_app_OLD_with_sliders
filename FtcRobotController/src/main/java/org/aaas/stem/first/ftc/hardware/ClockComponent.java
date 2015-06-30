package org.aaas.stem.first.ftc.hardware;


public class ClockComponent extends SensorComponent {


    public ClockComponent(HardwareManager hardwareManager, String componentName) {

        super(hardwareManager , componentName);

        if (isDriverDebugMode() ) {
            getDebugMap().put("elapsedSeconds", "3");
            addDebugProperty("elapsedSeconds", "type", "Double");
            addDebugProperty("elapsedSeconds", "min", "0");
            addDebugProperty("elapsedSeconds", "max", "30");
            addDebugProperty("elapsedSeconds", "decimalSpan", "1");

        }

    }


    public double getElapsedSeconds() {

        if (!isDriverDebugMode() ) {
            return getHardwareManager().getOpMode().time;
        }
        else {
            return Double.valueOf((String)getDebugMap().get("elapsedSeconds"));
        }
    }


}
