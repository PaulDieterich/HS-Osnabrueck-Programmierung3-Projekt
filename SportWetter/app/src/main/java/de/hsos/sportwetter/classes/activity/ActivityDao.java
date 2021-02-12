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
    void insertActivity(Activity activity);

    @Update
    void updateActivity(Activity activity);

    @Delete
    void deleteActivity(Activity... activity);

    @Query("SELECT * FROM Activity")
    public List<Activity> getAllActivitys();


    @Query("SELECT * FROM ACTIVITY WHERE activity_id = :id")
     Activity getActivityById(long id);

}

