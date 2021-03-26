package com.example.accelerometerapi

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),SensorEventListener {
    private val sensorManager: SensorManager by lazy {
        getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER).also {
            sensorManager.registerListener(
                    this,
                    it,
                    SensorManager.SENSOR_DELAY_NORMAL,
                    SensorManager.SENSOR_DELAY_NORMAL
            )
        }
    }

    override fun onSensorChanged(p0: SensorEvent?) {
        Log.d(
                "SENSORS",
                "onSensorChanged: x=${p0?.values?.get(0)?.toInt()}, y=${p0?.values?.get(1)?.toInt()}"
        )
        accelerometerTxt.text = "x=${p0?.values?.get(0)?.toInt()}, y=${p0?.values?.get(1)?.toInt()}"
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        Log.d("SENSORS", "onSensorChanged: The values are ${p1}")

    }
}