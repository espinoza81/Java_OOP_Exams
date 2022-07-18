package viceCity.models.neighbourhood;

import viceCity.models.players.Player;

import java.util.ArrayDeque;

public interface Neighbourhood {
    void action(Player mainPlayer, ArrayDeque<Player> civilPlayers);
}