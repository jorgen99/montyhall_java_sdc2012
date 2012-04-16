import models.Game;

import util.GamePlayer;

import play.Logger;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AcceptanceTest {

    @Test
    public void always_switching_should_win_about_two_thirds_of_the_time() {
        GamePlayer gamePlayer = new GamePlayer();
        int wonGames = 0;
        for (int i = 0; i < 10000; i++) {
            Game game = gamePlayer.playGameSwitchingDoor();
            if(game.won) {
                wonGames++;
            }
        }

        boolean wonMoreThanSixtyPercent = wonGames > 6000;
        boolean wonLessThanSeventyPercent = wonGames < 7000;
        assertThat(wonMoreThanSixtyPercent, is(true));
        assertThat(wonLessThanSeventyPercent, is(true));
    }

}
