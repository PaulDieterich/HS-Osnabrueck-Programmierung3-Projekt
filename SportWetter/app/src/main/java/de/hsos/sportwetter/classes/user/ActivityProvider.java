package de.hsos.sportwetter.user;

import java.util.Date;
import java.util.List;

import de.hsos.sportwetter.sport.Sport;

public class ActivityProvider extends User{
    public ActivityProvider(String name, String firstname, Date gbdate, List<Sport> likedSportAcivity, List<User> frendList) {
        super(name, firstname, gbdate, likedSportAcivity, frendList);
    }



}
