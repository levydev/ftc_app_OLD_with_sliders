package com.qualcomm.ftcrobotcontroller.aaas.hardware;


import com.qualcomm.ftcrobotcontroller.aaas.RobotControllerDriverActivity;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.ArrayList;
import java.util.List;

public class HardwareManager {

    private HardwareMap hardwareMap;
    private boolean driverDebugMode = false;
    private RobotControllerDriverActivity robotControllerDriverActivity;
    private List sensors = new ArrayList();
    private List motors = new ArrayList();

    public HardwareManager(HardwareMap hardwareMap ) {

        this.hardwareMap =  hardwareMap;
    }

    HardwareMap getHardwareMap() {
        return hardwareMap;
    }

    void setHardwareMap(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
    }

    boolean isDriverDebugMode() {
        return driverDebugMode;
    }

    public void setDriverDebugMode(boolean driverDebugMode, RobotControllerDriverActivity robotControllerDriverActivity) {
        this.driverDebugMode = driverDebugMode;
        this.robotControllerDriverActivity = robotControllerDriverActivity;
    }


    public void addSensor(HardwareComponent hardwareComponent) {

        this.sensors.add(hardwareComponent);
    }

    public void addMotor(HardwareComponent hardwareComponent) {

        this.motors.add(hardwareComponent);
    }


}
