package com.qualcomm.ftcrobotcontroller.aaas.hardware;


import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;

public abstract class SensorComponent extends HardwareComponent {

    private Map<String,EditText> debugSensorViewMap;

    public SensorComponent(HardwareManager hardwareManager, String componentName) {
        super(hardwareManager, componentName);

        if (getHardwareManager().isDriverDebugMode()) {
            getHardwareManager().addSensor(this);
            debugSensorViewMap = new HashMap<String,EditText>();
        }

    }

    public void addDebugView(String keyName, EditText valueView) {

        debugSensorViewMap.put(keyName, valueView);

    }

    public void syncDebugComponentValue(String keyName) {

        String userEnteredValue = debugSensorViewMap.get(keyName).getText().toString();
        getDebugMap().put(keyName , userEnteredValue);


    }


}
