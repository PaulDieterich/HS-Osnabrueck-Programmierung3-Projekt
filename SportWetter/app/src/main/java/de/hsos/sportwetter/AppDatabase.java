package de.hsos.sportwetter;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import de.hsos.sportwetter.activity.Activity;
import de.hsos.sportwetter.activity.ActivityDao;
import de.hsos.sportwetter.location.Location;
import de.hsos.sportwetter.location.LocationDao;
import de.hsos.sportwetter.sport.Sport;
import de.hsos.sportwetter.sport.SportDao;
import de.hsos.sportwetter.user.User;
import de.hsos.sportwetter.user.UserDao;


/**
 *
 * @author Paul Dieterich
 *
 */
@Database(entities = {
        User.class,
        Sport.class,
        Location.class,
        Activity.class
},
version = 0,
exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {
    public abstract SportDao sportDao();

    public abstract UserDao userDao();

    public abstract LocationDao locationDao();

    public abstract ActivityDao activityDao();

    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null)
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "AppDatabase").build();
            }
        }
        return INSTANCE;
    }
}