package de.hsos.sportwetter;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import de.hsos.sportwetter.classes.Converters;
import de.hsos.sportwetter.classes.activity.Activity;
import de.hsos.sportwetter.classes.activity.ActivityDao;
import de.hsos.sportwetter.classes.location.Location;
import de.hsos.sportwetter.classes.location.LocationDao;
import de.hsos.sportwetter.classes.sport.Sport;
import de.hsos.sportwetter.classes.sport.SportDao;
import de.hsos.sportwetter.classes.user.User;
import de.hsos.sportwetter.classes.user.UserDao;


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
version = 1,
exportSchema = false
)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract SportDao sportDao();

    public abstract UserDao userDao();

    public abstract LocationDao locationDao();

    public abstract ActivityDao activityDao();

    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null)
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "AppDatabase")
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .createFromAsset("database/sportWeather.db")
                            .build();
            }
        }
        return INSTANCE;
    }
}