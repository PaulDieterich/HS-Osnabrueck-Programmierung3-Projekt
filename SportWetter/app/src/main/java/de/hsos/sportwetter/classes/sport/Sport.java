package de.hsos.sportwetter.classes.sport;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.UUID;
/**
 * @author Paul Dieterich
 * sport klasse für die erstellung von sport Objekten
 * ebenso eine emplementierung von androidx.room.entity
 */

@Entity(tableName = "SPORT")
public class Sport {
    @PrimaryKey(autoGenerate = true) @NonNull
    @ColumnInfo(name = "sport_id")
    private long sportID;
    @ColumnInfo(name = "sport_name")
    private String name;
    @ColumnInfo(name = "min_number_of_participants")
    private int minNumberOfParticipants;
    @ColumnInfo(name = "max_number_of_participants")
    private int maxNumberOfParticipants;
    @Ignore
    public Sport(){

        this.name = "unset";
        this.minNumberOfParticipants = 0;
        this.maxNumberOfParticipants = 0;
    }
    /**
     * @author Paul Dieterich
     * @version 1
     * @since 20.01.2021
     * @param name - name des sports, z.B klettern
     * @param min_anzahl - interesannt für gruppensport oder kleingruppen
     * @param max_anzahl - interesannt für gruppensport oder kleingruppen
     * */
    @Ignore
    public Sport(String name, int min_anzahl, int max_anzahl) {

        this.name = name;
        this.minNumberOfParticipants = min_anzahl;
        this.maxNumberOfParticipants = max_anzahl;
    }

    public Sport(String name) {
        this.name = name;
    }

    public long getSportID() {
        return sportID;
    }

    public void setSportID(long sportID) {
        this.sportID = sportID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMinNumberOfParticipants() {
        return minNumberOfParticipants;
    }

    public void setMinNumberOfParticipants(int minNumberOfParticipants) {
        this.minNumberOfParticipants = minNumberOfParticipants;
    }

    public int getMaxNumberOfParticipants() {
        return maxNumberOfParticipants;
    }

    public void setMaxNumberOfParticipants(int maxNumberOfParticipants) {
        this.maxNumberOfParticipants = maxNumberOfParticipants;
    }

    @Override
    public String toString() {
        return name;
    }
}
