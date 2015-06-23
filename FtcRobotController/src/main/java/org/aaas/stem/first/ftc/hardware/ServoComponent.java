package org.aaas.stem.first.ftc.hardware;

import com.qualcomm.robotcore.hardware.Servo;

public class ServoComponent extends MotorComponent {

    private Servo servo;


    public ServoComponent(HardwareManager hardwareManager, String componentName) {

        super(hardwareManager, componentName);

        if (!isDriverDebugMode() ) {
            this.servo = getHardwareManager().getHardwareMap().servo.get(componentName);
        }
        else {
            getDebugMap().put("position" , "0.0");
            getDebugMap().put("direction" , Servo.Direction.FORWARD.toString());
        }

    }


    public void setDirection(Servo.Direction direction) {

        if (!isDriverDebugMode() ) {
            this.servo.setDirection(direction);
        }
        else {
            this.getDebugMap().put("direction", direction.name());
        }

    }

    public void setPosition(double position) {

        if (!isDriverDebugMode() ) {
            this.servo.setPosition(position);
        }
        else {
            this.getDebugMap().put("position", Double.toString(position));
        }

    }




}
