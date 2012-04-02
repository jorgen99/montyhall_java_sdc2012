package controllers;

import models.Game;
import views.html.game;
import views.html.index;

import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import org.codehaus.jackson.node.ObjectNode;

public class Application extends Controller {

    public static Result index() {
        Form<Game> gameForm = form(Game.class);
        return ok(index.render(gameForm));
    }

    public static Result newGame() {
        Form<Game> gameForm = form(Game.class).bindFromRequest();
        if (gameForm.hasErrors()) {
            return badRequest(index.render(gameForm));
        }
        Game newGame = gameForm.get();
        return redirectToGame(newGame);
    }

    public static Result newGameFor(String playerName) {
        Game newGame = new Game();
        newGame.playerName = playerName;
        return redirectToGame(newGame);
    }

    public static Result game(Long id) {
        Game currentGame = Game.find.byId(id);
        if(currentGame == null) {
            return redirect(routes.Application.index());
        }
        if(currentGame.gameOver) {
            return redirect(routes.Application.newGameFor(currentGame.playerName));
        }
        return ok(game.render(currentGame));
    }

    public static Result selectDoor(Long id, int doorNo) {
        Game currentGame = Game.find.byId(id);
        currentGame.initialPlayerDoor = doorNo;
        currentGame.save();
        ObjectNode result = Json.newObject();
        result.put("goatDoor", currentGame.goatDoor());
        return ok(result);
    }

    public static Result stayOrSwitch(Long id, int doorNo) {
        Game currentGame = Game.find.byId(id);
        currentGame.stayOrSwitch(doorNo);
        currentGame.save();
        ObjectNode result = Json.newObject();
        result.put("carDoor", currentGame.carDoor);
        result.put("won", currentGame.won);
        return ok(result);
    }

    private static Result redirectToGame(Game newGame) {
        newGame.save();
        flash("success", "Game " + newGame.playerName + " has been created");
        return redirect(routes.Application.game(newGame.id));
    }

}