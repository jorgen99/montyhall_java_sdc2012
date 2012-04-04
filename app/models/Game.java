package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Random;

@Entity
public class Game extends Model {

    @Id
    public Long id;

    @Constraints.Required
    public String playerName;
    public Integer initialPlayerDoor;
    public Integer carDoor = randomDoor();
    public boolean switched;
    public boolean won;
    public boolean gameOver;

    public static Finder<Long, Game> find = new Finder<Long, Game>(Long.class, Game.class);

    public Integer goatDoor() {
        while(true) {
            int possibleGoat = randomDoor();
            if(possibleGoat != carDoor && possibleGoat != initialPlayerDoor) {
                return possibleGoat;
            }
        }
    }

    public void stayOrSwitch(int doorNo) {
        if(doorNo != initialPlayerDoor) {
            switched = true;
        }
        if(doorNo == carDoor) {
            won = true;
        }
        gameOver = true;
    }


    private int randomDoor() {
        return new Random().nextInt(3) + 1;
    }

}
