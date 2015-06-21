package org.aaas.stem.first.ftc.hardware;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class HardwareComponent {

    private HardwareManager hardwareManager;
    private Map <String,String>debugMap;

    public HardwareComponent(HardwareManager hardwareManager, String componentName) {

        this.hardwareManager =  hardwareManager;

        if (this.hardwareManager.isDriverDebugMode() ) {
            debugMap = new HashMap <String,String>();
            debugMap.put("name", componentName);
        }

    }

    HardwareManager getHardwareManager() {

        return hardwareManager;
    }

    protected boolean isDriverDebugMode() {
        return getHardwareManager().isDriverDebugMode();

    }

    protected Map <String,String> getDebugMap() {

        return debugMap;
    }


    public String getName() {
        return  getDebugMap().get("name");
    }

    public Set<String> getKeyNames() {
        return  getDebugMap().keySet();
    }

    public String valueOn(String keyName) {

        return  getDebugMap().get(keyName);
    }



}
