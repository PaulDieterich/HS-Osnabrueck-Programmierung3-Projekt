package de.hsos.sportwetter.classes;



import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import de.hsos.sportwetter.classes.location.Location;
import de.hsos.sportwetter.classes.sport.Sport;
import de.hsos.sportwetter.classes.user.ActivityProvider;
import de.hsos.sportwetter.classes.user.User;

public class Converters {

    @TypeConverter
    public static Date timestampToDate(Long date) {
        return date == null ? null : new Date(date);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
    @TypeConverter
    public static UUID fromStringToUUI(String uuid){
        return uuid == null ? null : UUID.fromString(uuid);
    }

    @TypeConverter
    public static String fromUUIDToString(UUID uuid){
        return uuid == null ? null : uuid.toString();
    }
    //Beispiel https://medium.com/@toddcookevt/android-room-storing-lists-of-objects-766cca57e3f9
    //Gson gson = new Gson();
    @TypeConverter
    public static List<Sport> stringToListSportObject(String data){
        Gson gson = new Gson();
        if(data == null) return Collections.emptyList();
        Type listType = new TypeToken<List<Sport>>() {}.getType();
        return gson.fromJson(data, listType);
    }
    @TypeConverter
    public static String listSportObjectToString(List<Sport> sport){
        Gson gson = new Gson();
        return gson.toJson(sport);
    }
    Gson gson = new Gson();
    @TypeConverter
    public static List<User> stringToUsertObject(String data){
        Gson gson = new Gson();
        if(data == null) return Collections.emptyList();
        Type listType = new TypeToken<List<User>>() {}.getType();
        return gson.fromJson(data, listType);
    }
    @TypeConverter
    public static String UsertObjectToString(List<User> user){
        Gson gson = new Gson();
        return gson.toJson(user);
    }
    @TypeConverter
    public static ActivityProvider stringToActivityProviderObject(String user) {
        return user == null ? null : new ActivityProvider(user);
    }

    @TypeConverter
    public static String activityProviderToString( ActivityProvider user) {
        return user == null ? null : user.toString();
    }
    @TypeConverter
    public static Sport stringToSportObject(String sport) {
        return sport == null ? null : new Sport(sport);
    }

    @TypeConverter
    public static String sportToString(Sport sport) {
        return sport == null ? null : sport.toString();
    }
    @TypeConverter
    public static Location stringToLocationObject(String l) {
        return l == null ? null : new Location(l);
    }

    @TypeConverter
    public static String locationToString(Location location) {
        return location == null ? null : location.toString();
    }
}
