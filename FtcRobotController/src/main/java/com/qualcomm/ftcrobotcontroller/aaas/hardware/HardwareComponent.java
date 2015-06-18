package com.qualcomm.ftcrobotcontroller.aaas.hardware;


import java.util.HashMap;
import java.util.Map;

public class HardwareComponent {

    private HardwareManager hardwareManager;
    private Map debugMap;

    public HardwareComponent(HardwareManager hardwareManager) {

        this.hardwareManager =  hardwareManager;

        if (this.hardwareManager.isDriverDebugMode() ) {
            debugMap = new HashMap();
        }


    }

    HardwareManager getHardwareManager() {
        return hardwareManager;
    }


    protected boolean isDriverDebugMode() {
        return getHardwareManager().isDriverDebugMode();

    }

    protected Map getDebugMap() {
        return debugMap;
    }
}
