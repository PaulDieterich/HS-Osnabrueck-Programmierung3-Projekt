package de.hsos.sportwetter.classes.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.android.gms.net.CronetProviderInstaller;

import org.chromium.net.CronetEngine;
import org.chromium.net.UrlRequest;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * To save Data from
 * https://openweathermap.org/api
 *
 * Example of current weather API response
 *      "temp":306.15, //current temperature
 *      "pressure":1013,
 *      "humidity":44,
 *      "temp_min":306, //min current temperature in the city
 *      "temp_max":306 //max current temperature in the city
 *
 * Example of daily forecast weather API response
 *         "day":297.77,  //daily averaged temperature
 *         "min":293.52, //daily min temperature
 *         "max":297.77, //daily max temperature
 *         "night":293.52, //night temperature
 *         "eve":297.77, //evening temperature
 *         "morn":297.77}, //morning temperature
 * */

public class Weather{


    private float temp;
    private float temp_min;
    private float temp_max;
    private int pressure;
    private int humidity;

    private float dailyAveragedTemp;
    private float dailyMinTemp;
    private float dailyMaxTemp;
    private float nightTemp;
    private float eveningTemp;
    private float morningTemp;


}
