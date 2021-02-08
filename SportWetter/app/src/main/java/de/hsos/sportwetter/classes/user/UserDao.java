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
    void insertUser(User sport);

    @Update
    void updateUser(User user);

    @Delete
    void deleteUser(User... user);

    @Query("SELECT * FROM User")
    public List<User> getAllUsers();



}
