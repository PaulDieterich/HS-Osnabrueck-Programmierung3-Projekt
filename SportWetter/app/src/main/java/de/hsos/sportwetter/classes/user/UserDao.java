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
    long insertUser(User user);

    @Update
    void updateUser(User user);

    @Delete
    void deleteUser(User... user);


    /**
     * getAllUsers()
     * @return eine list von Users
     * */
    @Query("SELECT * FROM User")
    public List<User> getAllUsers();


}
