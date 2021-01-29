package de.hsos.sportwetter.activity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.UUID;
import de.hsos.sportwetter.location.Location;
import de.hsos.sportwetter.sport.Sport;
import de.hsos.sportwetter.user.ActivityProvider;

/**
 * @author Paul Dieterich
 * Activity klasse für die erstellung von Activitäts Objekten
 * ebenso eine emplementierung von androidx.room.entity
 */
@Entity(tableName = "ACTIVITY")
public class Activity {

    @PrimaryKey
    @ColumnInfo(name = "activity_id")
    private UUID uuid;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "provider")
    private ActivityProvider provider;
    @ColumnInfo(name = "art")
    private Sport art;
    @ColumnInfo(name = "ziel")
    private Location Ziel;
    @ColumnInfo(name = "start")
    private Location Start;

    public Activity(String name, ActivityProvider provider, Sport art, Location ziel, Location start) {
        this.name = name;
        this.provider = provider;
        this.art = art;
        Ziel = ziel;
        Start = start;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ActivityProvider getProvider() {
        return provider;
    }

    public void setProvider(ActivityProvider provider) {
        this.provider = provider;
    }

    public Sport getArt() {
        return art;
    }

    public void setArt(Sport art) {
        this.art = art;
    }

    public Location getZiel() {
        return Ziel;
    }

    public void setZiel(Location ziel) {
        Ziel = ziel;
    }

    public Location getStart() {
        return Start;
    }

    public void setStart(Location start) {
        Start = start;
    }
}
