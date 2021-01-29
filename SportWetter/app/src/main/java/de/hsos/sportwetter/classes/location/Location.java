package de.hsos.sportwetter.location;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
/**
 * @author Paul Dieterich
 * location klasse für die erstellung von locaion Objekten
 * ebenso eine emplementierung von androidx.room.entity
 */

@Entity(tableName = "LOCATION")
public class Location {

    @ColumnInfo(name = "placeName")
    private String placeName;
    @ColumnInfo(name = "street")
    private String street;
    @ColumnInfo(name = "country")
    private String country;
    @ColumnInfo(name = "city")
    private String city;
    @ColumnInfo(name = "plz")
    private int plz;
    @ColumnInfo(name = "latitude")
    private long  latitude;
    @ColumnInfo(name = "longitude")
    private long longitude;

    public Location(String placeName, String streed, String country, String city, int plz, long latitude, long longitude) {
        this.placeName = placeName;
        this.street = streed;
        this.country = country;
        this.city = city;
        this.plz = plz;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPlz() {
        return plz;
    }

    public void setPlz(int plz) {
        this.plz = plz;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }
}
