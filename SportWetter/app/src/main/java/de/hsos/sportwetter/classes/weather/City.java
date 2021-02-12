package de.hsos.sportwetter.classes.weather;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import net.aksingh.owmjapis.model.CurrentWeather;
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

    public City(String name, String state, String land, double longtitude, double latitude){
        setName(name);
        setState(state);
        setLand(land);
        setLongitude(longtitude);
        setLongitude(latitude);

    }


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
