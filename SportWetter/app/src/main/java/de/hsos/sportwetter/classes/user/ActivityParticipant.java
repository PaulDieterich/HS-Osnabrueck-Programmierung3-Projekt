package de.hsos.sportwetter.classes.user;

import java.util.Date;
import java.util.List;

import de.hsos.sportwetter.classes.sport.Sport;

public class ActivityParticipant extends User {

    public ActivityParticipant(String name, String firstname,String email, Date gbdate, List<Sport> likedSportAcivity, List<User> frendList) {
        super(name, firstname, email,gbdate, likedSportAcivity, frendList);
    }
}
