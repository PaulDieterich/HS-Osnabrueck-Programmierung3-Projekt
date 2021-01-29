package de.hsos.sportwetter.sport;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

/**
 * @author Paul Dieterich
 * Dao für Sport
 */


@Dao
public interface SportDao {
    @Insert
    void insertSport(Sport sport);

    @Update
    void updateSport(Sport sport);

    @Delete
    void deleteSport(Sport... sports);

    @Query("SELECT * FROM Sport")
    public List<Sport> getAllSports();

}
