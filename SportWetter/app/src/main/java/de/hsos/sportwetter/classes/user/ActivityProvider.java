package de.hsos.sportwetter.classes.user;

import java.util.Date;
import java.util.List;

import de.hsos.sportwetter.classes.sport.Sport;

public class ActivityProvider extends User{
    public ActivityProvider(String name, String firstname, Date gbdate, List<Sport> likedSportAcivity, List<User> frendList) {
        super(name, firstname, gbdate, likedSportAcivity, frendList);
    }

    public ActivityProvider(){
        super();
    }
    //TODO: mal überlegen
    public ActivityProvider(String user) {
        super();
    }
}
