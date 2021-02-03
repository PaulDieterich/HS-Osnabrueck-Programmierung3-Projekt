package de.hsos.sportwetter.classes.activity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ActivityDao {

    @Insert
    void insertLocation(Activity activity);

    @Update
    void updateLocation(Activity activity);

    @Delete
    void deleteLocation(Activity... activity);

    @Query("SELECT * FROM Activity")
    public List<Activity> getAllActivitys();


}

