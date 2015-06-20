package com.qualcomm.ftcrobotcontroller.aaas.opmodes;


import com.qualcomm.ftcrobotcontroller.aaas.RobotControllerDriverActivity;
import com.qualcomm.ftcrobotcontroller.aaas.hardware.HardwareManager;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;


public abstract class AAASOpMode extends OpMode {


    private HardwareManager hardwareManager;

    /**
     * Constructor
     */
    public AAASOpMode() {

    }

    public HardwareManager getHardwareManager() {
        if (hardwareManager == null) {

            this.hardwareManager = new HardwareManager(hardwareMap);
        }
        return hardwareManager;
    }

    public void setHardwareManager(HardwareManager hardwareManager) {
        this.hardwareManager = hardwareManager;
    }


    public void startInDebugMode(RobotControllerDriverActivity robotControllerDriverActivity) {

        getHardwareManager().setDriverDebugMode( robotControllerDriverActivity);
        this.start();


    }



}