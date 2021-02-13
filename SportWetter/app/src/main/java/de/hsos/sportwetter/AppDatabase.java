package de.hsos.sportwetter;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.preference.Preference;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import de.hsos.sportwetter.classes.Converters;
import de.hsos.sportwetter.classes.Preferences;
import de.hsos.sportwetter.classes.activity.Activity;
import de.hsos.sportwetter.classes.activity.ActivityDao;
import de.hsos.sportwetter.classes.location.Location;
import de.hsos.sportwetter.classes.location.LocationDao;
import de.hsos.sportwetter.classes.sport.Sport;
import de.hsos.sportwetter.classes.sport.SportDao;
import de.hsos.sportwetter.classes.user.User;
import de.hsos.sportwetter.classes.user.UserDao;
import de.hsos.sportwetter.classes.weather.City;
import de.hsos.sportwetter.classes.weather.CityDao;


/**
 *
 * @author Paul Dieterich
 *
 */
@Database(entities = {
        User.class,
        Sport.class,
        Location.class,
        Activity.class,
        City.class
},
version = 8,
exportSchema = false
)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract SportDao sportDao();

    public abstract UserDao userDao();

    public abstract LocationDao locationDao();

    public abstract ActivityDao activityDao();

    public abstract CityDao cityDao();

    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    /***
     * @return AppDatabase
     * @param context
     * holt sich die appDatabase instance, wenn die app das erstemal gestartet wird
     * werden die daten aus der sportWeather.db geladen
     * */
    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    Builder<AppDatabase> appDatabase = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "AppDatabase")
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries();

                    if(Preferences.getInstance(context).getFirstStart()){
                        appDatabase.createFromAsset("database/sportWeather.db"); //nur beim ersten Start. füllt die datenbank mit dummydata
                        Preferences.getInstance(context).setFirstStart(false);
                    }
                    INSTANCE = appDatabase.build();
                }
            }
        }
        return INSTANCE;
    }

}