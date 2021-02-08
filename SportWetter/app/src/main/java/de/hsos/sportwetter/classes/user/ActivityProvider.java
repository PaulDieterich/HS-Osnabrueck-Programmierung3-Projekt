package de.hsos.sportwetter.classes.user;

import java.util.Date;
import java.util.List;

import de.hsos.sportwetter.classes.sport.Sport;

public class ActivityProvider extends User{
    public ActivityProvider(String username ,String name, String firstname,String email, String password, Date birthday, List<Sport> likedSportAcivity, List<User> friendList){
    super();
    }
    //für den type convert
    public ActivityProvider(String user){
        super();
    }

    public ActivityProvider() {
        super();
    }


}
