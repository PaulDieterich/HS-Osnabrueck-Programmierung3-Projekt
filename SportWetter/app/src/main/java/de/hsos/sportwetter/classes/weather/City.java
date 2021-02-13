package de.hsos.sportwetter.classes.weather;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import net.aksingh.owmjapis.model.CurrentWeather;
/**
 * @author Stefan Waschk
 * City Klasse für die Erstellung von City Objekten
 */
@Entity(tableName = "city")
public class City {
   @PrimaryKey
   @ColumnInfo(name="id")
    private int id;
    @ColumnInfo(name="name") @NonNull
    private  String name;
    @ColumnInfo(name="state") @Nullable
    private  String state;
    @ColumnInfo(name="country") @Nullable
    private  String land;
    @ColumnInfo(name="lon") @NonNull
    private  double longitude;
    @ColumnInfo(name="lat")@NonNull
    private double latitude;

    /**
     * @author Stefan Waschk
     * @version 1
     * @since 20.01.2021
     * @param name - Name der Stadt
     * @param state - Bundesstaat (falls vorhanden), in dem sich die Stadt befindet
     * @param land - Land, in dem sich die Stadt befindet
     * @param longtitude - Längengrad der Stadt
     * @param latitude - Breitengrad der Stadt
     * */
    public City(String name, String state, String land, double longtitude, double latitude){
        setName(name);
        setState(state);
        setLand(land);
        setLongitude(longtitude);
        setLongitude(latitude);

    }
    /**
     * @author Stefan Waschk
     * @version 1
     * @since 20.01.2021
     * @param cwd - Benötigtes CurrentWeather Objekt, welches zuvor mit dem String des Stadtnamens
     *              gefüllt sein muss. Anhand dessen wird eine Anfrage an die OpenWeatherMap Java API
     *              weitergegeben, die wiederum die privaten Variablen füllt.
     * */
    public City(CurrentWeather cwd) {
        this.name = cwd.getCityName();
        this.land = cwd.getSystemData().getCountryCode();
        this.latitude = cwd.getCoordData().getLatitude();
        this.longitude = cwd.getCoordData().getLongitude();
    }

    public City() { }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    public void setName(String n){ name = n;}
    public String getName() { return this.name; }
    public void setLand(String l){land = l;}
    public String getLand() { return this.land; }
    public void setLongitude(double l){longitude = l;}
    public Double getLongitude() { return this.longitude; }
    public void setLatitude(double l){latitude = l;}
    public Double getLatitude() { return this.latitude; }
}
