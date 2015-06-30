package org.aaas.stem.first.ftc.hardware;


import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public abstract class SensorComponent extends HardwareComponent {

    private Map<String,TextView> debugSensorViewMap;
    private Map<String,Map> debugSensorPropertyMap;

    public SensorComponent(HardwareManager hardwareManager, String componentName) {
        super(hardwareManager, componentName);

        if (getHardwareManager().isDriverDebugMode()) {
            getHardwareManager().addSensor(this);
            debugSensorViewMap = new HashMap<String,TextView>();
            debugSensorPropertyMap = new HashMap<String,Map>();
        }

    }

    public void addDebugView(String keyName, TextView valueView) {

        debugSensorViewMap.put(keyName, valueView);

    }

    public boolean geBooleanValueOn(String keyName) {
       return  Boolean.parseBoolean(this.valueOn(keyName));
    }
    public double geDoubleValueOn(String keyName) {
        return   Double.parseDouble(this.valueOn(keyName));
    }

    protected void addDebugProperty(String propertyName, String keyName, String val) {

        Map propMap = debugSensorPropertyMap.get(propertyName);
        if (propMap == null ) {
            propMap = new  HashMap<String,String>();
            debugSensorPropertyMap.put(propertyName,propMap);
        }
        propMap.put(keyName, val);

    }

    public boolean isBooleanValue(String propertyName ) {
        Map propMap = debugSensorPropertyMap.get(propertyName);
        if (propMap == null ) return false;
        return propMap.get("type").equals("Boolean");
    }
    public boolean isStringValue(String propertyName ) {
        Map propMap = debugSensorPropertyMap.get(propertyName);
        if (propMap == null ) return false;
        return propMap.get("type").equals("String");
    }
    public double getMaxOn(String propertyName) {
        Map propMap = debugSensorPropertyMap.get(propertyName);
        if (propMap == null ) return 0;
        return Double.parseDouble((String)propMap.get("max"));
    }
    public double getMinOn(String propertyName) {
        Map propMap = debugSensorPropertyMap.get(propertyName);
        if (propMap == null ) return 0;
        return Double.parseDouble((String)propMap.get("min")) ;
    }
    public double getDecimalSpanOn(String propertyName) {
        Map propMap = debugSensorPropertyMap.get(propertyName);
        if (propMap == null ) return 0;
        return Double.parseDouble((String)propMap.get("decimalSpan")) ;
    }


    public void syncDebugComponentValue(String keyName) {


        if ( this.isBooleanValue(keyName) ) {
            CheckBox cb = (CheckBox)debugSensorViewMap.get(keyName);
            if ( cb.isChecked() ) {
                getDebugMap().put(keyName , "true");
            }
            else {
                getDebugMap().put(keyName , "false");
            }
        }
        else if ( this.isStringValue(keyName) ) {
            String userEnteredValue = debugSensorViewMap.get(keyName).getText().toString();

            getDebugMap().put(keyName, userEnteredValue);
        }
        else {
            String userEnteredValue = debugSensorViewMap.get(keyName).getText().toString();

            try
            {
                Double.parseDouble(userEnteredValue);
                getDebugMap().put(keyName, userEnteredValue);
            }
            catch(NumberFormatException nfe)
            {
                getDebugMap().put(keyName, "0.0");
            }

        }

    }

    public Map<String, Map> getDebugSensorPropertyMap() {
        return debugSensorPropertyMap;
    }
}
