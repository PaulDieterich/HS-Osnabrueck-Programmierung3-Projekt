package de.hsos.sportwetter.classes.weather;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CityDao {

        @Insert
        void insertCit(City city);

        @Update
        void updateCity(City city);

        @Delete
        void deleteCity(City... city);


        /**
         *
         * getAllCitys()
         * @return Eine Liste von Citys
         */

        @Query("SELECT * FROM city")
        public List<City> getAllCitys();


        /**
         * getCursor()
         * @param name - sortiert nach der Länge des eigegebenen Strings
         * @return Cursor
         * */
        @Query("SELECT * FROM city  WHERE name like :name ORDER BY length(name)")
        public Cursor getCursor(String name);
}


