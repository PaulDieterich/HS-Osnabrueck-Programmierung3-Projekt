package de.hsos.sportwetter.classes.user;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import de.hsos.sportwetter.classes.sport.Sport;



@Entity(tableName = "USER")
public class User {
    @PrimaryKey @NonNull
    @ColumnInfo(name="user_id")
    private UUID userID;
    @ColumnInfo(name="name")
    private String name;
    @ColumnInfo(name="firstname")
    private String firstname;
    @ColumnInfo(name="gbdate")
    private Date birthday;
    @ColumnInfo(name="liked_sport_acivity")
    List<Sport> likedSportAcivity;
    @ColumnInfo(name="frend_list")
    List<User> frendList;


    public User( String name, String firstname, Date birthday, List<Sport> likedSportAcivity, List<User> frendList) {
        this.userID = UUID.randomUUID();
        this.name = name;
        this.firstname = firstname;
        this.birthday = birthday;
        this.likedSportAcivity = likedSportAcivity;
        this.frendList = frendList;
    }
    @Ignore
    public User(){

    }


    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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
