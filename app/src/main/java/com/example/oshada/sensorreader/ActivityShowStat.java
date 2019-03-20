package com.example.oshada.sensorreader;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class ActivityShowStat extends AppCompatActivity implements SensorEventListener {

    TextView textView, textView1, textView2;
    Button mButton;
    boolean started;
    private SensorManager sensorManager;
    private Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_stat);
        textView = findViewById(R.id.textView);
        textView1 = findViewById(R.id.textView2);
        textView2 = findViewById(R.id.textView3);
        mButton = findViewById(R.id.button);
        started = false;
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                started = !started;
                onButtonClick();
            }
        });

    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] values = event.values;
        textView.setText(Float.toString(values[0]));
        textView1.setText(Float.toString(values[1]));
        textView2.setText(Float.toString(values[2]));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private void onButtonClick() {
        sensorManager = (SensorManager) getApplicationContext().getSystemService(Context.SENSOR_SERVICE);

        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (started) {
            mButton.setText("STOP");
            sensorManager.registerListener(this, sensor, 100000);
        } else {
            mButton.setText("START");
            sensorManager.unregisterListener(this, sensor);
        }
    }


}
