package de.hsos.sportwetter.classes.user;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.UUID;
import de.hsos.sportwetter.classes.sport.Sport;

@Entity(tableName = "USER")
public class User {
    @PrimaryKey(autoGenerate = true) @NonNull
    @ColumnInfo(name="user_id")
    private long userID;
    @ColumnInfo(name="username")
    private String username;
    @ColumnInfo(name="name")
    private String name;
    @ColumnInfo(name="firstname")
    private String firstname;
    @ColumnInfo(name="email")
    private String email;
    @ColumnInfo(name="passwd")
    private String password;
    @ColumnInfo(name="gbdate")
    private Date birthday;
    @ColumnInfo(name="liked_sport_acivity")
    List<Sport> likedSportAcivity;
    @ColumnInfo(name="friend_list")
    List<User> friendList;


    public User(String name, String firstname, String email, Date birthday, List<Sport> likedSportAcivity, List<User> friendList) {

        this.name = name;
        this.firstname = firstname;
        this.email = email;
        this.birthday = birthday;
        this.likedSportAcivity = likedSportAcivity;
        this.friendList = friendList;
    }
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
    @Ignore
    public User(){

    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
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

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

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

    public List<User> getfriendList() {
        return friendList;
    }

    public void setfriendList(List<User> friendList) {
        this.friendList = friendList;
    }
}
