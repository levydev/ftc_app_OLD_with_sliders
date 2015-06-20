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
    private List<SensorComponent> sensors = new ArrayList<SensorComponent>();
    private List <MotorComponent> motors = new ArrayList<MotorComponent> ();

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

    public void setDriverDebugMode(RobotControllerDriverActivity robotControllerDriverActivity) {
        this.driverDebugMode = true;
        this.robotControllerDriverActivity = robotControllerDriverActivity;
    }


    public void addSensor(SensorComponent hardwareComponent) {

        this.sensors.add(hardwareComponent);
    }

    public void addMotor(MotorComponent hardwareComponent) {

        this.motors.add(hardwareComponent);
    }

    public List<SensorComponent>  getSensors() {
        return sensors;
    }

    public List <MotorComponent> getMotors() {
        return motors;
    }

}
