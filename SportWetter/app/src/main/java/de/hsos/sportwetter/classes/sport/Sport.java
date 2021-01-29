package de.hsos.sportwetter.classes.sport;

import androidx.annotation.NonNull;
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
    @PrimaryKey @NonNull
    @ColumnInfo(name = "sport_id")
    private UUID sportID;
    @ColumnInfo(name = "sport_name")
    private String name;
    @ColumnInfo(name = "min_number_of_participants")
    private int minNumberOfParticipants;
    @ColumnInfo(name = "max_number_of_participants")
    private int maxNumberOfParticipants;

    public Sport(){
        this.sportID = UUID.randomUUID();
        this.name = "unset";
        this.minNumberOfParticipants = 0;
        this.maxNumberOfParticipants = 0;
    }
    public Sport(String name, int min_anzahl, int max_anzahl) {
        this.sportID = UUID.randomUUID();
        this.name = name;
        this.minNumberOfParticipants = min_anzahl;
        this.maxNumberOfParticipants = max_anzahl;
    }

    public Sport(String sport) {
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
    @Override
    public String toString(){
        return "{"+getSportID() +","+getName()+","+ getMinNumberOfParticipants() +","+getMaxNumberOfParticipants() + "}";
    }
}
