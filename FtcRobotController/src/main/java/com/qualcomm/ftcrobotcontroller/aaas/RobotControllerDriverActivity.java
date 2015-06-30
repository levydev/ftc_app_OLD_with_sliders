

package com.qualcomm.ftcrobotcontroller.aaas;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.LinearLayout.LayoutParams;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.qualcomm.ftcrobotcontroller.FtcRobotControllerActivity;
import com.qualcomm.ftcrobotcontroller.R;
import com.qualcomm.ftcrobotcontroller.ViewLogsActivity;
import com.qualcomm.robotcore.util.RobotLog;


import org.aaas.stem.first.ftc.AAASOpModeRegister;
import org.aaas.stem.first.ftc.hardware.HardwareComponent;
import org.aaas.stem.first.ftc.hardware.HardwareManager;
import org.aaas.stem.first.ftc.hardware.SensorComponent;
import org.aaas.stem.first.ftc.hardware.MotorComponent;
import org.aaas.stem.first.ftc.opmodes.AAASOpMode;
import org.aaas.stem.first.ftc.opmodes.AutoRotateOp;

import java.lang.reflect.Constructor;
import java.util.Iterator;
import java.util.Map;


public class RobotControllerDriverActivity extends Activity {

    protected static final String VIEW_LOGS_ACTION = "com.qualcomm.ftcrobotcontroller.VIEW_LOGS";

    public static final String SHARED_PREY_KEY = "sharedPrefKey";
    private SharedPreferences sharedpreferences;
    private AAASOpMode opMode;

    private LinearLayout telemetryList;

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


    TextView opModeName = (TextView) findViewById(R.id.opModeName);
    opModeName.setText( selectedOpModeName);


    LinearLayout sensorListView = (LinearLayout)findViewById(R.id.sensors);
    for  (SensorComponent  hc :  opMode.getHardwareManager().getSensors() ) {
        addHardwareComponentView(hc , sensorListView , true);
    }

    LinearLayout motorListView = (LinearLayout)findViewById(R.id.motors);
    for  (MotorComponent  hc :  opMode.getHardwareManager().getMotors() ) {
          addHardwareComponentView(hc , motorListView , false);
    }

    telemetryList =   (LinearLayout) findViewById(R.id.telemetry);

    Button sendButton = (Button) findViewById(R.id.send_button);
    sendButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              opMode.resetDebugTelemetry();

             // Toast.makeText(getApplicationContext(), "msg msg", Toast.LENGTH_SHORT).show();
              for  (SensorComponent  hc :  opMode.getHardwareManager().getSensors() ) {
                  syncSensorValues(hc);
              }
              opMode.loop();

              for  (MotorComponent  hc :  opMode.getHardwareManager().getMotors() ) {
                  syncMotorValues(hc);
              }

              syncTelemetry();

          }
      });


  }

    private void syncTelemetry() {

        telemetryList.removeAllViews();


        for (String teleItem : opMode.getDebugTelemetry()) {
            LinearLayout rowLayout = rowLayoutOn(telemetryList);
            TextView label = new TextView(this);
            label.setText(teleItem);

            rowLayout.addView(label);
        }




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
                SensorComponent sensorComponent = ((SensorComponent)hardwareComponent);
                if (sensorComponent.isBooleanValue(keyName) ) {
                    CheckBox valueView = new CheckBox(this);
                    rowLayout.addView(valueView);
                    sensorComponent.addDebugView(keyName, valueView);
                    valueView.setChecked(sensorComponent.geBooleanValueOn(keyName));
                    continue;
                }
                if (sensorComponent.isStringValue(keyName) ) {
                    final EditText valueView = new EditText(this);
                    rowLayout.addView(valueView);

                    sensorComponent.addDebugView(keyName, valueView);
                    valueView.setText(sensorComponent.valueOn(keyName));
                    continue;
                }

                final EditText valueView = new EditText(this);
                rowLayout.addView(valueView);

                sensorComponent.addDebugView(keyName, valueView);
                valueView.setText(sensorComponent.valueOn(keyName));

                final double min = sensorComponent.getMinOn(keyName);
                final double max = sensorComponent.getMaxOn(keyName);
                final double decimalSpan = sensorComponent.getDecimalSpanOn(keyName);

                SeekBar seekBar = new SeekBar(this);
                seekBar.setVisibility(View.VISIBLE);
                LayoutParams lp = new LayoutParams(250, LayoutParams.WRAP_CONTENT);
                lp.setMargins(0, 26, 0, 0);
                seekBar.setLayoutParams(lp);

                seekBar.setMax((int) ((max - min) * decimalSpan));
                seekBar.setProgress((int) Math.abs(min));

                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                    public void onStopTrackingTouch(SeekBar arg0) {


                    }

                    public void onStartTrackingTouch(SeekBar arg0) {

                    }

                    public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {

                        double dbVal =  ( arg1 /  decimalSpan )  + min;
                        dbVal = Math.round(dbVal * 1000 )/ 1000d;
                        valueView.setText(String.valueOf(dbVal));


                    }
                });


                rowLayout.addView(seekBar);


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

            case R.id.action_view_logs:
                Intent viewLogsIntent = new Intent(VIEW_LOGS_ACTION);
                viewLogsIntent.putExtra(ViewLogsActivity.FILENAME, RobotLog.getLogFilename(this));
                startActivity(viewLogsIntent);
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
