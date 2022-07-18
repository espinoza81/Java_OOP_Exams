package viceCity.models.players;

import viceCity.common.Constants;

public class CivilPlayer extends BasePlayer {
    public CivilPlayer(String name) {
        super(name, Constants.CIVIL_PLAYER_LIFE_POINTS);
    }
}