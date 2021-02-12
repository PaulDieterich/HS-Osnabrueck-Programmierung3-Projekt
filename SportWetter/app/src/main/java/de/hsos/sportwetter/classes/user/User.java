package de.hsos.sportwetter.classes.user;

import android.content.Context;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.UUID;
import de.hsos.sportwetter.classes.sport.Sport;

@Entity(tableName = "USER")
public class User{
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
    /**
     * @author Paul Dieterich
     * @version 1
     * @since 20.01.2021
     * */
    @Ignore
    public User(String username ,String name, String firstname,String email, String password, Date birthday, List<Sport> likedSportAcivity, List<User> friendList) {
    setUsername(username);
    setName(name);
    setFirstname(firstname);
    setEmail(email);
    setPassword(password);
    setBirthday(birthday);
    setLikedSportAcivity(likedSportAcivity);
    setfriendList(friendList);
    }

    public User(String username, String email, String password) {
        this(username,"", "", email,password ,null, null,null);
    }

    @Ignore
    public User(){ }

    public User(String user) {
        setUsername(user);
        setName("name");
        setFirstname("firstname");
        setEmail("email");
        setPassword("password");
        setBirthday(null);
        setLikedSportAcivity(null);
        setfriendList(null);

        }


    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email, password);
    }
}
