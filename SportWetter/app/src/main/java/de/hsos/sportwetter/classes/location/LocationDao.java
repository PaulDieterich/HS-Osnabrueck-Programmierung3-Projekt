package de.hsos.sportwetter.location;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;



/**
 * @author Paul Dieterich
 * Dao für Sport
 */
@Dao
public interface LocationDao {

        @Insert
        void insertLocation(Location location);

        @Update
        void updateLocation(Location location);

        @Delete
        void deleteLocation(Location... location);

        @Query("SELECT * FROM Sport")
        public List<Location> getAllLocations();

    }


