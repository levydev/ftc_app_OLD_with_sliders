package org.aaas.stem.first.ftc.hardware;


import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public abstract class MotorComponent extends HardwareComponent{

    private Map<String,TextView> debugMotorViewMap;

    public MotorComponent(HardwareManager hardwareManager, String componentName) {
        super(hardwareManager, componentName);

        if (getHardwareManager().isDriverDebugMode() ) {
            getHardwareManager().addMotor(this);
            debugMotorViewMap = new HashMap<String,TextView>();
        }

    }


    public void addDebugView(String keyName, TextView valueView) {

        debugMotorViewMap.put(keyName, valueView);

    }

    public void syncDebugComponentValue(String keyName) {

        TextView valueView = debugMotorViewMap.get(keyName);
        valueView.setText( getDebugMap().get(keyName ));

    }

}
