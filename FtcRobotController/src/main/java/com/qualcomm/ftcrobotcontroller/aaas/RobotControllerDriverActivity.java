

package com.qualcomm.ftcrobotcontroller.aaas;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;

import com.qualcomm.ftcrobotcontroller.R;
import com.qualcomm.ftcrobotcontroller.aaas.hardware.HardwareManager;
import com.qualcomm.ftcrobotcontroller.aaas.opmodes.IrSeekerOpMode;


public class RobotControllerDriverActivity extends Activity {


  private HardwareManager hardwareManager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    IrSeekerOpMode opMode = new IrSeekerOpMode();
    opMode.startInDebugMode(this);
    hardwareManager = opMode.getHardwareManager();

    setContentView(R.layout.activity_ftc_controller_driver);

    //hardwareManager.g




  }

  @Override
  protected void onStart() {
    super.onStart();

  }

  @Override
  protected void onResume() {

      super.onResume();



  }

  @Override
  public void onPause() {
    super.onPause();
  }

  @Override
  protected void onStop() {
    super.onStop();


  }


  @Override
  public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
    // don't destroy assets on screen rotation
  }


}
