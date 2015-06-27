package org.aaas.stem.first.ftc.hardware;


import com.qualcomm.ftcrobotcontroller.aaas.RobotControllerDriverActivity;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.aaas.stem.first.ftc.opmodes.AAASOpMode;

import java.util.ArrayList;
import java.util.List;

public class HardwareManager {

    private HardwareMap hardwareMap;
    private boolean driverDebugMode = false;
    private RobotControllerDriverActivity robotControllerDriverActivity;
    private List<SensorComponent> sensors = new ArrayList<SensorComponent>();
    private List <MotorComponent> motors = new ArrayList<MotorComponent> ();

   private AAASOpMode opMode;

    public HardwareManager(HardwareMap hardwareMap, AAASOpMode opMode) {

        this.hardwareMap =  hardwareMap;
        this.opMode = opMode;
    }

    HardwareMap getHardwareMap() {
        return hardwareMap;
    }

    void setHardwareMap(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
    }

    public boolean isDriverDebugMode() {
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

    public AAASOpMode getOpMode() {
        return opMode;
    }
}
