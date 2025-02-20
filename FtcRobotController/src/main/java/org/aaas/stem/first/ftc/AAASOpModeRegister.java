/* Copyright (c) 2014, 2015 Qualcomm Technologies Inc

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Qualcomm Technologies Inc nor the names of its contributors
may be used to endorse or promote products derived from this software without
specific prior written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. */

package org.aaas.stem.first.ftc;


import org.aaas.stem.first.ftc.hardware.MotorComponent;
import org.aaas.stem.first.ftc.opmodes.AutoRotateOp;
import org.aaas.stem.first.ftc.opmodes.CompassCalibration;
import org.aaas.stem.first.ftc.opmodes.IrSeekerOp;
import org.aaas.stem.first.ftc.opmodes.K9AutoTime;
import org.aaas.stem.first.ftc.opmodes.K9IrSeeker;
import org.aaas.stem.first.ftc.opmodes.K9Line;
import org.aaas.stem.first.ftc.opmodes.K9TankDrive;
import org.aaas.stem.first.ftc.opmodes.K9TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpModeManager;
import com.qualcomm.robotcore.eventloop.opmode.OpModeRegister;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Register Op Modes
 */
public class AAASOpModeRegister implements OpModeRegister {

   public final static List<Class> OP_MODE_REGISTRY_CLASSES = new ArrayList<Class>();

    static {
        OP_MODE_REGISTRY_CLASSES.addAll(

            Arrays.asList(
                    AutoRotateOp.class,
                    CompassCalibration.class,
                    IrSeekerOp.class,
                    K9AutoTime.class,
                    K9IrSeeker.class,
                    K9Line.class,
                    K9TankDrive.class,
                    K9TeleOp.class
            ));
    }


    /**
   * The Op Mode Manager will call this method when it wants a list of all
   * available op modes. Add your op mode to the list to enable it.
   *
   * @param manager op mode manager
   */
  public void register(OpModeManager manager) {

    /*
     * register your op modes here.
     * The first parameter is the name of the op mode
     * The second parameter is the op mode class property
     *
     * If two or more op modes are registered with the same name, the app will display an error.
     */

    for  (Class cl :  OP_MODE_REGISTRY_CLASSES ) {
        manager.register(cl.getSimpleName(), cl);
    }


  }
}
