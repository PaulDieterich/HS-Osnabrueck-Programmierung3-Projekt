package de.hsos.sportwetter.classes.location;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * @author Paul Dieterich
 * Dao für Location
 * wird aktuell nicht genutzt
 *
 */

@Dao
public interface LocationDao {

        @Insert
        void insertLocation(Location location);

        @Update
        void updateLocation(Location location);

        @Delete
        void deleteLocation(Location... location);
        /**
         * getAllLocations
         * @return Liste aller Locations zuück
         * */
     @Query("SELECT * FROM Location")
     public List<Location> getAllLocations();

    }


