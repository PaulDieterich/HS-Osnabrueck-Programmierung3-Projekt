package de.hsos.sportwetter.classes.weather;

import androidx.appcompat.app.AppCompatActivity;

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

import de.hsos.sportwetter.classes.Callback;

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

public class Weather extends AppCompatActivity {
    private Bitmap bitmap;
    private Canvas canvas;
    private ImageView imageView;
    private Paint paint;

    private int breite, hoehe;
    private int cityID;

    final private String apiKey = "21e7a9fdfcb6191632a1252da242cc81";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.bitmap = Bitmap.createBitmap(this.breite, this.hoehe, Bitmap.Config.ARGB_8888);
        this.canvas = new Canvas(this.bitmap);
        this.imageView = new ImageView(this);
        this.imageView.setImageBitmap(this.bitmap);
        this.paint = new Paint();

        //setContentView(R.layout.activity_main);
        setContentView(imageView);

        //Cronet initialisieren, um Executor zu bauen
        CronetProviderInstaller.installProvider(this);
        CronetEngine.Builder myBuilder = new CronetEngine.Builder(this);
        CronetEngine cronetEngine = myBuilder.build();

        //Executor initialisieren, einzelner Thread (Multithread ginge wohl auch)
        Executor executor = Executors.newSingleThreadExecutor();

        //Builder mit URL (CityID aus res/city.list.json), Callback-Object und Executor vorbereiten
        UrlRequest.Builder requestBuilder = cronetEngine.newUrlRequestBuilder(
                "api.openweathermap.org/data/2.5/weather?id=" + cityID + "&appid=" + apiKey,
                new Callback(), executor);

        //Request ueber den Builder bauen
        UrlRequest request = requestBuilder.build();

        //Request abfeuern
        request.start();
    }
}
