package de.hsos.sportwetter.classes.activity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import java.util.UUID;
import de.hsos.sportwetter.classes.location.Location;
import de.hsos.sportwetter.classes.sport.Sport;
import de.hsos.sportwetter.classes.user.ActivityProvider;

/**
 * @author Paul Dieterich
 * Activity klasse für die erstellung von Activitäts Objekten
 * ebenso eine emplementierung von androidx.room.entity
 */
@Entity(tableName = "ACTIVITY")
public class Activity {

    @PrimaryKey @NonNull
    @ColumnInfo(name = "activity_id")
    private UUID uuid;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "provider")
    private ActivityProvider provider;
    @ColumnInfo(name = "art")
    private Sport art;
    @ColumnInfo(name = "ziel")
    private Location ziel;
    @ColumnInfo(name = "start")
    private Location start;

    public Activity(){
        this.uuid = UUID.randomUUID();
        this.name = "unset";
        this.provider = new ActivityProvider();
        this.art = new Sport();
        this.ziel = new Location();
        this.start = new Location();
    }
    @Ignore
    public Activity(String name, ActivityProvider provider, Sport art, Location ziel, Location start) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.provider = provider;
        this.art = art;
        this.ziel = ziel;
        this.start = start;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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
        return ziel;
    }

    public void setZiel(Location ziel) {
        this.ziel = ziel;
    }

    public Location getStart() {
        return start;
    }

    public void setStart(Location start) { this.start = start;
    }
}
