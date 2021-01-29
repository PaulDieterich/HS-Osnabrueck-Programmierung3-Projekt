package de.hsos.sportwetter.sport;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;
/**
 * @author Paul Dieterich
 * sport klasse für die erstellung von sport Objekten
 * ebenso eine emplementierung von androidx.room.entity
 */

@Entity(tableName = "SPORT")
public class Sport {
    @PrimaryKey
    @ColumnInfo(name = "sport_id")
    private UUID sportID;
    @ColumnInfo(name = "sport_name")
    private String name;
    @ColumnInfo(name = "min_number_of_participants")
    private int minNumberOfParticipants;
    @ColumnInfo(name = "max_number_of_participants")
    private int maxNumberOfParticipants;

    public Sport(UUID sportID, String name, int min_anzahl, int max_anzahl) {
        this.sportID = sportID;
        this.name = name;
        this.minNumberOfParticipants = min_anzahl;
        this.maxNumberOfParticipants = max_anzahl;
    }

    public UUID getSportID() {
        return sportID;
    }

    public void setSportID(UUID sportID) {
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
}
