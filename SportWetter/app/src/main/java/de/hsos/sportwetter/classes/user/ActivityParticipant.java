package de.hsos.sportwetter.classes.user;

import java.util.Date;
import java.util.List;

import de.hsos.sportwetter.classes.sport.Sport;

public class ActivityParticipant extends User {

    public ActivityParticipant(String username ,String name, String firstname,String email, String password, Date birthday, List<Sport> likedSportAcivity, List<User> friendList) {
        super();
    }
    public ActivityParticipant(){
        super();
    };
    //für den typeconvert
    public ActivityParticipant(String user){
        super();
    };
}
