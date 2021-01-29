package de.hsos.sportwetter;


import de.hsos.sportwetter.user.User;

@androidx.room.Database(entities = {
        User.class,

} ,
        version = 0,
        exportSchema = false)


public class Database {
}
