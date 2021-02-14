package de.hsos.sportwetter.classes.activity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * Dao für die Activity Klasse.
 * */

@Dao
public interface ActivityDao {

    @Insert
    void insertActivity(Activity activity);

    @Update
    void updateActivity(Activity activity);

    @Delete
    void deleteActivity(Activity... activity);

    /**
     *
     * getAllActivitys sucht in der Activity Tabelle nach Aktivitäten
     * @return Liste aller Aktivitäten
     */

    @Query("SELECT * FROM Activity")
    public List<Activity> getAllActivitys();

    /**
     *
     * @return Gibt eine Aktivität zurück, die zu der gesuchten :id passt
     * */

    @Query("SELECT * FROM ACTIVITY WHERE activity_id = :id")
     Activity getActivityById(long id);

}

