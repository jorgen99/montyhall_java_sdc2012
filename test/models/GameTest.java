package models;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class GameTest {

    private Game game;

    @Before
    public void setUp() {
        game = new Game();
        game.playerName = "John Doe";
    }

    @Test
    public void car_door_is_set_on_creation() {
        assertNotNull(game.carDoor);
    }

    @Test
    public void the_opened_goat_door_should_not_be_the_initial_player_door() {
        game.initialPlayerDoor = 1;
        for (int i = 0; i < 500; i++) {
            assertThat(game.goatDoor(), is(not(1)));
        }

    }

    @Test
    public void the_opened_goat_door_should_not_be_the_car_door() {
        game.initialPlayerDoor = 1;
        for (int i = 0; i < 500; i++) {
            assertThat(game.goatDoor(), is(not(game.carDoor)));
        }

    }

    @Test
    public void when_choosing_the_car_door_you_win() {
        game.carDoor = 1;
        game.initialPlayerDoor = 1;
        game.stayOrSwitch(1);
        assertThat(game.won, is(true));
    }

    @Test
    public void when_switching_door_switch_is_true() {
        game.initialPlayerDoor = 1;
        game.stayOrSwitch(2);
        assertThat(game.switched, is(true));
    }


    @Test
    public void selecting_a_door_and_then_switching_should_end_the_game() {
        game.initialPlayerDoor = 1;
        game.stayOrSwitch(2);
        assertThat(game.gameOver, is(true));
    }

}
