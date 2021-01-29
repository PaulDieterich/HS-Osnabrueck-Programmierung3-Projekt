package de.hsos.sportwetter.classes.user;

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
public interface UserDao {
    @Insert
    void insertSport(User sport);

    @Update
    void updateSport(User user);

    @Delete
    void deleteSport(User... user);

    @Query("SELECT * FROM User")
    public List<User> getAllSports();
}
