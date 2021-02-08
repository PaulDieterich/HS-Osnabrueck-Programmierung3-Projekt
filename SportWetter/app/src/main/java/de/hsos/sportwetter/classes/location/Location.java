package de.hsos.sportwetter.classes.location;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * @author Paul Dieterich
 * location klasse für die erstellung von locaion Objekten
 * ebenso eine emplementierung von androidx.room.entity
 */

@Entity(tableName = "LOCATION")
public class Location {
    @PrimaryKey @NonNull
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




    /**
     * @author Paul Dieterich
     * @version 2
     * @since 20.01.2021
     * @param placeName - name des ortes der aktivität
     * @param street - Straßenname
     * @param country - ländername
     * @param city - Stadtname
     * @param plz - postleitzahl
     * @param latitude - breitengrad - wichtig für die spätere googlemaps anbindung (falls zeit)
     * @param longitude - längengrad - wichtig für die spätere googlemaps anbindung (falls zeit)
     * */
    public Location(String placeName, String street, String country, String city, int plz, long latitude, long longitude) {
        this.placeName = placeName;
        this.street = street;
        this.country = country;
        this.city = city;
        this.plz = plz;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Location(){
        this.placeName = "unset";
        this.street = "unset";
        this.country = "unset";
        this.city = "unset";
        this.plz = 0;
        this.latitude = 0;
        this.longitude = 0;
    }
    public Location(String name) {
        this.placeName = name;
        this.street = "unset";
        this.country = "unset";
        this.city = "unset";
        this.plz = 0;
        this.latitude = 0;
        this.longitude = 0;
    }
    public Location(Location l) {
        this.placeName = l.placeName;
        this.street = l.street;
        this.country = l.country;
        this.city = l.city;
        this.plz = l.plz;
        this.latitude = l.latitude;
        this.longitude = l.longitude;
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

    @Override
    public String toString() {
        return "Location{" +
                "placeName='" + getPlaceName() + '\'' +
                '}';
    }
}
