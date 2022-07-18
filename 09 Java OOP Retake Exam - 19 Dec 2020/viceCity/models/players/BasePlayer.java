package viceCity.models.players;

import viceCity.common.ExceptionMessages;
import viceCity.models.guns.Gun;
import viceCity.repositories.interfaces.GunRepository;
import viceCity.repositories.interfaces.Repository;

public abstract class BasePlayer implements Player {
    private String name;
    private int lifePoints;
    private Repository<Gun> gunRepository;

    public BasePlayer(String name, int lifePoints) {
        setName(name);
        setLifePoints(lifePoints);
        this.gunRepository = new GunRepository();
    }

    private void setName(String name) {
        if(name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.NAME_NULL);
        }

        this.name = name;
    }

    private void setLifePoints(int lifePoints) {
        if(lifePoints < 0) {
            throw new IllegalArgumentException(ExceptionMessages.PLAYER_LIFE_POINTS_LESS_THAN_ZERO);
        }

        this.lifePoints = lifePoints;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getLifePoints() {
        return lifePoints;
    }

    @Override
    public boolean isAlive() {
        //if the player's health is more than 0, he is alive.
        return lifePoints > 0;
    }

    @Override
    public Repository<Gun> getGunRepository() {
        return gunRepository;
    }

    @Override
    public void takeLifePoints(int points) {
        //The takeLifePoints method decreases players' life points.
        // Player's life points should not drop below zero.

        lifePoints -= points;
    }
}