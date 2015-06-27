package org.aaas.stem.first.ftc.hardware;


import com.qualcomm.robotcore.hardware.Gamepad;

import java.util.HashSet;
import java.util.Set;



public class GamepadComponent extends SensorComponent {

    private int gamepadID;
    private String[] debugGamepadControlList;


    public GamepadComponent(HardwareManager hardwareManager, String componentName, int gamepadID , String[] controlList) {

        super(hardwareManager, componentName);

        this.gamepadID = gamepadID;

        if (isDriverDebugMode() ) {
            this.debugGamepadControlList = controlList;

            getDebugMap().put("left_stick_x" , "0.0");
            getDebugMap().put("left_stick_y" , "0.0");
            getDebugMap().put("right_stick_x" , "0.0");
            getDebugMap().put("right_stick_y" , "0.0");
            getDebugMap().put("left_trigger" , "0.0");
            getDebugMap().put("right_trigger" , "0.0");

            getDebugMap().put("dpad_up" , "false");
            getDebugMap().put("dpad_down" , "false");
            getDebugMap().put("dpad_left" , "false");
            getDebugMap().put("dpad_right" , "false");
            getDebugMap().put("a" , "false");
            getDebugMap().put("b" , "false");
            getDebugMap().put("x" , "false");
            getDebugMap().put("y" , "false");
            getDebugMap().put("left_bumper" , "false");
            getDebugMap().put("right_bumper" , "false");

        }
     

    }

    public Gamepad getGamepad() {

        if (gamepadID == 1 ) {
             return getHardwareManager().getOpMode().gamepad1;

        }
        else if (gamepadID == 2 ){
             return getHardwareManager().getOpMode().gamepad2;
        }
        return null;
    }

    @Override
    public Set<String> getKeyNames() {
        Set keys = new HashSet();
        for ( String key : debugGamepadControlList){
            keys.add(key);
        }

        return keys;
    }


    public float getLeft_stick_x() {
        if (!isDriverDebugMode() ) {
            return getGamepad().left_stick_x;
        }
        else {
            return Float.valueOf((String)getDebugMap().get("left_stick_x"));
        }
    }

    public float getLeft_stick_y() {
        if (!isDriverDebugMode() ) {
            return getGamepad().left_stick_y;
        }
        else {
            return Float.valueOf((String)getDebugMap().get("left_stick_y"));
        }
    }

    public float getRight_stick_x() {
        if (!isDriverDebugMode() ) {
            return getGamepad().right_stick_x;
        }
        else {
            return Float.valueOf((String)getDebugMap().get("right_stick_x"));
        }
    }

    public float getRight_stick_y() {
        if (!isDriverDebugMode() ) {
            return getGamepad().right_stick_y;
        }
        else {
            return Float.valueOf((String)getDebugMap().get("right_stick_y"));
        }
    }


    public float getLeft_trigger() {
        if (!isDriverDebugMode() ) {
            return getGamepad().left_trigger;
        }
        else {
            return Float.valueOf((String)getDebugMap().get("left_trigger"));
        }
    }

    public float getRight_trigger() {
        if (!isDriverDebugMode() ) {
            return getGamepad().right_trigger;
        }
        else {
            return Float.valueOf((String)getDebugMap().get("right_trigger"));
        }
    }
    
    public boolean isDpad_up() {
        if (!isDriverDebugMode() ) {
            return getGamepad().dpad_up;
        }
        else {
            return Boolean.parseBoolean((String)getDebugMap().get("dpad_up"));
        }
    }

    public boolean isDpad_down() {
        if (!isDriverDebugMode() ) {
            return getGamepad().dpad_down;
        }
        else {
            return Boolean.parseBoolean((String)getDebugMap().get("dpad_down"));
        }
    }

    public boolean isDpad_left() {
        if (!isDriverDebugMode() ) {
            return getGamepad().dpad_left;
        }
        else {
            return Boolean.parseBoolean((String)getDebugMap().get("dpad_left"));
        }
    }

    public boolean isDpad_right() {
        if (!isDriverDebugMode() ) {
            return getGamepad().dpad_right;
        }
        else {
            return Boolean.parseBoolean((String)getDebugMap().get("dpad_right"));
        }
    }

    public boolean isA() {
        if (!isDriverDebugMode() ) {
            return getGamepad().a;
        }
        else {
            return Boolean.parseBoolean((String)getDebugMap().get("a"));
        }
    }

    public boolean isB() {
        if (!isDriverDebugMode() ) {
            return getGamepad().b;
        }
        else {
            return Boolean.parseBoolean((String)getDebugMap().get("b"));
        }
    }

    public boolean isX() {
        if (!isDriverDebugMode() ) {
            return getGamepad().x;
        }
        else {
            return Boolean.parseBoolean((String)getDebugMap().get("x"));
        }
    }

    public boolean isY() {
        if (!isDriverDebugMode() ) {
            return getGamepad().y;
        }
        else {
            return Boolean.parseBoolean((String)getDebugMap().get("y"));
        }
    }

    public boolean isLeft_bumper() {
        if (!isDriverDebugMode() ) {
            return getGamepad().left_bumper;
        }
        else {
            return Boolean.parseBoolean((String)getDebugMap().get("left_bumper"));
        }
    }

    public boolean isRight_bumper() {
        if (!isDriverDebugMode() ) {
            return getGamepad().right_bumper;
        }
        else {
            return Boolean.parseBoolean((String)getDebugMap().get("right_bumper"));
        }
    }

}
