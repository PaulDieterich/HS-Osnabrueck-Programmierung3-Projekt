package de.hsos.sportwetter.classes.user;

import java.util.Date;
import java.util.List;

import de.hsos.sportwetter.classes.sport.Sport;

public class ActivityParticipent extends User {

    public ActivityParticipent(String name, String firstname, Date gbdate, List<Sport> likedSportAcivity, List<User> frendList) {
        super(name, firstname, gbdate, likedSportAcivity, frendList);
    }
}
