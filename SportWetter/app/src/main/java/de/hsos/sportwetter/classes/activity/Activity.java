package de.hsos.sportwetter.classes.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


import java.util.ArrayList;

import java.util.List;

import java.util.Objects;

import de.hsos.sportwetter.classes.location.Location;
import de.hsos.sportwetter.classes.sport.Sport;
import de.hsos.sportwetter.classes.user.User;

@Entity(tableName = "ACTIVITY")
public class Activity {

    @PrimaryKey(autoGenerate = true) @NonNull
    @ColumnInfo(name = "activity_id")
    private long id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "user")
    public User user;
    @ColumnInfo(name = "art")
    private Sport art;
    @ColumnInfo(name = "ziel")
    private Location ziel;
    @ColumnInfo(name = "start")
    private Location start;
    @ColumnInfo(name = "teilnehmer")
    private List<User> teilnehmer = new ArrayList<>();

    /**
     * @author Paul Dieterich
     * @version 3
     * @since 20.01.2021
     * @param name - Name der Aktivität
     * @param user - Name des Anbieters der Aktivität
     * @param art - Sportart der Aktivität (z.B Joggen)
     * @param ziel - Location, wo die Sportaktivität endet
     * @param start - Location, wo die Sportaktivität beginnt
     * */
    @Ignore
    public Activity(String name, User user, Sport art, Location ziel, Location start) {
        this.name = name;
        this.user = user;
        this.art = art;
        this.ziel = ziel;
        this.start = start;

    }
    public Activity() {
        this.name = "unset";
        this.user = new User();
        this.art = new Sport();
        this.ziel = new Location();
        this.start = new Location();
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Sport getArt() {
        return art;
    }

    public void setArt(Sport art) {
        this.art = art;
    }

    public Location getZiel() {
        return ziel;
    }

    public void setZiel(Location ziel) {
        this.ziel = ziel;
    }

    public Location getStart() {
        return start;
    }

    public void setStart(Location start) { this.start = start;}

    public List<User> getTeilnehmer() {
        return teilnehmer;
    }

    public void setTeilnehmer(List<User> teilnehmer) {
        this.teilnehmer = teilnehmer;
    }
    public void addTeilnehmer(User newTeilnehmer){
        this.teilnehmer.add(newTeilnehmer);
    }

    @Override
    public String toString() {
        return "Activity{" +
                "name='" + name + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activity activity = (Activity) o;
        return id == activity.id &&
                Objects.equals(name, activity.name) &&
                Objects.equals(user, activity.user) &&
                Objects.equals(art, activity.art) &&
                Objects.equals(ziel, activity.ziel) &&
                Objects.equals(start, activity.start);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, user, art, ziel, start);
    }
}
