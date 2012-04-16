package util;

import models.Game;

public class GamePlayer {

    public Game playGameSwitchingDoor() {
        Game game = startNewGameAndPickFirstDoor();
        switchDoor(game);
        return game;
    }

    private Game startNewGameAndPickFirstDoor() {
        Game game = new Game();
        game.initialPlayerDoor = 1;
        return game;
    }

    private void switchDoor(Game game) {
        int goatDoor = game.goatDoor();
        if(goatDoor == 2) {
            game.stayOrSwitch(3);
        } else {
            game.stayOrSwitch(2);
        }
    }
}
