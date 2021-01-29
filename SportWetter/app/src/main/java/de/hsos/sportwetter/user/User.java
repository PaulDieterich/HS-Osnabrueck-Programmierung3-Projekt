package de.hsos.sportwetter.user;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import de.hsos.sportwetter.sport.Sport;



@Entity(tableName = "USER")
public class User {
    @PrimaryKey
    @ColumnInfo(name="user_id")
    private UUID userID;
    @ColumnInfo(name="name")
    private String name;
    @ColumnInfo(name="firstname")
    private String firstname;
    @ColumnInfo(name="gbdate")
    private Date gbdate;
    @ColumnInfo(name="liked_sport_acivity")
    List<Sport> likedSportAcivity;
    @ColumnInfo(name="frend_list")
    List<User> frendList;

    public User( String name, String firstname, Date gbdate, List<Sport> likedSportAcivity, List<User> frendList) {
        this.userID = UUID.randomUUID();
        this.name = name;
        this.firstname = firstname;
        this.gbdate = gbdate;
        this.likedSportAcivity = likedSportAcivity;
        this.frendList = frendList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Date getGbdate() {
        return gbdate;
    }

    public void setGbdate(Date gbdate) {
        this.gbdate = gbdate;
    }

    public List<Sport> getLikedSportAcivity() {
        return likedSportAcivity;
    }

    public void setLikedSportAcivity(List<Sport> likedSportAcivity) {
        this.likedSportAcivity = likedSportAcivity;
    }

    public List<User> getFrendList() {
        return frendList;
    }

    public void setFrendList(List<User> frendList) {
        this.frendList = frendList;
    }
}
