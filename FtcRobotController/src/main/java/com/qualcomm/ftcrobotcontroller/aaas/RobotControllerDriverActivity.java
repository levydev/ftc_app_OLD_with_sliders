

package com.qualcomm.ftcrobotcontroller.aaas;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qualcomm.ftcrobotcontroller.FtcRobotControllerActivity;
import com.qualcomm.ftcrobotcontroller.R;


import org.aaas.stem.first.ftc.hardware.HardwareComponent;
import org.aaas.stem.first.ftc.hardware.HardwareManager;
import org.aaas.stem.first.ftc.hardware.SensorComponent;
import org.aaas.stem.first.ftc.hardware.MotorComponent;
import org.aaas.stem.first.ftc.opmodes.AAASOpMode;
import org.aaas.stem.first.ftc.opmodes.AutoRotateOp;

import java.lang.reflect.Constructor;


public class RobotControllerDriverActivity extends Activity {

    public static final String SHARED_PREY_KEY = "sharedPrefKey";
    private SharedPreferences sharedpreferences;
    private HardwareManager hardwareManager;

    private AAASOpMode opMode;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    sharedpreferences = getSharedPreferences(SHARED_PREY_KEY, Context.MODE_PRIVATE);
    String selectedOpModeName =  sharedpreferences.getString("opModeName",null);
    if (selectedOpModeName == null) {
          startActivity(new Intent(getBaseContext(), RobotControllerDriverOpModesActivity.class));
          return;
    }

    setContentView(R.layout.activity_ftc_controller_driver);

    try {
        Class<?> c = Class.forName(selectedOpModeName);
        Constructor<?> cons = c.getConstructor();
        opMode = (AAASOpMode)cons.newInstance();
    }
    catch (Throwable e) {

    }

    opMode.startInDebugMode(this);
    hardwareManager = opMode.getHardwareManager();



    TextView opModeName = (TextView) findViewById(R.id.opModeName);
    opModeName.setText("Op Mode: " + selectedOpModeName);

    LinearLayout sensorListView = (LinearLayout)findViewById(R.id.sensors);
    for  (SensorComponent  hc :  hardwareManager.getSensors() ) {
        addHardwareComponentView(hc , sensorListView , true);
    }

    LinearLayout motorListView = (LinearLayout)findViewById(R.id.motors);
    for  (MotorComponent  hc :  hardwareManager.getMotors() ) {
       addHardwareComponentView(hc , motorListView , false);
    }

    Button sendButton = (Button) findViewById(R.id.send_button);
    sendButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
             // Toast.makeText(getApplicationContext(), "msg msg", Toast.LENGTH_SHORT).show();
              for  (SensorComponent  hc :  hardwareManager.getSensors() ) {
                  syncSensorValues(hc);
              }
              opMode.loop();

              for  (MotorComponent  hc :  hardwareManager.getMotors() ) {
                  syncMotorValues(hc);
              }
          }
      });


  }

    private LinearLayout rowLayoutOn(LinearLayout parentLayout) {

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                (LinearLayout.LayoutParams.WRAP_CONTENT), (LinearLayout.LayoutParams.WRAP_CONTENT));

        LinearLayout rowLayout = new LinearLayout(this);
        rowLayout.setLayoutParams(layoutParams);
        rowLayout.setOrientation(LinearLayout.HORIZONTAL);

        parentLayout.addView(rowLayout);

        return rowLayout;


    }

    private void addHardwareComponentView(HardwareComponent hardwareComponent , LinearLayout listView , boolean isSensorList) {

        LinearLayout rowLayout = rowLayoutOn(listView);

        TextView label = new TextView(this);
        label.setText(hardwareComponent.getName());
        label.setTypeface(null, Typeface.BOLD);
        rowLayout.addView(label);

        for  (String  keyName :  hardwareComponent.getKeyNames() ) {
            if( keyName.equals("name")) {
                continue;
            }

            rowLayout = rowLayoutOn(listView);

            label = new TextView(this);
            label.setText(keyName);
            rowLayout.addView(label);

            if ( isSensorList) {
                EditText valueView = new EditText(this);
                //valueView.setLayoutParams(new LinearLayout.LayoutParams(40, 50));
                rowLayout.addView(valueView);
                ((SensorComponent)hardwareComponent).addDebugView(keyName, valueView);
                valueView.setText(hardwareComponent.valueOn(keyName));

            }
            else {
                TextView valueView = new TextView(this);
                LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                llp.setMargins(50, 0, 0, 0); // llp.setMargins(left, top, right, bottom);
                valueView.setLayoutParams(llp);
                rowLayout.addView(valueView);
                ((MotorComponent)hardwareComponent).addDebugView(keyName, valueView);
            }

        }

    }

    private void syncSensorValues(SensorComponent hardwareComponent ) {

        for  (String  keyName :  hardwareComponent.getKeyNames() ) {
            if( keyName.equals("name")) {
                continue;
            }

            hardwareComponent.syncDebugComponentValue(keyName);

        }

    }

    private void syncMotorValues(MotorComponent hardwareComponent ) {

        for  (String  keyName :  hardwareComponent.getKeyNames() ) {
            if( keyName.equals("name")) {
                continue;
            }

            hardwareComponent.syncDebugComponentValue(keyName);

        }

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ftc_robot_controller_driver, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {


            case R.id.action_choose_op_mode:
                startActivity(new Intent(getBaseContext(), RobotControllerDriverOpModesActivity.class));
                return true;

            case R.id.action_run_from_driver_app:
                startActivity(new Intent(getBaseContext(), FtcRobotControllerActivity.class));
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
